package com.epam.storozhuk.entities;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Order {
    private Map<Hardware, Integer> orderProductsList;

    public Order() {
        orderProductsList = new LinkedHashMap<>();
    }

    public Order(Map<Hardware, Integer> copyProducts) {
        orderProductsList = new LinkedHashMap<>(copyProducts);
    }

    public Map<Hardware, Integer> getOrderProductsList() {
        return orderProductsList;
    }

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();
        Iterator<Hardware> listHardwaresIterator = orderProductsList.keySet().iterator();
        Hardware hardware;
        while (listHardwaresIterator.hasNext()) {
            hardware = listHardwaresIterator.next();
            resultString.append(hardware + ". Products count: " + orderProductsList.get(hardware) + "\n");
        }
        return resultString.toString();
    }
}
