package com.epam.core.after.injection.loggers;

import com.epam.core.after.injection.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ermek_Abyshev on 3/29/2017.
 */
public class CombinedEventLogger implements EventLogger {

    List<EventLogger> loggers;

    public CombinedEventLogger(List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    public void logEvent(Event event) {
        for (EventLogger logger : loggers) {
            logger.logEvent(event);
        }
    }
}
