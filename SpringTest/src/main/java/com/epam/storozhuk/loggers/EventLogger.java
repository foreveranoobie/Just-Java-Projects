package com.epam.storozhuk.loggers;

import java.io.IOException;
import com.epam.storozhuk.events.Event;

public interface EventLogger {
    void logEvent(Event event) throws IOException;
}
