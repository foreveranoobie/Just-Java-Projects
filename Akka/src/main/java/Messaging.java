import akka.actor.typed.ActorSystem;
import behavior.FirstSimpleBehavior;

public class Messaging {
    public static void main(String... args){
        ActorSystem<String> actor = ActorSystem.create(FirstSimpleBehavior.createBehavior(), "FirstActor");
        actor.tell("say hello");
        actor.tell("who are you");
        actor.tell("Hello, world!");
        actor.tell("create a child");
    }
}
