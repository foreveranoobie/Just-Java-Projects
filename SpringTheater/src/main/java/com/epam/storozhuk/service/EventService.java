package com.epam.storozhuk.service;

import java.util.Date;
import java.util.List;
import com.epam.storozhuk.domain.Event;

public interface EventService {
    void save(Event event);

    void remove(Event event);

    Event getByName(String name);

    List<Event> getAll();

    List<Event> getForDateRange(Date from, Date to);

    List<Event> getNextEvents(Date to);
}
