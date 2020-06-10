package com.epam.storozhuk.command;

import com.epam.storozhuk.application.ApplicationContext;
import com.epam.storozhuk.entities.Order;
import com.epam.storozhuk.services.OrderService;
import com.epam.storozhuk.services.Reader;

import java.util.Iterator;
import java.util.Map;

public class GetProductNearest implements Command {
    private Reader reader;
    private OrderService orderService;

    public GetProductNearest(ApplicationContext applicationContext) {
        reader = applicationContext.getReaderService();
        orderService = applicationContext.getOrderService();
    }

    @Override
    public void execute() {
        String date;
        do {
            System.out.println("Enter date to find the nearest date (dd-MM-yyyy hh:mm:ss)");
            date = reader.nextLine();
        } while (!isCorrectDate(date));
        Map<String, Order> nearestOrder = null;
        try {
            nearestOrder = orderService.getNearestOrder(date);
        } catch (Exception emptyListException) {
            System.out.println(emptyListException.getMessage());
            return;
        }
        Iterator<String> dateIterator = nearestOrder.keySet().iterator();
        String receivedDate = dateIterator.next();
        System.out.println(receivedDate + " " + nearestOrder.get(receivedDate));
    }

}
