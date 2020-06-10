package com.epam.storozhuk.command;

import com.epam.storozhuk.application.ApplicationContext;
import com.epam.storozhuk.exceptions.ApplicationException;
import com.epam.storozhuk.services.BasketService;
import com.epam.storozhuk.services.ProductService;
import com.epam.storozhuk.services.Reader;

public class AddProductToBasketCommand implements Command {
    private Reader reader;
    private ProductService productService;
    private BasketService basketService;

    public AddProductToBasketCommand(ApplicationContext applicationContext) {
        reader = applicationContext.getReaderService();
        productService = applicationContext.getProductService();
        basketService = applicationContext.getBasketService();
    }

    @Override
    public void execute() throws ApplicationException {
        int id;
        System.out.println("Enter the number of product: ");
        id = reader.nextInt();
        if (id < 1 || id > productService.getAllProducts().size()) {
            throw new ApplicationException("Your index is out of products bounds");
        }
        int count;
        System.out.println("Enter the count of products to add to the basket");
        count = reader.nextInt();
        if (count < 1) {
            throw new ApplicationException("Your index is out of products bounds");
        }
        basketService.addProductToBasket(count, productService.getProductById(id));
    }
}
