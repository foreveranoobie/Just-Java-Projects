package com.epam.storozhuk.services.impl;

import com.epam.storozhuk.dao.impl.BasketDAO;
import com.epam.storozhuk.entities.Hardware;
import com.epam.storozhuk.services.BasketService;

import java.util.Collections;
import java.util.Map;

public class BasketServiceImpl implements BasketService {
    private BasketDAO basketDAO;

    public BasketServiceImpl(BasketDAO basketDAO) {
        this.basketDAO = basketDAO;
    }

    public Map<Hardware, Integer> getBasketProducts() {
        return Collections.unmodifiableMap(basketDAO.getBasket());
    }

    public void addProductToBasket(int count, Hardware product) {
        basketDAO.addProductToBasket(product, count);
    }

    public Map<Hardware, Integer> getLastFiveBasketProducts() {
        return Collections.unmodifiableMap(basketDAO.getLastFiveBasketProducts());
    }

    public void clearBasket() {
        basketDAO.clearBasket();
    }
}
