import com.epam.storozhuk.dao.impl.ProductDAO;
import com.epam.storozhuk.entities.CPU;
import com.epam.storozhuk.entities.Hardware;
import com.epam.storozhuk.entities.Intel;
import com.epam.storozhuk.entities.VideoCard;
import com.epam.storozhuk.services.ProductService;
import com.epam.storozhuk.services.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductServiceTest {
    private ProductService productService;

    @Before
    public void initService() {
        productService = new ProductServiceImpl(new ProductDAO());
        productService.addNewProduct(new CPU(0, 0, new BigDecimal(0), "0", "0"));
        productService.addNewProduct(new VideoCard(0, 0, new BigDecimal(0), "0", "0"));
        productService.addNewProduct(new Intel(0, 0, new BigDecimal(0), "0", "0", "0", "0"));
    }

    @Test
    public void testAddingProduct() {
        Intel intelCPU = new Intel(4, 3400, BigDecimal.valueOf(150), "intel123",
                "China", "Ivy Bridge", "i5");
        productService.addNewProduct(intelCPU);
        CPU simpleCPU = new CPU(2, 2200, BigDecimal.valueOf(100), "CPU111", "China");
        productService.addNewProduct(simpleCPU);
        assertEquals(intelCPU, productService.getAllProducts().get(3));
        assertEquals(simpleCPU, productService.getAllProducts().get(4));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testForUnmodifiableListReturn() {
        Intel intelCPU = new Intel(4, 3400, BigDecimal.valueOf(150), "intel123",
                "China", "Ivy Bridge", "i5");
        productService.addNewProduct(intelCPU);
        productService.getAllProducts().add(new CPU());
    }

    @Test
    public void testGettingProductsByIndex() {
        List<Hardware> productList = productService.getAllProducts();
        assertEquals(productList.get(0), productService.getProductById(1));
        assertEquals(productList.get(1), productService.getProductById(2));
    }
}

