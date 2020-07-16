package com.epam.storozhuk.domain;

import java.util.Date;
import java.util.Objects;

public class Ticket {
    private User user;
    private Event event;
    private int seatNumber;
    private Date date;

    public Ticket(User user, Event event, int seatNumber, Date date) {
        this.user = user;
        this.event = event;
        this.seatNumber = seatNumber;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return getSeatNumber() == ticket.getSeatNumber() &&
                Objects.equals(getUser(), ticket.getUser()) &&
                Objects.equals(getEvent(), ticket.getEvent()) &&
                Objects.equals(getDate(), ticket.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getEvent(), getSeatNumber(), getDate());
    }
}
