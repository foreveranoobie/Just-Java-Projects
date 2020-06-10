package com.epam.storozhuk.services;

import com.epam.storozhuk.entities.Hardware;
import com.epam.storozhuk.entities.Order;
import com.epam.storozhuk.exceptions.ApplicationException;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Map;

public interface OrderService {
    String DATE_FORMAT = "dd-MM-yyyy hh:mm:ss";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);

    Map<String, Order> getNearestOrder(String newestDate) throws ApplicationException;

    Map<String, Order> getOrdersInRange(String olderDate, String newerDate) throws ApplicationException;

    Map<String, Order> getAllOrders();

    BigDecimal makeOrder(String date, Map<Hardware, Integer> basket);

    BigDecimal orderBasket(String date, Map<Hardware, Integer> basket);
}
