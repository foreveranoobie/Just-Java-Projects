package com.epam.storozhuk.service.impl;

import java.util.Date;
import java.util.List;
import com.epam.storozhuk.dao.EventDAO;
import com.epam.storozhuk.domain.Event;
import com.epam.storozhuk.service.EventService;

public class EventServiceImpl implements EventService {
    private EventDAO eventDAO;

    public EventServiceImpl(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @Override
    public void save(Event event) {

    }

    @Override
    public void remove(Event event) {

    }

    @Override
    public Event getByName(String name) {
        return null;
    }

    @Override
    public List<Event> getAll() {
        return null;
    }

    @Override
    public List<Event> getForDateRange(Date from, Date to) {
        return null;
    }

    @Override
    public List<Event> getNextEvents(Date to) {
        return null;
    }
}
