package blockchain;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.SupervisorStrategy;
import akka.actor.typed.Terminated;
import akka.actor.typed.javadsl.*;
import model.Block;
import model.HashResult;

import java.io.Serializable;
import java.util.Objects;

public class ManagerBehavior extends AbstractBehavior<ManagerBehavior.Command> {

    private ActorRef<HashResult> sender;
    private int difficultyLevel;
    private Block block;
    private int currentNonce;
    private boolean currentlyMining;
    private StashBuffer<Command> stashBuffer;

    public ManagerBehavior(ActorContext<Command> context, StashBuffer<Command> stashBuffer) {
        super(context);
        this.stashBuffer = stashBuffer;
    }

    public static Behavior<Command> createBehavior() {
        return Behaviors.withStash(10,
                buffer -> Behaviors.setup(context -> new ManagerBehavior(context, buffer)));
    }

    @Override
    public Receive<Command> createReceive() {
        return newReceiveBuilder()
                .onMessage(MineBlockCommand.class, command -> {
                    sender = command.getSender();
                    block = command.getBlock();
                    difficultyLevel = command.getDifficultyLevel();
                    currentlyMining = true;
                    for (int i = 0; i < 10; i++) {
                        startNextWorker();
                    }
                    return onMiningActiveCommand();
                })
/*                .onMessage(HashResultCommand.class, command -> {
                    ActorContext<Command> context = getContext();
                    context.getChildren().forEach(context::stop);
                    currentlyMining = false;
                    sender.tell(command.getHashResult());
                    return Behaviors.same();
                })*/
                .onSignal(Terminated.class, handler -> {
                    startNextWorker();
                    return Behaviors.same();
                })
                .build();
    }

    public Receive<Command> onMiningActiveCommand() {
        return newReceiveBuilder()
                .onMessage(MineBlockCommand.class, command -> {
                    //getContext().getSelf().tell(command);
                    if (!stashBuffer.isFull()) {
                        stashBuffer.stash(command);
                    }
                    return Behaviors.same();
                })
                .onMessage(HashResultCommand.class, command -> {
                    ActorContext<Command> context = getContext();
                    context.getChildren().forEach(context::stop);
                    currentlyMining = false;
                    sender.tell(command.getHashResult());
                    return stashBuffer.unstashAll(createReceive());
                })
                .onSignal(Terminated.class, handler -> {
                    startNextWorker();
                    return Behaviors.same();
                })
                .build();
    }

    private void startNextWorker() {
        if (currentlyMining) {
            Behavior<WorkerBehavior.Command> worker = Behaviors.supervise(WorkerBehavior.createBehavior())
                    .onFailure(SupervisorStrategy.resume());
            ActorRef<WorkerBehavior.Command> workerActor = getContext().spawn(worker, "Worker-" + currentNonce);
            getContext().watch(workerActor);
            workerActor.tell(new WorkerBehavior.Command(block, currentNonce * 1000, difficultyLevel, getContext().getSelf()));
            currentNonce++;
        }
    }

    public interface Command extends Serializable {
    }

    public static class MineBlockCommand implements Command {
        public static final long serialVersionUID = 1L;
        private Block block;
        private ActorRef<HashResult> sender;
        private int difficultyLevel;

        public MineBlockCommand(Block block, ActorRef<HashResult> sender, int difficultyLevel) {
            this.block = block;
            this.sender = sender;
            this.difficultyLevel = difficultyLevel;
        }

        public Block getBlock() {
            return block;
        }

        public ActorRef<HashResult> getSender() {
            return sender;
        }

        public int getDifficultyLevel() {
            return difficultyLevel;
        }
    }

    public static class HashResultCommand implements Command {
        public static final long serialVersionUID = 1L;
        private HashResult hashResult;

        public HashResultCommand(HashResult hashResult) {
            this.hashResult = hashResult;
        }

        public HashResult getHashResult() {
            return hashResult;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            HashResultCommand that = (HashResultCommand) o;
            return Objects.equals(getHashResult(), that.getHashResult());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getHashResult());
        }
    }
}
