package behavior;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class FirstSimpleBehavior extends AbstractBehavior<String> {

    private FirstSimpleBehavior(ActorContext<String> context) {
        super(context);
    }

    public static Behavior<String> createBehavior() {
        return Behaviors.setup(FirstSimpleBehavior::new);
    }

    public Receive<String> createReceive() {
        return newReceiveBuilder()
                .onMessageEquals("say hello", () -> {
                    System.out.println("Hello");
                    return this;
                })
                .onMessageEquals("who are you", () -> {
                    System.out.println(getContext().getSelf().path());
                    return this;
                })
                .onMessageEquals("create a child", () -> {
                    ActorRef<String> childActor = getContext().spawn(createBehavior(), "secondActor");
                    childActor.tell("who are you");
                    return this;
                })
                .onAnyMessage(message -> {
                    System.out.printf("Received message: %s\n", message);
                    return this;
                })
                .build();
    }
}
