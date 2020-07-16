package com.epam.storozhuk.loggers;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import com.epam.storozhuk.events.Event;

public class FileEventLogger implements EventLogger {
    private String filename;
    private File file;

    public FileEventLogger(String filename) {
        this.filename = filename;
    }

    public void init() {
        file = new File(filename);
    }

    public void logEvent(Event event) throws IOException {
        FileUtils.writeStringToFile(file, event.toString() + System.lineSeparator(), "utf-8", true);
    }
}
