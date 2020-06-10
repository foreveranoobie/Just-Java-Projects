package com.epam.storozhuk.command;

import com.epam.storozhuk.application.ApplicationContext;
import com.epam.storozhuk.reflection.impl.RandomReflectionInput;
import com.epam.storozhuk.util.ProductSerializer;

import java.util.HashMap;
import java.util.Map;

public final class CommandContainer {
    private final Map<String, Command> commands;

    public CommandContainer(ApplicationContext applicationContext) {
        commands = new HashMap<>();
        commands.put("0", () -> {
            System.out.println("Enter the number of times of Products to be serialized");
            int loopCount = applicationContext.getReaderService().nextInt();
            ProductSerializer.serializeProduct(applicationContext.getProductService().getAllProducts(), loopCount);
            System.exit(0);
        });
        commands.put("1", new ShowListCommand(applicationContext));
        commands.put("2", new AddProductCommand(applicationContext));
        commands.put("3", new RemoveProductCommand(applicationContext));
        commands.put("4", new AddProductToBasketCommand(applicationContext));
        commands.put("5", new GetElementsFromBasketCommand(applicationContext));
        commands.put("6", new MakeOrderCommand(applicationContext));
        commands.put("7", new OrderBasketCommand(applicationContext));
        commands.put("8", new GetLastFiveProductsCommand(applicationContext));
        commands.put("9", new GetOrdersListCommand(applicationContext));
        commands.put("10", new GetOrdersInRange(applicationContext));
        commands.put("11", new GetProductNearest(applicationContext));
        commands.put("12", new RandomReflectionProductCommand(applicationContext));
        commands.put("13", new UserReflectionProductCommand(applicationContext));
    }

    public Command getCommand(String number) {
        return commands.get(number);
    }
}
