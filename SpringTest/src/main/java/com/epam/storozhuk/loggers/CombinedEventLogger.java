package com.epam.storozhuk.loggers;

import java.io.IOException;
import java.util.Collection;
import com.epam.storozhuk.events.Event;

public class CombinedEventLogger implements EventLogger {
    private Collection<EventLogger> loggers;

    public CombinedEventLogger(Collection<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) throws IOException {
        for (EventLogger logger : loggers) {
            logger.logEvent(event);
        }
    }
}
