package com.epam.core.after.injection.loggers;

import com.epam.core.after.injection.Event;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by Ermek_Abyshev on 3/27/2017.
 */
public class FileEventLogger implements EventLogger {

    protected File file;

    public void init() throws IOException {
        file = new File("d:\\Dev\\JAVA\\spring\\springcore\\src\\main\\resources\\test.txt");
        if (!file.canWrite()) {
            throw new IOException("No permission write to file!");
        }
    }

    public void logEvent(Event event) {
        file = new File("d:\\Dev\\JAVA\\spring\\springcore\\src\\main\\resources\\test.txt");
        try {
            FileUtils.writeStringToFile(file, event.toString(), "UTF-8", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        // do some file checks if needed
    }
}
