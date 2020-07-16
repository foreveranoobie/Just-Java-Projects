package com.epam.storozhuk.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.epam.storozhuk.domain.Event;
import com.epam.storozhuk.domain.User;
import com.epam.storozhuk.service.DiscountService;
import com.epam.storozhuk.strategy.DiscountStrategy;

public class DiscountServiceImpl implements DiscountService {
    private List<DiscountStrategy> discountStrategies;

    public DiscountServiceImpl(List<DiscountStrategy> discountStrategies) {
        this.discountStrategies = new ArrayList<>(discountStrategies);
    }

    @Override
    public double getDiscount(User user, Event event, Date dateTime, int numberOfTickets) {
        double maxDiscount = 0;
        double currentDiscount = 0;
        for (DiscountStrategy discountStrategy : discountStrategies) {
            currentDiscount = discountStrategy.calculateDiscount(user, event, dateTime, numberOfTickets);
            if (currentDiscount > maxDiscount) {
                maxDiscount = currentDiscount;
            }
        }
        return maxDiscount;
    }
}
