import com.epam.storozhuk.entities.CPU;
import com.epam.storozhuk.entities.Intel;
import com.epam.storozhuk.entities.VideoCard;
import com.epam.storozhuk.reflection.impl.UserReflectionInput;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.Assert.assertEquals;

public class UserInputReflectionTest {
    private ResourceBundle ruBundle;
    private String newLine;

    @Before
    public void initTestFields() {
        ruBundle = ResourceBundle.getBundle("words", new Locale("ru"));
        newLine = "\n";
    }

    @Test
    public void testUserInputCPU() {
        int expectedCoresCount = 2;
        int expectedFrequency = 3000;
        BigDecimal expectedPrice = new BigDecimal(150.25);
        String expectedArticle = "CPU33333";
        String expectedCountry = "China";
        CPU expectedCPU = new CPU(expectedCoresCount, expectedFrequency, expectedPrice, expectedArticle, expectedCountry);
        String inputAnswer = expectedCoresCount + newLine + expectedFrequency + newLine + expectedPrice + newLine +
                expectedArticle + newLine + expectedCountry + newLine;
        System.setIn(new ByteArrayInputStream(inputAnswer.getBytes()));
        UserReflectionInput userReflectionInput = new UserReflectionInput();
        userReflectionInput.setVocabulary(ruBundle);
        CPU realCPU = (CPU) userReflectionInput.createCPU();
        assertEquals(expectedCPU, realCPU);
    }

    @Test
    public void testUserInputIntel() {
        String expectedGeneration = "Haswell";
        String expectedModel = "i3";
        int expectedCoresCount = 2;
        int expectedFrequency = 3000;
        BigDecimal expectedPrice = new BigDecimal(150.25);
        String expectedArticle = "CPU33333";
        String expectedCountry = "China";
        Intel expectedIntel = new Intel(expectedCoresCount, expectedFrequency, expectedPrice, expectedArticle, expectedCountry, expectedGeneration, expectedModel);
        String inputAnswer = "Haswell\ni3\n2\n3000\n150.25\nCPU33333\nChina\n";
        System.setIn(new ByteArrayInputStream(inputAnswer.getBytes()));
        UserReflectionInput userReflectionInput = new UserReflectionInput();
        userReflectionInput.setVocabulary(ruBundle);
        Intel realIntel = (Intel) userReflectionInput.createIntel();
        assertEquals(expectedIntel, realIntel);
    }

    @Test
    public void testUserInputVideoCard() {
        int expectedMemSize = 1024;
        int expectedBusMemory = 128;
        BigDecimal expectedPrice = BigDecimal.valueOf(202.9);
        String expectedArticle = "VideoSuper2000";
        String expectedCountry = "Taiwan";
        VideoCard expectedVideoCard = new VideoCard(expectedMemSize, expectedBusMemory, expectedPrice, expectedArticle, expectedCountry);
        String inputAnswer = "1024\n128\n202.9\nVideoSuper2000\nTaiwan\n";
        System.setIn(new ByteArrayInputStream(inputAnswer.getBytes()));
        UserReflectionInput userReflectionInput = new UserReflectionInput();
        userReflectionInput.setVocabulary(ruBundle);
        VideoCard realVideoCard = (VideoCard) userReflectionInput.createVideoCard();
        assertEquals(expectedVideoCard, realVideoCard);
    }

}
