package com.epam.storozhuk.command;

import com.epam.storozhuk.application.ApplicationContext;
import com.epam.storozhuk.entities.Hardware;
import com.epam.storozhuk.services.ProductService;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ShowListCommand implements Command {
    private ProductService productService;

    public ShowListCommand(ApplicationContext applicationContext) {
        productService = applicationContext.getProductService();
    }

    @Override
    public void execute() {
        List<Hardware> storeProducts = productService.getAllProducts();
        if (storeProducts.size() == 0) {
            System.out.println("List is empty");
            return;
        }
        AtomicInteger index = new AtomicInteger(1);
        storeProducts.stream().forEach(hardware -> {
            System.out.println((index.getAndIncrement()) + " " + hardware);
        });
    }

}
