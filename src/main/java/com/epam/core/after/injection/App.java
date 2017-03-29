package com.epam.core.after.injection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Ermek_Abyshev on 3/27/2017.
 */
public class App {

    private Client client;
    private EventLogger eventLogger;
    private EventLogger fileEventLogger;
    private EventLogger cacheFileEventLogger;
    private static ConfigurableApplicationContext context;

    public App() {
    }

    public App(Client client, EventLogger eventLogger, EventLogger fileEventLogger,
               EventLogger cacheFileEventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
        this.fileEventLogger = fileEventLogger;
        this.cacheFileEventLogger = cacheFileEventLogger;
    }

    private void logEvent(String msg) {
        Event event = getEvent();
        String message = msg.replace(client.getId(), client.getFullName());
        event.setMsg(message);
        eventLogger.logEvent(event);
//        fileEventLogger.logEvent(event);
        cacheFileEventLogger.logEvent(event);
    }

    public static void main(String[] args) throws InterruptedException {
        context = new ClassPathXmlApplicationContext("spring.xml");

        final App app = (App) context.getBean("app");

        app.logEvent("Some event for 1");
        Thread.sleep(1000);
        app.logEvent("Some event for 2");
        Thread.sleep(1000);
        app.logEvent("Some event for 3");
        Thread.sleep(1000);
        app.logEvent("Some event for 4");
        Thread.sleep(1000);
        app.logEvent("Some event for 5");
        Thread.sleep(1000);
        app.logEvent("Some event for 6");
        context.close();
    }

    public Event getEvent() {
        return (Event) context.getBean("event");
    }
}
