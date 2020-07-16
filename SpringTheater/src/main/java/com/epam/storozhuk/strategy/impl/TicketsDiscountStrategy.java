package com.epam.storozhuk.strategy.impl;

import java.util.Date;
import com.epam.storozhuk.domain.Event;
import com.epam.storozhuk.domain.User;
import com.epam.storozhuk.strategy.DiscountStrategy;

public class TicketsDiscountStrategy implements DiscountStrategy {
    public double calculateDiscount(User user, Event event, Date date, int numberOfTickets) {
        if (numberOfTickets % 10 == 0) {
            return 0.5;
        }
        return 0;
    }
}
