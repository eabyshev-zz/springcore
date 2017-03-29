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
    private Event event;
    private EventLogger fileEventLogger;
    private EventLogger cacheFileEventLogger;

    public App() {
    }

    public App(Client client, EventLogger eventLogger, Event event, EventLogger fileEventLogger,
               EventLogger cacheFileEventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
        this.event = event;
        this.fileEventLogger = fileEventLogger;
        this.cacheFileEventLogger = cacheFileEventLogger;
    }

    private void logEvent(String msg) {
        String message = msg.replace(client.getId(), client.getFullName());
        event.setMsg(message);
        eventLogger.logEvent(event);
//        fileEventLogger.logEvent(event);
        cacheFileEventLogger.logEvent(event);
    }

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        final App app = (App) ctx.getBean("app");
//        Thread.sleep(1000);
        final App app2 = (App) ctx.getBean("app");
//        Thread.sleep(1000);
        final App app3 = (App) ctx.getBean("app");
//        Thread.sleep(1000);
        final App app4 = (App) ctx.getBean("app");
//        Thread.sleep(1000);
        final App app5 = (App) ctx.getBean("app");

        app.logEvent("Some event for 1");
        Thread.sleep(1000);
        app2.logEvent("Some event for 2");
        Thread.sleep(1000);
        app3.logEvent("Some event for 3");
        Thread.sleep(1000);
        app4.logEvent("Some event for 4");
        Thread.sleep(1000);
        app5.logEvent("Some event for 5");
        ctx.close();
    }
}
