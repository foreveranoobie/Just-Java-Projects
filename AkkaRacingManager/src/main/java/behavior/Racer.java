package behavior;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.PostStop;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.io.Serializable;
import java.util.Random;

public class Racer extends AbstractBehavior<Racer.Command> {
    private final double defaultAverageSpeed = 48.2;
    private Random random;
    private double currentSpeed = 0;

    public Racer(ActorContext<Command> context) {
        super(context);
    }

    public static Behavior<Command> createBehavior() {
        return Behaviors.setup(Racer::new);
    }

    @Override
    public Receive<Command> createReceive() {
        return newReceiveBuilder()
                .onMessage(StartCommand.class, command -> {
                    int raceLength = command.getRaceLength();
                    random = new Random();
                    int averageSpeedAdjustmentFactor = random.nextInt(30) - 10;
                    return createRunningReceive(raceLength, averageSpeedAdjustmentFactor, 0);
                })
                .onMessage(PositionCommand.class, command -> {
                    System.out.println("Start position asked");
                    command.getController().tell(new RaceController.RacerUpdateCommand(getContext().getSelf(), 0));
                    return Behaviors.same();
                })
                .build();
    }

    public Receive<Command> createRunningReceive(int raceLength, int averageSpeedAdjustmentFactor,
                                                 final int currentPosition) {
        return newReceiveBuilder()
                .onMessage(PositionCommand.class, command -> {
                    int newPosition = currentPosition;
                    determineNextSpeed(currentPosition, raceLength, averageSpeedAdjustmentFactor);
                    newPosition += getDistanceMovedPerSecond();
                    if (newPosition > raceLength)
                        newPosition = raceLength;
                    command.getController().tell(new RaceController.RacerUpdateCommand(getContext().getSelf(),
                            newPosition));
                    if (newPosition == raceLength) {
                        return createCompletedReceive(raceLength);
                    } else {
                        return createRunningReceive(raceLength, averageSpeedAdjustmentFactor, newPosition);
                    }
                })
                .build();
    }

    public Receive<Command> createCompletedReceive(int raceLength) {
        return newReceiveBuilder()
                .onMessage(PositionCommand.class, command -> {
                    command.getController().tell(new RaceController.RacerUpdateCommand(getContext().getSelf(), raceLength));
                    command.getController().tell(new RaceController.RacerFinishedCommand(getContext().getSelf()));
                    return afterCompletedReceive();
                })
                .build();
    }

    public Receive<Command> afterCompletedReceive() {
        return newReceiveBuilder()
                .onAnyMessage(command -> Behaviors.same())
                .onSignal(PostStop.class, signal -> {
                    getContext().getLog().info("Racer is about to stop");
                    return Behaviors.same();
                })
                .build();
    }

    private double getMaxSpeed(int averageSpeedAdjustmentFactor) {
        return defaultAverageSpeed * (1 + ((double) averageSpeedAdjustmentFactor / 100));
    }

    private double getDistanceMovedPerSecond() {
        return currentSpeed * 1000 / 3600;
    }

    private void determineNextSpeed(int currentPosition, int raceLength, int averageSpeedAdjustmentFactor) {
        if (currentPosition < (raceLength / 4)) {
            currentSpeed = currentSpeed + (((getMaxSpeed(averageSpeedAdjustmentFactor) - currentSpeed) / 10) * random.nextDouble());
        } else {
            currentSpeed = currentSpeed * (0.5 + random.nextDouble());
        }

        if (currentSpeed > getMaxSpeed(averageSpeedAdjustmentFactor))
            currentSpeed = getMaxSpeed(averageSpeedAdjustmentFactor);

        if (currentSpeed < 5)
            currentSpeed = 5;

        if (currentPosition > (raceLength / 2) && currentSpeed < getMaxSpeed(averageSpeedAdjustmentFactor) / 2) {
            currentSpeed = getMaxSpeed(averageSpeedAdjustmentFactor) / 2;
        }
    }

    public interface Command extends Serializable {
    }

    public static class StartCommand implements Command {
        public static final long serialVersionUID = 1L;
        private int raceLength;

        public StartCommand(int raceLength) {
            this.raceLength = raceLength;
        }

        public int getRaceLength() {
            return raceLength;
        }
    }

    public static class PositionCommand implements Command {
        public static final long serialVersionUID = 1L;
        private ActorRef<RaceController.Command> controller;

        public PositionCommand(ActorRef<RaceController.Command> controller) {
            this.controller = controller;
        }

        public ActorRef<RaceController.Command> getController() {
            return controller;
        }
    }
}
