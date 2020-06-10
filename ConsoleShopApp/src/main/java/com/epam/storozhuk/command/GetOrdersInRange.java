package com.epam.storozhuk.command;

import com.epam.storozhuk.application.ApplicationContext;
import com.epam.storozhuk.entities.Order;
import com.epam.storozhuk.exceptions.ApplicationException;
import com.epam.storozhuk.services.OrderService;
import com.epam.storozhuk.services.Reader;

import java.util.Iterator;
import java.util.Map;

public class GetOrdersInRange implements Command {
    private Reader reader;
    private OrderService orderService;

    public GetOrdersInRange(ApplicationContext applicationContext) {
        reader = applicationContext.getReaderService();
        orderService = applicationContext.getOrderService();
    }

    @Override
    public void execute() throws ApplicationException {
        System.out.println("Enter the older date");
        String olderDate = getDateFromUser();
        System.out.println("Enter the newer date:");
        String newerDate = getDateFromUser();
        Map<String, Order> rangedOrders = null;
        try {
            rangedOrders = orderService.getOrdersInRange(olderDate, newerDate);
        } catch (ApplicationException customException) {
            throw customException;
        }
        if (rangedOrders.isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        Iterator<String> datesIterator = rangedOrders.keySet().iterator();
        String currentIteratedDate;
        int index = 1;
        while (datesIterator.hasNext()) {
            currentIteratedDate = datesIterator.next();
            System.out.println((index++) + ". " + currentIteratedDate + " - " + rangedOrders.get(currentIteratedDate));
        }
    }

    private String getDateFromUser() {
        String date;
        do {
            date = reader.nextLine();
        } while (!isCorrectDate(date));
        return date;
    }
}
