package com.epam.storozhuk.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import com.epam.storozhuk.domain.Event;

public class EventDAO {
    private List<Event> eventList;

    public EventDAO(List<Event> eventList) {
        this.eventList = new ArrayList<>(eventList);
    }

    public Event getByName(String name) {
        for (Event event : eventList) {
            if (event.getName().equals(name)) {
                return event;
            }
        }
        return null;
    }

    public Set<Event> getForDateRange(Date from, Date to) {
        Set<Event> foundDates = new LinkedHashSet<>();
        for (Event event : eventList) {
            for (Date date : event.getDates()) {
                if (date.before(to) && date.after(from)) {
                    foundDates.add(event);
                    break;
                }
            }
        }
        return foundDates;
    }

    public Set<Event> getNextEvents(Date to) {
        return getForDateRange(new Date(System.currentTimeMillis()), to);
    }

    public void save(Event event) {
        if (!eventList.contains(event)) {
            eventList.add(event);
        }
    }

    public void remove(Event event) {
        eventList.remove(event);
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }
}
