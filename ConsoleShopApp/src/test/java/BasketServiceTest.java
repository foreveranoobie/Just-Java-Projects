import com.epam.storozhuk.dao.impl.BasketDAO;
import com.epam.storozhuk.entities.CPU;
import com.epam.storozhuk.entities.Intel;
import com.epam.storozhuk.entities.VideoCard;
import com.epam.storozhuk.services.BasketService;
import com.epam.storozhuk.services.impl.BasketServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BasketServiceTest {
    private BasketService basketService;

    @Before
    public void initBasketService() {
        basketService = new BasketServiceImpl(new BasketDAO());
        basketService.addProductToBasket(1, new Intel(0, 0, BigDecimal.valueOf(0), "0", "0", "0", "0"));
        basketService.addProductToBasket(2, new CPU(1, 1, BigDecimal.valueOf(1), "1", "1"));
        basketService.addProductToBasket(1, new VideoCard(2, 2, BigDecimal.valueOf(2), "2", "2"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiableBasketMapReturn() {
        basketService.getBasketProducts().put(new VideoCard(), 3);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiableLastFiveProducts() {
        basketService.getLastFiveBasketProducts().put(new VideoCard(), 3);
    }

    @Test
    public void testBasketCleaning() {
        basketService.clearBasket();
        assertEquals(0, basketService.getBasketProducts().size());
        assertTrue(basketService.getLastFiveBasketProducts().size() == 3);
    }
}
