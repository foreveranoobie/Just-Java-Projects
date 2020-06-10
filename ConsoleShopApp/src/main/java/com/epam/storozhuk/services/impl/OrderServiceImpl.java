package com.epam.storozhuk.services.impl;

import com.epam.storozhuk.dao.impl.OrderDAO;
import com.epam.storozhuk.entities.Hardware;
import com.epam.storozhuk.entities.Order;
import com.epam.storozhuk.exceptions.ApplicationException;
import com.epam.storozhuk.services.OrderService;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO;

    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public Map<String, Order> getNearestOrder(String newestDate) throws ApplicationException {
        if (orderDAO.getOrders().isEmpty()) {
            return Collections.emptyMap();
        }
        String[] dates = new String[orderDAO.getOrders().size()];
        dates = orderDAO.getOrders().keySet().toArray(dates);
        Date compareDate = parse(newestDate);
        String nearestDate = dates[0];
        long lowerestDateDifferences = compareDate.getTime() - parse(nearestDate).getTime();
        lowerestDateDifferences = toUnsigned(lowerestDateDifferences);
        long currentCompare;
        for (String iteratedDate : dates) {
            currentCompare = toUnsigned(compareDate.getTime() - parse(iteratedDate).getTime());
            if (currentCompare < lowerestDateDifferences) {
                nearestDate = iteratedDate;
                lowerestDateDifferences = currentCompare;
            }
        }
        Map<String, Order> orderToReturn = new HashMap<>();
        orderToReturn.put(nearestDate, orderDAO.getOrders().get(nearestDate));
        return orderToReturn;
    }

    public Map<String, Order> getAllOrders() {
        return orderDAO.getOrders();
    }

    public BigDecimal makeOrder(String date, Map<Hardware, Integer> basket) {
        Order order = new Order(basket);
        return orderDAO.createOrder(date, order);
    }

    public BigDecimal orderBasket(String date, Map<Hardware, Integer> basket) {
        Order order = new Order(basket);
        return orderDAO.orderBasket(date, order);
    }

    public Map<String, Order> getOrdersInRange(String olderDate, String newerDate) throws ApplicationException {
        return orderDAO.getOrdersInRange(olderDate, newerDate);
    }

    private Date parse(String dateString) throws ApplicationException {
        Date date;
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new ApplicationException("Data parse exception error");
        }
        return date;
    }

    private long toUnsigned(long value) {
        return value < 0 ? value * -1 : value;
    }
}
