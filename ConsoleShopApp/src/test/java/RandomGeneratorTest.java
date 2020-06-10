import com.epam.storozhuk.entities.CPU;
import com.epam.storozhuk.entities.Intel;
import com.epam.storozhuk.entities.VideoCard;
import com.epam.storozhuk.strategy.ProductGenerator;
import com.epam.storozhuk.strategy.impl.RandomProductGenerator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RandomGeneratorTest {
    private ProductGenerator productGenerator;

    @Before
    public void initProductGenerator() {
        productGenerator = new RandomProductGenerator();
    }

    @Test
    public void testSimpleCPUCreation() {
        assertTrue(productGenerator.createCPU() instanceof CPU);
    }

    @Test
    public void testIntelCPUCreation() {
        assertTrue(productGenerator.createIntel() instanceof Intel);
    }

    @Test
    public void testVideoCardCreation() {
        assertTrue(productGenerator.createVideoCard() instanceof VideoCard);
    }
}
