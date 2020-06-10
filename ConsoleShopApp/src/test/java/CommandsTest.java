import com.epam.storozhuk.application.ApplicationContext;
import com.epam.storozhuk.command.CommandContainer;
import com.epam.storozhuk.exceptions.ApplicationException;
import com.epam.storozhuk.strategy.impl.RandomProductGenerator;
import com.epam.storozhuk.util.ProductSerializer;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

public class CommandsTest {
    private CommandContainer commandContainer;
    private ApplicationContext applicationContext;

    @Before
    public void initContainerAndContext() {
        ProductSerializer.setFileName("test123.ser");
        try {
            applicationContext = new ApplicationContext();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        applicationContext.setProductGenerator(new RandomProductGenerator());
        commandContainer = new CommandContainer(applicationContext);
    }

    @Test(expected = ApplicationException.class)
    public void testExceptionOnIndexOutOfBounds() throws ApplicationException {
        System.setIn(new ByteArrayInputStream("6\n".getBytes()));
        applicationContext = new ApplicationContext();
        applicationContext.setProductGenerator(new RandomProductGenerator());
        commandContainer = new CommandContainer(applicationContext);
        commandContainer.getCommand("4").execute();
    }
}
