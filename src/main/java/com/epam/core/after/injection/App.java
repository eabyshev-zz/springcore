package com.epam.core.after.injection;

import com.epam.core.after.injection.loggers.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Ermek_Abyshev on 3/27/2017.
 */
public class App {

    // inject with constructor
    private Client client;
    private EventLogger cacheFileEventLogger;
    private Map<EventType, EventLogger> loggers;
    private static ConfigurableApplicationContext context;

    // inject const
    private String constanta;

    // inject property
    private String test;

    public App() {
    }

    public App(Client client, EventLogger cacheFileEventLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.cacheFileEventLogger = cacheFileEventLogger;
        this.loggers = loggers;
    }

    private void logEvent(String msg, EventType type) {
        Event event = getEvent();
        String message = msg.replace(client.getId(), client.getFullName());
        event.setMsg(message);
        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = cacheFileEventLogger;
        }
        logger.logEvent(event);

    }

    public static void main(String[] args) throws InterruptedException {
        context = new ClassPathXmlApplicationContext("spring.xml");

        final App app = (App) context.getBean("app");

        app.logEvent("Some event for 1", EventType.INFO);
        Thread.sleep(1000);
        app.logEvent("Some event for 2", EventType.OTHER);
        Thread.sleep(1000);
        app.logEvent("Some event for 3", EventType.OTHER);
        Thread.sleep(1000);
        app.logEvent("Some event for 4", EventType.INFO);
        Thread.sleep(1000);
        app.logEvent("Some event for 5", EventType.ERROR);
        Thread.sleep(1000);

        // print const
        System.out.println(app.constanta);

        context.close();
    }

    public Event getEvent() {
        return (Event) context.getBean("event");
    }

    public void setTest(String test) {
        this.test = test;
    }

    public void setConstanta(String  constanta) {
        this.constanta = constanta;
    }
}
