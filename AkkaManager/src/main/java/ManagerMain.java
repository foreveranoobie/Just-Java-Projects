import akka.actor.typed.ActorSystem;
import akka.actor.typed.javadsl.AskPattern;
import behavior.ManagerBehavior;

import java.math.BigInteger;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.CompletionStage;

public class ManagerMain {
    public static void main(String[] args) {
        ActorSystem<ManagerBehavior.Command> manager = ActorSystem.apply(ManagerBehavior.createBehavior(), "ManagerActor");
        CompletionStage<Set<BigInteger>> result = AskPattern.ask(manager, (sender) -> new ManagerBehavior.InstructionCommand("start", sender),
                Duration.ofSeconds(20L), manager.scheduler());
        result.whenComplete((set, throwable) -> {
            if (set != null) {
                set.forEach(System.out::println);
            } else {
                System.out.println("No result has been received!");
            }
            manager.terminate();
        });

    }
}
