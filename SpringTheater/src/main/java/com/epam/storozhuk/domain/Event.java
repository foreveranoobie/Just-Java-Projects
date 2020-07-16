package com.epam.storozhuk.domain;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import org.springframework.lang.Nullable;
import com.epam.storozhuk.eventrating.EventRating;

public class Event {
    private String name;
    private Map<Date, Auditorium> auditoriums;
    private double basePrice;
    private EventRating eventRating;


    public Event(String name, Map<Date, Auditorium> auditoriums, double basePrice, @Nullable EventRating eventRating) {
        this.name = name;
        this.auditoriums = new TreeMap<>(auditoriums);
        this.basePrice = basePrice;
        this.eventRating = eventRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Date, Auditorium> getAuditoriums() {
        return auditoriums;
    }

    public Set<Date> getDates() {
        return auditoriums.keySet();
    }

    public void setAuditoriums(Map<Date, Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public EventRating getEventRating() {
        return eventRating;
    }

    public void setEventRating(EventRating eventRating) {
        this.eventRating = eventRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Double.compare(event.getBasePrice(), getBasePrice()) == 0 &&
                Objects.equals(getName(), event.getName()) &&
                Objects.equals(getAuditoriums(), event.getAuditoriums()) &&
                getEventRating() == event.getEventRating();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAuditoriums(), getBasePrice(), getEventRating());
    }
}
