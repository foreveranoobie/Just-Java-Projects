package com.epam.storozhuk.loggers;

import com.epam.storozhuk.events.Event;

public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event event) {
        System.out.println(event.toString());
    }
}
