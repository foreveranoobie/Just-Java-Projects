package com.epam.storozhuk.strategy;

import java.util.Date;
import com.epam.storozhuk.domain.Event;
import com.epam.storozhuk.domain.User;

public interface DiscountStrategy {
    double calculateDiscount(User user, Event event, Date date, int numberOfTickets);
}
