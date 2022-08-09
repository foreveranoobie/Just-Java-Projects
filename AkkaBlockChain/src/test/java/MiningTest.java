import akka.actor.testkit.typed.CapturedLogEvent;
import akka.actor.testkit.typed.javadsl.BehaviorTestKit;
import akka.actor.testkit.typed.javadsl.TestInbox;
import blockchain.WorkerBehavior;
import model.Block;
import model.HashResult;
import org.junit.jupiter.api.Test;
import org.slf4j.event.Level;
import utils.BlocksData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MiningTest {
    @Test
    public void shouldFailWithNonceOutOfRange() {
        BehaviorTestKit<WorkerBehavior.Command> worker = BehaviorTestKit.create(WorkerBehavior.createBehavior());
        Block block = BlocksData.getNextBlock(0, "0");

        TestInbox<HashResult> controller = TestInbox.create();

        WorkerBehavior.Command command = new WorkerBehavior.Command(block, 1000, 5, controller.getRef());
        worker.run(command);
        List<CapturedLogEvent> capturedLogs = worker.getAllLogEntries();
        assertEquals(capturedLogs.size(), 1);
        assertEquals(capturedLogs.stream().findFirst().get().message(), "null");
        assertEquals(capturedLogs.stream().findFirst().get().level(), Level.DEBUG);
    }

    @Test
    void shouldPassWithNonceInRange() {
        BehaviorTestKit<WorkerBehavior.Command> testActor = BehaviorTestKit.create(WorkerBehavior.createBehavior());
        Block block = BlocksData.getNextBlock(0, "0");

        TestInbox<HashResult> controller = TestInbox.create();

        WorkerBehavior.Command message = new WorkerBehavior.Command(block, 2784000, 5, controller.getRef());
        testActor.run(message);
        List<CapturedLogEvent> logMessages = testActor.getAllLogEntries();
        assertEquals(logMessages.size(), 1);
        String expectedResult = "2784133 : 00000e557ecd72770fd680907439286648407de24e7932548b2bced8fb8ac933";
        assertEquals(expectedResult, logMessages.get(0).message());
        assertEquals(Level.DEBUG, logMessages.get(0).level());
    }

    @Test
    void shouldTellHashResultWithNonceInRange() {
        BehaviorTestKit<WorkerBehavior.Command> testActor = BehaviorTestKit.create(WorkerBehavior.createBehavior());
        Block block = BlocksData.getNextBlock(0, "0");

        TestInbox<HashResult> controller = TestInbox.create();

        WorkerBehavior.Command message = new WorkerBehavior.Command(block, 2784000, 5, controller.getRef());
        testActor.run(message);

        HashResult expectedHashResult = new HashResult();
        expectedHashResult.foundAHash("00000e557ecd72770fd680907439286648407de24e7932548b2bced8fb8ac933", 2784133);
        controller.expectMessage(expectedHashResult);
    }

    @Test
    void shouldNotTellHashResultWithNonceOutOfRange() {
        BehaviorTestKit<WorkerBehavior.Command> testActor = BehaviorTestKit.create(WorkerBehavior.createBehavior());
        Block block = BlocksData.getNextBlock(0, "0");

        TestInbox<HashResult> controller = TestInbox.create();

        WorkerBehavior.Command message = new WorkerBehavior.Command(block, 0, 5, controller.getRef());
        testActor.run(message);

        assertFalse(controller.hasMessages());
    }
}
