package com.epam.storozhuk.command;

import com.epam.storozhuk.application.ApplicationContext;
import com.epam.storozhuk.services.BasketService;
import com.epam.storozhuk.services.OrderService;
import com.epam.storozhuk.services.Reader;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

public class OrderBasketCommand implements Command {
    private Reader reader;
    private OrderService orderService;
    private BasketService basketService;

    public OrderBasketCommand(ApplicationContext applicationContext) {
        reader = applicationContext.getReaderService();
        orderService = applicationContext.getOrderService();
        basketService = applicationContext.getBasketService();
    }

    @Override
    public void execute() {
        System.out.println("Enter the date (dd-mm-yyyy hh:mm:ss):");
        String date = reader.nextLine();
        if (!checkDateIsCorrect(date)) {
            System.err.println("Incorrect date format input");
            execute();
            return;
        }
        BigDecimal totalPrice = orderService.orderBasket(date, basketService.getBasketProducts());
        System.out.println("The total price of your order is: " + totalPrice);
        basketService.clearBasket();
    }

    private boolean checkDateIsCorrect(String date) {
        try {
            if (simpleDateFormat.parse(date).compareTo(new Date()) > 0) {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
