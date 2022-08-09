package behavior;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Random;

public class WorkerBehavior extends AbstractBehavior<WorkerBehavior.WorkerCommand> {

    public static class WorkerCommand implements Serializable {
        public static final Long serialVersionUID = 1L;

        private String message;
        private ActorRef<ManagerBehavior.Command> actor;

        public WorkerCommand(String message, ActorRef<ManagerBehavior.Command> actor) {
            this.message = message;
            this.actor = actor;
        }

        public String getMessage() {
            return message;
        }

        public ActorRef<ManagerBehavior.Command> getActor() {
            return actor;
        }
    }

    private WorkerBehavior(ActorContext<WorkerCommand> context) {
        super(context);
    }

    public static Behavior<WorkerCommand> createBehavior() {
        return Behaviors.setup(WorkerBehavior::new);
    }

    public Receive<WorkerCommand> createReceive() {
        return createPrimeBehavior();
    }

    public Receive<WorkerCommand> createPrimeBehavior() {
        return newReceiveBuilder()
                .onAnyMessage(command -> {
                    BigInteger prime = new BigInteger("0");
                    if ("start".equals(command.getMessage())) {
                        BigInteger bigInteger = new BigInteger(2000, new Random());
                        prime = bigInteger.nextProbablePrime();
                        ManagerBehavior.DataCommand dataCommand = new ManagerBehavior.DataCommand(prime);
                        Random random = new Random();
                        //Just made for implementation of error-processing retries
                        if (random.nextInt(5) < 2) {
                            command.getActor().tell(dataCommand);
                        }
                    }
                    return afterPrimeCreatedBehavior(prime);
                })
                .build();
    }

    public Receive<WorkerCommand> afterPrimeCreatedBehavior(BigInteger prime) {
        return newReceiveBuilder()
                .onAnyMessage(command -> {
                    if ("start".equals(command.getMessage())) {
                        ManagerBehavior.DataCommand dataCommand = new ManagerBehavior.DataCommand(prime);
                        Random random = new Random();
                        //Just made for implementation of error-processing retries
                        if (random.nextInt(5) < 2) {
                            command.getActor().tell(dataCommand);
                        }
                    }
                    return this;
                })
                .build();
    }


}
