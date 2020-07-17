package com.epam.storozhuk.loggers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PreDestroy;
import com.epam.storozhuk.events.Event;

public class CachedFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache;

    public CachedFileEventLogger(String filename, int cacheSize) {
        super(filename);
        this.cacheSize = cacheSize;
        cache = new ArrayList<>(cacheSize);
    }

    @Override
    public void logEvent(Event event) throws IOException {
        if (cache.size() < cacheSize) {
            cache.add(event);
        }
        if (cacheSize == cache.size()) {
            for (Event cachedEvent : cache) {
                super.logEvent(cachedEvent);
            }
            cache = new ArrayList<>(cacheSize);
        }
    }

    @PreDestroy
    public void onCacheDestroy() throws IOException {
        System.out.println("Destroying CachedFileEventLogger");
        if (!cache.isEmpty()) {
            for (Event cachedEvent : cache) {
                super.logEvent(cachedEvent);
            }
        }
    }
}
