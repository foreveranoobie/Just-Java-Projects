package com.epam.storozhuk;

import java.io.IOException;
import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.epam.storozhuk.beans.Client;
import com.epam.storozhuk.context.AppContext;
import com.epam.storozhuk.enums.EventType;
import com.epam.storozhuk.events.Event;
import com.epam.storozhuk.loggers.EventLogger;

public class App {
    private Client client;
    private EventLogger defaultLogger;
    private Event event;
    Map<EventType, EventLogger> loggers;

    public App() {
    }

    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    public static void main(String... args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);
        App app = (App) context.getBean("app");
        for (int i = 0; i < 5; i++) {
            app.logEvent(EventType.ERROR, "ERROR event for 1");
            app.logEvent(EventType.INFO, "INFO event for 1");
        }
        app.logEvent(EventType.DEFAULT, "DEFAULT event for 3");
        Thread.sleep(1000);
        app.setEvent((Event) context.getBean("event"));
        app.logEvent(EventType.DEFAULT, "DEFAULT event for 4");
        context.close();
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
