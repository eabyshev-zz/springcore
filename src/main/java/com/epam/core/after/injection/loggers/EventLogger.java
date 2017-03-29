package com.epam.core.after.injection.loggers;

import com.epam.core.after.injection.Event;

/**
 * Created by Ermek_Abyshev on 3/27/2017.
 */
public interface EventLogger {

    void logEvent(Event event);
}
