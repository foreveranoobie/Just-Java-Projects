package com.epam.storozhuk.command;

import com.epam.storozhuk.application.ApplicationContext;
import com.epam.storozhuk.entities.Order;
import com.epam.storozhuk.services.OrderService;

import java.util.Map;

public class GetOrdersListCommand implements Command {
    private OrderService orderService;

    public GetOrdersListCommand(ApplicationContext applicationContext) {
        orderService = applicationContext.getOrderService();
    }

    @Override
    public void execute() {
        Map<String, Order> orderMap = orderService.getAllOrders();
        if (orderMap.isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        orderMap.keySet().stream().forEach((str) ->
                System.out.println(str + " - " + orderMap.get(str)));
    }
}
