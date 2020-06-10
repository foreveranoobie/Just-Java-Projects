package com.epam.storozhuk.command;

import com.epam.storozhuk.application.ApplicationContext;
import com.epam.storozhuk.entities.Hardware;
import com.epam.storozhuk.services.BasketService;

import java.util.Map;

public class GetLastFiveProductsCommand implements Command {
    private BasketService basketService;

    public GetLastFiveProductsCommand(ApplicationContext applicationContext) {
        basketService = applicationContext.getBasketService();
    }

    @Override
    public void execute() {
        Map<Hardware, Integer> fiveProducts = basketService.getLastFiveBasketProducts();
        if (fiveProducts.isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        fiveProducts.keySet()
                .iterator()
                .forEachRemaining(product -> System.out.println(product + ". Products count " + fiveProducts.get(product)));
    }
}
