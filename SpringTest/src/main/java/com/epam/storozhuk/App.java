package com.epam.storozhuk;

import java.io.IOException;
import java.util.Map;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.epam.storozhuk.beans.Client;
import com.epam.storozhuk.enums.EventType;
import com.epam.storozhuk.events.Event;
import com.epam.storozhuk.loggers.EventLogger;

public class App {
    private Client client;
    private EventLogger defaultLogger;
    private Event event;
    Map<EventType, EventLogger> loggers;

    public App(){}

    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    public static void main(String... args) throws InterruptedException {
        ConfigurableApplicationContext ctx =
                new ClassPathXmlApplicationContext(
                        "spring.xml");
        App app = (App) ctx.getBean("app");
        for (int i = 0; i < 5; i++) {
            app.logEvent(EventType.ERROR, "ERROR event for 1");
            app.logEvent(EventType.INFO, "INFO event for 1");
        }
        app.logEvent(EventType.DEFAULT, "DEFAULT event for 3");
        app.logEvent(EventType.DEFAULT, "DEFAULT event for 4");
        ctx.close();
    }

    private void logEvent(EventType eventType, String msg) {
        EventLogger logger = loggers.get(eventType);
        if (logger == null) {
            logger = defaultLogger;
        }
        msg.replaceAll(client.getId(), client.getFullName());
        msg += ". " + client.getGreeting();
        event.setMsg(msg);
        try {
            logger.logEvent(event);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
