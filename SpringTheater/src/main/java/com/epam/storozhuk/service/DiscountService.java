package com.epam.storozhuk.service;

import java.util.Date;
import com.epam.storozhuk.domain.Event;
import com.epam.storozhuk.domain.User;

public interface DiscountService {
    double getDiscount(User user, Event event, Date dateTime, int numberOfTickets);
}
