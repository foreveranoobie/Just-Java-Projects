package behavior;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

import java.io.Serializable;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class RaceController extends AbstractBehavior<RaceController.Command> {

    private Map<ActorRef<Racer.Command>, Integer> positionsMap;
    private Map<ActorRef<Racer.Command>, Long> finishTimeMap;
    private long start;
    private int raceLength = 100;
    private Object TIMER_KEY;

    public RaceController(ActorContext<Command> context) {
        super(context);
    }

    public static Behavior<Command> createBehavior() {
        return Behaviors.setup(RaceController::new);
    }

    public Receive<Command> createReceive() {
        return newReceiveBuilder()
                .onMessage(StartCommand.class, command -> {
                    start = System.currentTimeMillis();
                    positionsMap = new HashMap<>();
                    finishTimeMap = new HashMap<>();
                    for (int i = 0; i < 10; i++) {
                        ActorRef<Racer.Command> racer = getContext().spawn(Racer.createBehavior(),
                                String.format("Racer-%d", i));
                        positionsMap.put(racer, 0);
                        racer.tell(new Racer.StartCommand(raceLength));
                    }
                    return Behaviors.withTimers(timer -> {
                        timer.startTimerAtFixedRate(TIMER_KEY, new GetPositionCommand(), Duration.ofSeconds(1L));
                        return Behaviors.same();
                    });
                })
                .onMessage(GetPositionCommand.class, command -> {
                    for (ActorRef<Racer.Command> racer : positionsMap.keySet()) {
                        racer.tell(new Racer.PositionCommand(getContext().getSelf()));
                        displayRace();
                    }
                    return Behaviors.same();
                })
                .onMessage(RacerUpdateCommand.class, command -> {
                    positionsMap.put(command.getRacer(), command.getPosition());
                    return Behaviors.same();
                })
                .onMessage(RacerFinishedCommand.class, command -> {
                    finishTimeMap.put(command.getRacer(), System.currentTimeMillis());
                    if (finishTimeMap.size() == 10) {
                        return finishRaceReceive();
                    } else {
                        return Behaviors.same();
                    }
                })
                .build();
    }

    public Receive<RaceController.Command> finishRaceReceive() {
        return newReceiveBuilder()
                .onMessage(GetPositionCommand.class, command -> {
                    finishTimeMap.keySet().forEach(getContext()::stop);
                    return Behaviors.withTimers(timers -> {
                        timers.cancelAll();
                        displayResults();
                        return Behaviors.stopped();
                    });
                })
                .build();
    }

    private void displayResults() {
        System.out.println("Results");
        finishTimeMap.values().stream().sorted().forEach(it -> {
            for (ActorRef<Racer.Command> racer : finishTimeMap.keySet()) {
                if (finishTimeMap.get(racer) == it) {
                    System.out.println(racer.path().name() + " finished in " + ((double) it - start) / 1000 + " seconds.");
                }
            }
        });
    }

    private void displayRace() {
        int displayLength = 160;
        for (int i = 0; i < 50; ++i) {
            System.out.println();
        }
        System.out.println("Race has been running for " + ((System.currentTimeMillis() - start) / 1000) + " seconds.");
        System.out.println("    " + new String(new char[displayLength]).replace('\0', '='));
        int i = 0;
        for (ActorRef<Racer.Command> racer : positionsMap.keySet()) {
            i++;
            System.out.println(racer.path().name()
                    + " : " + new String(new char[positionsMap.get(racer) * displayLength / 100])
                    .replace('\0', '*'));
        }
    }

    public interface Command extends Serializable {
    }

    public static class StartCommand implements Command {
        public static final long serialVersionUID = 1L;
    }

    public static class RacerUpdateCommand implements Command {
        public static final long serialVersionUID = 1L;
        private ActorRef<Racer.Command> racer;
        private int position;

        public RacerUpdateCommand(ActorRef<Racer.Command> racer, int position) {
            this.racer = racer;
            this.position = position;
        }

        public ActorRef<Racer.Command> getRacer() {
            return racer;
        }

        public int getPosition() {
            return position;
        }
    }

    private class GetPositionCommand implements Command {
        public static final long serialVersionUID = 1L;
    }

    public static class RacerFinishedCommand implements Command {
        public static final long serialVersionUID = 1L;
        private ActorRef<Racer.Command> racer;

        public RacerFinishedCommand(ActorRef<Racer.Command> racer) {
            this.racer = racer;
        }

        public ActorRef<Racer.Command> getRacer() {
            return racer;
        }
    }
}
