package com.epam.storozhuk.services;

import com.epam.storozhuk.entities.Hardware;

import java.util.Map;

public interface BasketService {
    Map<Hardware, Integer> getBasketProducts();

    void addProductToBasket(int count, Hardware product);

    Map<Hardware, Integer> getLastFiveBasketProducts();

    void clearBasket();
}
