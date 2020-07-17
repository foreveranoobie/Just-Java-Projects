package com.epam.storozhuk.context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import com.epam.storozhuk.loggers.CachedFileEventLogger;
import com.epam.storozhuk.loggers.CombinedEventLogger;
import com.epam.storozhuk.loggers.ConsoleEventLogger;
import com.epam.storozhuk.loggers.EventLogger;
import com.epam.storozhuk.loggers.FileEventLogger;

@Configuration
public class LoggersConfig {
    @Bean
    @Lazy
    public ConsoleEventLogger consoleEventLogger() {
        return new ConsoleEventLogger();
    }

    @Bean
    @Lazy
    public FileEventLogger fileEventLogger() {
        FileEventLogger fileEventLogger = new FileEventLogger("events.txt");
        return fileEventLogger;
    }

    @Bean(name = "cachedFileEventLogger")
    public FileEventLogger cachedFileEventLogger() {
        return new CachedFileEventLogger("events.txt", 3);
    }

    @Bean
    public CombinedEventLogger combinedEventLogger() {
        List<EventLogger> loggerEvents = new ArrayList<EventLogger>(Arrays.asList(consoleEventLogger(), fileEventLogger()));
        return new CombinedEventLogger(loggerEvents);
    }
}
