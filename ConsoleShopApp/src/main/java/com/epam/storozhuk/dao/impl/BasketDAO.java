package com.epam.storozhuk.dao.impl;

import com.epam.storozhuk.dao.DAO;
import com.epam.storozhuk.entities.Hardware;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class BasketDAO implements DAO {
    private static final int BASKET_ORDERS_HISTORY_COUNT = 5;
    private Map<Hardware, Integer> orderingBasket;
    private Map<Hardware, Integer> lastFiveElements;

    public BasketDAO() {
        initBasketDAO();
    }

    public void addProductToBasket(Hardware product, int count) {
        orderingBasket.put(product, count);
        updateLastFiveBasketProducts(product, count);
    }

    public void updateLastFiveBasketProducts(Hardware product, int count) {
        if (lastFiveElements.size() < BASKET_ORDERS_HISTORY_COUNT) {
            lastFiveElements.put(product, count);
            return;
        }
        Iterator<Hardware> hardware = lastFiveElements.keySet().iterator();
        if (hardware.hasNext()) {
            Hardware hardwareKey = hardware.next();
            lastFiveElements.remove(hardwareKey, lastFiveElements.get(hardwareKey));
        }
        lastFiveElements.put(product, count);
    }

    public void clearBasket() {
        orderingBasket.clear();
    }

    public Map<Hardware, Integer> getBasket() {
        return Collections.unmodifiableMap(orderingBasket);
    }

    public Map<Hardware, Integer> getLastFiveBasketProducts() {
        return Collections.unmodifiableMap(lastFiveElements);
    }

    private void initBasketDAO() {
        orderingBasket = new HashMap<>();
        lastFiveElements = new LinkedHashMap<>();
    }
}
