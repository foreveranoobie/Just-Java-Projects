package com.epam.storozhuk.service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import com.epam.storozhuk.domain.Event;
import com.epam.storozhuk.domain.Ticket;
import com.epam.storozhuk.domain.User;

public interface BookingService {
    double getTicketsPrice(Event event, Date date, User user, Set<Integer> seats);

    Set<Ticket> getAllPurchasedTicketsForEvent(Event event, Date date);

    void bookTickets(Set<Ticket> tickets);

    List<Integer> getAvailableSeats(Event event, Date date);
}
