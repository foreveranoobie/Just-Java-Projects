import com.epam.storozhuk.dao.impl.ProductDAO;
import com.epam.storozhuk.entities.CPU;
import com.epam.storozhuk.entities.Intel;
import com.epam.storozhuk.entities.VideoCard;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ProductDAOTest {
    private ProductDAO productDAO;

    @Before
    public void initProducts() {
        productDAO = new ProductDAO();
        productDAO.addProduct(new CPU(0, 0, BigDecimal.valueOf(0), "0", "0"));
        productDAO.addProduct(new VideoCard(1, 1, BigDecimal.valueOf(1), "1", "1"));
        productDAO.addProduct(new Intel(2, 2, BigDecimal.valueOf(2), "2", "2", "2", "2"));
    }

    @Test
    public void testRemovingProduct() {
        productDAO.removeProduct(0);
        assertEquals(new VideoCard(1, 1, BigDecimal.valueOf(1), "1", "1"), productDAO.getProducts().get(0));
        assertEquals(2, productDAO.getProducts().size());
        assertEquals(new Intel(2, 2, BigDecimal.valueOf(2), "2", "2", "2", "2"),
                productDAO.removeProduct(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testForOutOfBoundsInRemoving() {
        productDAO.removeProduct(productDAO.getProducts().size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testForUnmodifiableListException() {
        productDAO.getProducts().remove(1);
    }
}
