import com.epam.storozhuk.entities.CPU;
import com.epam.storozhuk.entities.Intel;
import com.epam.storozhuk.entities.VideoCard;
import com.epam.storozhuk.strategy.impl.UserInputProductGenerator;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class UserInputGeneratorTest {
    @Test
    public void testSimpleCPUCreation() {
        System.setIn(new ByteArrayInputStream("2, 2500, 135.5, simpleCPU123, China\n".getBytes()));
        UserInputProductGenerator userInputProductGenerator = new UserInputProductGenerator();
        CPU receivedCPU = (CPU) userInputProductGenerator.createCPU();
        CPU expectedCPU = new CPU(2, 2500, BigDecimal.valueOf(135.5), "simpleCPU123", "China");
        assertEquals(expectedCPU, receivedCPU);
    }

    @Test
    public void testIntelCPUCreation() {
        System.setIn(new ByteArrayInputStream("2, 2500, 135.5, simpleCPU123, China, Haswell, i3\n".getBytes()));
        UserInputProductGenerator userInputProductGenerator = new UserInputProductGenerator();
        Intel receivedIntel = (Intel) userInputProductGenerator.createIntel();
        Intel expectedIntel = new Intel(2, 2500, BigDecimal.valueOf(135.5), "simpleCPU123", "China", "Haswell", "i3");
        assertEquals(expectedIntel, receivedIntel);
    }

    @Test
    public void testVideoCardCreation() {
        System.setIn(new ByteArrayInputStream("1024, 192, 205.3, superVideo228, Taiwan\n".getBytes()));
        UserInputProductGenerator userInputProductGenerator = new UserInputProductGenerator();
        VideoCard receivedVideoCard = (VideoCard) userInputProductGenerator.createVideoCard();
        VideoCard expectedVideoCard = new VideoCard(1024, 192, BigDecimal.valueOf(205.3), "superVideo228", "Taiwan");
        assertEquals(expectedVideoCard, receivedVideoCard);
    }
}
