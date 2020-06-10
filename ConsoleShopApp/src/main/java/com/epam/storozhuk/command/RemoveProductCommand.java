package com.epam.storozhuk.command;

import com.epam.storozhuk.application.ApplicationContext;
import com.epam.storozhuk.exceptions.ApplicationException;
import com.epam.storozhuk.services.ProductService;
import com.epam.storozhuk.services.Reader;

public class RemoveProductCommand implements Command {
    private Reader reader;
    private ProductService productService;

    public RemoveProductCommand(ApplicationContext applicationContext) {
        reader = applicationContext.getReaderService();
        productService = applicationContext.getProductService();
    }

    @Override
    public void execute() throws ApplicationException {
        System.out.println("Enter the number of product to remove");
        int index = reader.nextInt();
        if (index < 0 || index > productService.getAllProducts().size()) {
            throw new ApplicationException("Index is out of products bounds");
        }
        productService.removeProduct(--index);
    }
}
