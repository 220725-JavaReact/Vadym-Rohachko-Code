package Util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Logger {
    //Create the instance of Logger
    private static Logger single_instance_of_logger = null;

    //Create the private constructor to prevent from creating the object
    private Logger() {}

    //Create the method that will
    public static Logger getInstance() {
        if (single_instance_of_logger == null)
            //Create the object
            single_instance_of_logger = new Logger();

        return single_instance_of_logger;
    }
}
