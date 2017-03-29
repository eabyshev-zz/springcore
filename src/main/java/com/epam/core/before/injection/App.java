package com.epam.core.before.injection;

/**
 * Created by Ermek_Abyshev on 3/27/2017.
 */
public class App {

    private Client client;
    private ConsoleEventLogger eventLogger;

    private void logEvent(String msg) {
        String message = msg.replace(client.getId(), client.getFullName());
        eventLogger.logEvent(message);
    }

    public static void main(String[] args) {
        App app = new App();

        app.client = new Client("1", "John Smith");
        app.eventLogger = new ConsoleEventLogger();

        app.logEvent("Some event for user 1");
    }
}
