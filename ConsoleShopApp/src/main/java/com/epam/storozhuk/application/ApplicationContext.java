package com.epam.storozhuk.application;

import com.epam.storozhuk.dao.impl.BasketDAO;
import com.epam.storozhuk.dao.impl.OrderDAO;
import com.epam.storozhuk.dao.impl.ProductDAO;
import com.epam.storozhuk.exceptions.ApplicationException;
import com.epam.storozhuk.reflection.ReflectionInput;
import com.epam.storozhuk.services.BasketService;
import com.epam.storozhuk.services.OrderService;
import com.epam.storozhuk.services.ProductService;
import com.epam.storozhuk.services.Reader;
import com.epam.storozhuk.services.impl.BasketServiceImpl;
import com.epam.storozhuk.services.impl.KeyboardReader;
import com.epam.storozhuk.services.impl.OrderServiceImpl;
import com.epam.storozhuk.services.impl.ProductServiceImpl;
import com.epam.storozhuk.strategy.ProductGenerator;
import com.epam.storozhuk.util.ProductSerializer;

import java.util.Locale;
import java.util.ResourceBundle;

public class ApplicationContext {
    private ProductService productService;
    private BasketService basketService;
    private OrderService orderService;
    private OrderDAO orderDAO;
    private ProductDAO productDAO;
    private BasketDAO basketDAO;
    private Reader keyReader;
    private ProductGenerator productGenerator;
    private ResourceBundle vocabulary;
    private ReflectionInput reflectionInput;

    public ApplicationContext() throws ApplicationException {
        orderDAO = new OrderDAO();
        productDAO = new ProductDAO(ProductSerializer.deserializeProduct());
        basketDAO = new BasketDAO();
        productService = new ProductServiceImpl(productDAO);
        basketService = new BasketServiceImpl(basketDAO);
        orderService = new OrderServiceImpl(orderDAO);
        keyReader = new KeyboardReader();
    }

    public void setReflectionInput(ReflectionInput reflectionInput) {
        this.reflectionInput = reflectionInput;
    }

    public ReflectionInput getReflectionInput() {
        return reflectionInput;
    }

    public ResourceBundle getBundle() {
        return vocabulary;
    }

    public void setBundle(String localeName) {
        vocabulary = ResourceBundle.getBundle("words", new Locale(localeName));
    }

    public ProductGenerator getProductGenerator() {
        return productGenerator;
    }

    public void setProductGenerator(ProductGenerator productGenerator) {
        this.productGenerator = productGenerator;
    }

    public Reader getReaderService() {
        return keyReader;
    }

    public ProductService getProductService() {
        return productService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public BasketService getBasketService() {
        return basketService;
    }
}
