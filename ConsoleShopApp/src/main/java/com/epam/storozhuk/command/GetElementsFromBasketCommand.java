package com.epam.storozhuk.command;

import com.epam.storozhuk.application.ApplicationContext;
import com.epam.storozhuk.entities.Hardware;
import com.epam.storozhuk.services.BasketService;

import java.util.Map;

public class GetElementsFromBasketCommand implements Command {
    private BasketService basketService;

    public GetElementsFromBasketCommand(ApplicationContext applicationContext) {
        basketService = applicationContext.getBasketService();
    }

    @Override
    public void execute() {
        int index = 1;
        Map<Hardware, Integer> basket = basketService.getBasketProducts();
        if (basket.isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        for (Hardware element : basket.keySet()) {
            System.out.printf("%d. %s. Products count %d\n", index++, element, basket.get(element));
        }
    }
}
