package com.epam.core.after.injection;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ermek_Abyshev on 3/27/2017.
 */
public class CacheFileEventLogger extends FileEventLogger {

    private int cacheSize;
    private List<Event> cache = new ArrayList<Event>();

    public CacheFileEventLogger() {
    }

    public CacheFileEventLogger(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    @Override
    public void init() throws IOException {
        super.init();
    }

    public void destroy() {
        if (!cache.isEmpty()) {
            writeEventsFromCache();
        }
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);

        if (cache.size() == cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    private void writeEventsFromCache() {
        file = new File("d:\\Dev\\JAVA\\spring\\springcore\\src\\main\\resources\\test.txt");
        for (Event event : cache) {
            try {
                FileUtils.writeStringToFile(file, event.toString(), "UTF-8", true);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
