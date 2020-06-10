import com.epam.storozhuk.entities.CPU;
import com.epam.storozhuk.entities.Intel;
import com.epam.storozhuk.entities.VideoCard;
import com.epam.storozhuk.reflection.impl.RandomReflectionInput;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertTrue;

public class RandomReflectionInputTest {
    private RandomReflectionInput randomReflectionInput;

    @Before
    public void initRandomInput() {
        randomReflectionInput = new RandomReflectionInput();
    }

    @Test
    public void testRandomCPUCreation() {
        assertTrue(randomReflectionInput.createCPU() instanceof CPU);
    }

    @Test
    public void testRandomIntelCreation() {
        assertTrue(randomReflectionInput.createIntel() instanceof Intel);
    }

    @Test
    public void testRandomVideoCardCreation() {
        assertTrue(randomReflectionInput.createVideoCard() instanceof VideoCard);
    }
}
