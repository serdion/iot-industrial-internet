/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.errors;

import fi.iot.iiframework.errors.service.ErrorService;
import java.util.Date;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

/**
 * Create and save new errors to database.
 *
 */
public class ErrorLogger {

    @Autowired
    static ErrorService eService;

    /**
     * Creates a new error and calls newError to save it to database
     *
     * @param e ErrorType
     * @param d Date
     * @param desc Optional description
     */
    public static void newError(ErrorType e, Date d, String desc) {
        SysError error = new SysError(e, d, desc);
        saveError(error);

    }

    /**
     * Creates a new error and calls newError to save it to database
     *
     * @param e ErrorType
     * @param d Date
     */
    public static void newError(ErrorType e, Date d) {
        SysError error = new SysError(e, d);
        saveError(error);

    }
    
    /**
     * Saves a predefined SysError to the database
     * @param error 
     */

    public static void newError(SysError error) {
        saveError(error);

    }

    /**
     * Uses ErrorService to save SysError to database
     *
     * @param error SysError to be saved
     */
    private static void saveError(SysError error) {
        eService.add(error);

    }

}
