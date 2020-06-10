package com.epam.storozhuk.dao.impl;

import com.epam.storozhuk.dao.DAO;
import com.epam.storozhuk.entities.Hardware;
import com.epam.storozhuk.entities.Order;
import com.epam.storozhuk.exceptions.ApplicationException;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class OrderDAO implements DAO {
    private final static String DATE_FORMAT = "dd-MM-yyyy hh:mm:ss";
    private final SimpleDateFormat simpleDateFormat;
    private Map<String, Order> orders;

    public OrderDAO() {
        orders = new TreeMap<>();
        simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
    }

    public BigDecimal createOrder(String date, Order order) {
        orders.put(date, order);
        BigDecimal price = new BigDecimal(0);
        for (Hardware element : order.getOrderProductsList().keySet()) {
            price = price.add(element.getPrice().multiply(new BigDecimal(order.getOrderProductsList().get(element))));
        }
        return price;
    }

    public BigDecimal orderBasket(String date, Order order) {
        BigDecimal price = new BigDecimal(0);
        for (Hardware element : order.getOrderProductsList().keySet()) {
            price = price.add(element.getPrice().multiply(new BigDecimal(order.getOrderProductsList().get(element))));
        }
        return price;
    }

    public Map<String, Order> getOrdersInRange(String older, String newer) throws ApplicationException {
        Map<String, Order> rangedOrdersMap = new HashMap<>();
        Date dateOlder;
        Date dateNewer;
        try {
            dateOlder = simpleDateFormat.parse(older);
            dateNewer = simpleDateFormat.parse(newer);
        } catch (ParseException dateParsingException) {
            throw new ApplicationException("Error while parsing dates from orders by simpleDateFormat");
        }
        Iterator<String> dateOrdersIterator = orders.keySet().iterator();
        String currentStringDate;
        Date currentDate;
        while (dateOrdersIterator.hasNext()) {
            currentStringDate = dateOrdersIterator.next();
            try {
                currentDate = simpleDateFormat.parse(currentStringDate);
            } catch (ParseException dateParsingException) {
                throw new ApplicationException("Error while parsing dates from orders by simpleDateFormat");
            }
            if (currentDate.compareTo(dateOlder) >= 0 && currentDate.compareTo(dateNewer) <= 0) {
                rangedOrdersMap.put(currentStringDate, orders.get(currentStringDate));
            }
        }
        return Collections.unmodifiableMap(rangedOrdersMap);
    }

    public Map<String, Order> getOrders() {
        return Collections.unmodifiableMap(orders);
    }
}
