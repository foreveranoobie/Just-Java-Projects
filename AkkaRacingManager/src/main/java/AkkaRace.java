import akka.actor.typed.ActorSystem;
import behavior.RaceController;

public class AkkaRace {
    public static void main(String[] args) {
        ActorSystem<RaceController.Command> raceController = ActorSystem.create(RaceController.createBehavior(),
                "RaceController");
        raceController.tell(new RaceController.StartCommand());
    }
}
