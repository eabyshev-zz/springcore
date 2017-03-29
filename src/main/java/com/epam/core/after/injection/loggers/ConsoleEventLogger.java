package com.epam.core.after.injection.loggers;

import com.epam.core.after.injection.Event;

/**
 * Created by Ermek_Abyshev on 3/27/2017.
 */
public class ConsoleEventLogger implements EventLogger {

    public ConsoleEventLogger() {
    }

    public void logEvent(Event event) {
        System.out.println(event.toString());
    }
}
