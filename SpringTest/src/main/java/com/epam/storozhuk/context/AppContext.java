package com.epam.storozhuk.context;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import com.epam.storozhuk.App;
import com.epam.storozhuk.beans.Client;
import com.epam.storozhuk.enums.EventType;
import com.epam.storozhuk.events.Event;
import com.epam.storozhuk.loggers.EventLogger;
import com.epam.storozhuk.loggers.FileEventLogger;

@Configuration
@ComponentScan
@Import(LoggersConfig.class)
@PropertySource("classpath:client.properties")
public class AppContext {
    @Value("${id}")
    private String id;
    @Value("${fullName}")
    private String fullName;
    @Value("${greeting}")
    private String greeting;
    @Value("${info}")
    private String info;
    @Value("${error}")
    private String error;
    @Autowired
    @Qualifier("consoleEventLogger")
    private EventLogger consoleEventLogger;
    @Autowired
    @Qualifier("combinedEventLogger")
    private EventLogger combinedEventLogger;
    @Autowired
    @Qualifier("cachedFileEventLogger")
    private FileEventLogger cachedFileEventLogger;

    @Bean
    public Client client() {
        return new Client(id, fullName, greeting);
    }

    @Bean
    @Scope("prototype")
    public Event event() {
        return new Event(new Date(), new SimpleDateFormat("YYYY-MM-DD hh:mm:ss"));
    }

    @Bean
    public App app() {
        Map<EventType, EventLogger> eventLoggerMap = new HashMap<>();
        eventLoggerMap.put(EventType.INFO, consoleEventLogger);
        eventLoggerMap.put(EventType.ERROR, combinedEventLogger);
        App app = new App(client(), cachedFileEventLogger, eventLoggerMap);
        app.setEvent(event());
        return app;
    }
}
