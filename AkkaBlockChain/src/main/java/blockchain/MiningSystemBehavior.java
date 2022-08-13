package blockchain;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.SupervisorStrategy;
import akka.actor.typed.javadsl.*;

public class MiningSystemBehavior extends AbstractBehavior<ManagerBehavior.Command> {
    private PoolRouter<ManagerBehavior.Command> poolRouter;
    private ActorRef<ManagerBehavior.Command> managers;

    public MiningSystemBehavior(ActorContext<ManagerBehavior.Command> context) {
        super(context);
        poolRouter = Routers.pool(3, Behaviors.supervise(ManagerBehavior.createBehavior()).onFailure(SupervisorStrategy.restart()));
        managers = getContext().spawn(poolRouter, "poolRouter");

    }

    public static Behavior<ManagerBehavior.Command> createBehavior() {
        return Behaviors.setup(MiningSystemBehavior::new);
    }

    @Override
    public Receive<ManagerBehavior.Command> createReceive() {
        return newReceiveBuilder()
                .onAnyMessage(command -> {
                    managers.tell(command);
                    return Behaviors.same();
                })
                .build();
    }
}
