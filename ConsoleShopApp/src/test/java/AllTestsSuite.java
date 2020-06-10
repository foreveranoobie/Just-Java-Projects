import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ProductDAOTest.class,
        ObjectSerializationTest.class,
        ProductServiceTest.class,
        BasketServiceTest.class,
        OrderServiceTest.class,
        RandomGeneratorTest.class,
        UserInputGeneratorTest.class,
        ProductSerializerTest.class,
        CommandsTest.class,
        RandomReflectionInputTest.class,
        UserInputReflectionTest.class
})
public class AllTestsSuite {
}
