package com.epam.storozhuk.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import com.epam.storozhuk.domain.Event;
import com.epam.storozhuk.domain.Ticket;

public class TicketDAO {
    private Set<Ticket> tickets;

    public TicketDAO(Set<Ticket> tickets) {
        this.tickets = new LinkedHashSet<>(tickets);
    }

    public Set<Ticket> getAllPurchasedTicketsForEvent(Event event, Date date) {
        Set<Ticket> tickets = new LinkedHashSet<>();
        for (Ticket ticket : this.tickets) {
            if (ticket.getDate().equals(date) && ticket.getEvent().equals(event)) {
                tickets.add(ticket);
            }
        }
        return tickets;
    }

    public void bookTickets(Set<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            ticket.getUser().addTicket(ticket);
            this.tickets.add(ticket);
        }
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public List<Integer> getAvailableSeats(Event event, Date date) {
        ArrayList<Integer> seatsNumbers = new ArrayList<>();
        int vipSeatsCount = event.getAuditoriums().get(date).getVipSeatsCount();
        int seatsCount = event.getAuditoriums().get(date).getSeatsCount();
        int seatNum = 1;
        while (seatNum <= vipSeatsCount + seatsCount) {
            seatsNumbers.add(seatNum++);
        }
        for (Ticket ticket : tickets) {
            if (ticket.getEvent().equals(event) && ticket.getDate().equals(date)) {
                seatsNumbers.remove(ticket.getSeatNumber());
            }
        }
        return seatsNumbers;
    }

}
