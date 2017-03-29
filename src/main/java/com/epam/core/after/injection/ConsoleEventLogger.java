package com.epam.core.after.injection;

/**
 * Created by Ermek_Abyshev on 3/27/2017.
 */
public class ConsoleEventLogger implements EventLogger {

    public ConsoleEventLogger() {
    }

    public void logEvent(Event event) {
        System.out.println(event.toString());
        System.out.println(event.hashCode());
    }
}
