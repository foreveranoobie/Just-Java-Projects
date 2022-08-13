package blockchain;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import model.Block;
import model.HashResult;

import java.io.Serializable;
import java.util.Random;

import static utils.BlockChainUtils.calculateHash;

public class WorkerBehavior extends AbstractBehavior<WorkerBehavior.Command> {
    private WorkerBehavior(ActorContext<Command> context) {
        super(context);
    }

    public static Behavior<Command> createBehavior() {
        return Behaviors.setup(WorkerBehavior::new);
    }

    public Receive<Command> createReceive() {
        return newReceiveBuilder()
                .onMessage(Command.class, command -> {
                    int difficultyLevel = command.getDifficultyLevel();
                    String hash = new String(new char[difficultyLevel]).replace("\0", "X");
                    String target = new String(new char[difficultyLevel]).replace("\0", "0");

                    int startNonce = command.getStartNonce();
                    int nonce = command.getStartNonce();
                    Block block = command.getBlock();
                    while (!hash.substring(0, difficultyLevel).equals(target) && nonce < startNonce + 1000) {
                        nonce++;
                        String dataToEncode = block.getPreviousHash() + Long.toString(block.getTransaction().getTimestamp()) + Integer.toString(nonce) + block.getTransaction();
                        hash = calculateHash(dataToEncode);
                    }
                    if (hash.substring(0, difficultyLevel).equals(target)) {
                        HashResult hashResult = new HashResult();
                        hashResult.foundAHash(hash, nonce);
                        getContext().getLog().debug(nonce + " : " + hash);
                        command.getController().tell(new ManagerBehavior.HashResultCommand(hashResult));
                        return Behaviors.same();
                    } else {
                        getContext().getLog().debug("null");
/*                        Random random = new Random();
                        if (random.nextInt(10) == 3) {
                            throw new ArithmeticException("No hash found!");
                        }*/
                        return Behaviors.stopped();
                    }
                })
                .build();
    }

    public static class Command implements Serializable {
        public static final long serialVersionUID = 1L;
        private Block block;
        private int startNonce;
        private int difficultyLevel;
        private ActorRef<ManagerBehavior.Command> controller;

        public Command(Block block, int startNonce, int difficultyLevel, ActorRef<ManagerBehavior.Command> controller) {
            this.block = block;
            this.startNonce = startNonce;
            this.difficultyLevel = difficultyLevel;
            this.controller = controller;
        }

        public Block getBlock() {
            return block;
        }

        public int getStartNonce() {
            return startNonce;
        }

        public int getDifficultyLevel() {
            return difficultyLevel;
        }

        public ActorRef<ManagerBehavior.Command> getController() {
            return controller;
        }
    }
}
