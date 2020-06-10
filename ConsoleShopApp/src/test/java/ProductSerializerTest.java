import com.epam.storozhuk.entities.CPU;
import com.epam.storozhuk.entities.Hardware;
import com.epam.storozhuk.entities.Intel;
import com.epam.storozhuk.exceptions.ApplicationException;
import com.epam.storozhuk.util.ProductSerializer;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductSerializerTest {
    @Before
    public void setFileName(){
        ProductSerializer.setFileName("testSerialized.ser");
    }

    @Test
    public void testSerializationDeserialization(){
        List<Hardware> hardwareList = new ArrayList<>();
        hardwareList.add(new CPU(0, 0, BigDecimal.valueOf(0), "0", "0"));
        hardwareList.add(new Intel(1, 1, BigDecimal.valueOf(1), "1", "1", "1", "1"));
        try {
            ProductSerializer.serializeProduct(hardwareList, 1);
            assertEquals(hardwareList, ProductSerializer.deserializeProduct());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEmptyListOnFileNotFound(){
        ProductSerializer.setFileName("smtunknown");
        try {
            assertTrue(ProductSerializer.deserializeProduct().isEmpty());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
}
