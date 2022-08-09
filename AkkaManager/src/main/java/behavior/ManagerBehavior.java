package behavior;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.Duration;
import java.util.Set;
import java.util.TreeSet;

public class ManagerBehavior extends AbstractBehavior<ManagerBehavior.Command> {
    public interface Command extends Serializable {
    }

    public static class InstructionCommand implements Command {
        public static final long serialVersionUID = 1L;
        private String message;
        private ActorRef<Set<BigInteger>> sender;

        public InstructionCommand(String message, ActorRef<Set<BigInteger>> sender) {
            this.message = message;
            this.sender = sender;
        }

        public String getMessage() {
            return message;
        }

        public ActorRef<Set<BigInteger>> getSender() {
            return sender;
        }
    }

    public static class DataCommand implements Command {
        public static final long serialVersionUID = 1L;
        private BigInteger number;

        public DataCommand(BigInteger number) {
            this.number = number;
        }

        public BigInteger getNumber() {
            return number;
        }
    }

    private class ErrorResponseCommand implements Command {
        public static final long serialVersionUID = 1L;
        private ActorRef<WorkerBehavior.WorkerCommand> worker;

        private ErrorResponseCommand(ActorRef<WorkerBehavior.WorkerCommand> worker) {
            this.worker = worker;
        }

        public ActorRef<WorkerBehavior.WorkerCommand> getWorker() {
            return worker;
        }
    }

    private Set<BigInteger> numbers = new TreeSet<>();
    private ActorRef<Set<BigInteger>> sender;

    private ManagerBehavior(ActorContext<Command> context) {
        super(context);
    }

    public static Behavior<Command> createBehavior() {
        return Behaviors.setup(ManagerBehavior::new);
    }

    @Override
    public Receive<Command> createReceive() {
        return newReceiveBuilder()
                .onMessage(InstructionCommand.class, (command) -> {
                    if ("start".equals(command.getMessage())) {
                        sender = command.getSender();
                        ActorContext<Command> context = getContext();
                        for (int i = 0; i < 20; i++) {
                            ActorRef<WorkerBehavior.WorkerCommand> actor =
                                    context.spawn(WorkerBehavior.createBehavior(), String.format("Worker-%d", i));
                            askForBigPrime(actor);
                        }
                    }
                    return Behaviors.same();
                })
                .onMessage(DataCommand.class, (command) -> {
                    numbers.add(command.getNumber());
                    System.out.println("The size of numbers set is = " + numbers.size());
                    if (numbers.size() == 20) {
                        sender.tell(numbers);
                    }
                    return Behaviors.same();
                })
                .onMessage(ErrorResponseCommand.class, command -> {
                    System.out.println("Retrying to request prime number from " + command.getWorker().path().name());
                    askForBigPrime(command.getWorker());
                    return Behaviors.same();
                })
                .build();
    }

    public void askForBigPrime(ActorRef<WorkerBehavior.WorkerCommand> worker) {
        getContext().ask(Command.class, worker, Duration.ofSeconds(5L), (requester) -> new WorkerBehavior.WorkerCommand("start", requester),
                (response, throwable) -> {
                    if (response != null) {
                        return response;
                    } else {
                        System.out.println("No response came from " + worker.path().name());
                        return new ErrorResponseCommand(worker);
                    }
                });
    }
}
