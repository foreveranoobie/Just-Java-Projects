package com.epam.storozhuk.strategy.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.epam.storozhuk.domain.Event;
import com.epam.storozhuk.domain.User;
import com.epam.storozhuk.strategy.DiscountStrategy;

public class BirthdayDiscountStrategy implements DiscountStrategy {
    public double calculateDiscount(User user, Event event, Date date, int numberOfTickets) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date birthDay = null;
        try {
            birthDay = simpleDateFormat.parse(user.getBirthday());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (birthDay.getTime() - date.getTime() <= 432_000_000 || birthDay.getTime() - date.getTime() <= -432_000_000) {
            return 0.05;
        }
        return 0;
    }
}
