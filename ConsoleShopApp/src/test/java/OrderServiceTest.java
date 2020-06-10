import com.epam.storozhuk.dao.impl.OrderDAO;
import com.epam.storozhuk.entities.CPU;
import com.epam.storozhuk.entities.Hardware;
import com.epam.storozhuk.entities.Order;
import com.epam.storozhuk.entities.VideoCard;
import com.epam.storozhuk.exceptions.ApplicationException;
import com.epam.storozhuk.services.OrderService;
import com.epam.storozhuk.services.impl.OrderServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OrderServiceTest {
    private OrderService orderService;

    @Before
    public void initOrderService() {
        orderService = new OrderServiceImpl(new OrderDAO());
        Map<Hardware, Integer> basketMaps = new HashMap<>();
        basketMaps.put(new CPU(0, 0, BigDecimal.valueOf(0), "0", "0"), 1);
        orderService.makeOrder("22-01-2020 10:10:10", basketMaps);
        basketMaps.clear();
        basketMaps.put(new VideoCard(1, 1, BigDecimal.valueOf(1), "1", "1"), 2);
        orderService.makeOrder("10-06-2019 15:40:31", basketMaps);
    }

    @Test
    public void testMakingOrder() {
        Map<Hardware, Integer> basketMaps = new HashMap<>();
        basketMaps.put(new CPU(3, 3, BigDecimal.valueOf(3), "3", "3"), 1);
        orderService.makeOrder("24-05-2018 12:30:00", basketMaps);
        assertTrue(orderService.getAllOrders().size() == 3);
    }

    @Test
    public void testSizeNotChangedAfterBasketOrder() {
        Map<Hardware, Integer> basketMaps = new HashMap<>();
        basketMaps.put(new CPU(3, 3, BigDecimal.valueOf(3), "3", "3"), 1);
        orderService.orderBasket("24-05-2018 12:30:00", basketMaps);
        assertTrue(orderService.getAllOrders().size() == 2);
    }

    @Test
    public void testRangedOrders() {
        Map<String, Order> ordersMap = null;
        try {
            ordersMap = orderService.getOrdersInRange("10-06-2019 00:00:00", "25-07-2019 00:00:00");
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        assertEquals(1, ordersMap.size());
        assertEquals("[10-06-2019 15:40:31]", ordersMap.keySet().toString());
    }

    @Test
    public void testGettingNearest() {
        Map<String, Order> ordersMap = null;
        try {
            ordersMap = orderService.getNearestOrder("10-10-2019 00:00:00");
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        assertEquals("[22-01-2020 10:10:10]", ordersMap.keySet().toString());
    }
}
