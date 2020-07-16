package com.epam.storozhuk.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;
import com.epam.storozhuk.dao.EventDAO;
import com.epam.storozhuk.dao.TicketDAO;
import com.epam.storozhuk.domain.Auditorium;
import com.epam.storozhuk.domain.Event;
import com.epam.storozhuk.domain.Ticket;
import com.epam.storozhuk.domain.User;
import com.epam.storozhuk.service.BookingService;
import com.epam.storozhuk.service.DiscountService;

public class BookingServiceImpl implements BookingService {
    private DiscountService discountService;
    private TicketDAO ticketDAO;
    private EventDAO eventDAO;

    public BookingServiceImpl(DiscountService discountService, TicketDAO ticketDAO) {
        this.discountService = discountService;
        this.ticketDAO = ticketDAO;
    }

    @Override
    public double getTicketsPrice(Event event, Date date, User user, Set<Integer> seats) {
        int ticketCount = user.getTickets().size();
        double averageDiscount = 0;
        double summaryPrice = 0;
        for (Integer seatNumber : seats) {
            averageDiscount += discountService.getDiscount(user, event, date, ticketCount++);
            summaryPrice += countSeatPrice(event, date, seatNumber);
        }
        averageDiscount /= seats.size();
        summaryPrice *= averageDiscount;
        return summaryPrice;
    }

    @Override
    public Set<Ticket> getAllPurchasedTicketsForEvent(Event event, Date date) {
        return ticketDAO.getAllPurchasedTicketsForEvent(event, date);
    }

    @Override
    public void bookTickets(Set<Ticket> tickets) {
        ticketDAO.bookTickets(tickets);
    }

    public DiscountService getDiscountService() {
        return discountService;
    }

    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }

    private double countSeatPrice(Event event, Date date, int seatNumber) {
        double price = event.getBasePrice();
        Auditorium auditorium = event.getAuditoriums().get(date);
        if (auditorium.isVipSeat(seatNumber)) {
            price *= 2;
        }
        return price;
    }

    public List<Integer> getAvailableSeats(Event event, Date date) {
        return ticketDAO.getAvailableSeats(event, date);
    }
}
