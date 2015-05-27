/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.errors;

import fi.iot.iiframework.errors.service.ErrorService;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Create and save new errors to database.
 *
 */
@Component
public class ErrorLogger {
    
    private static ErrorService eService;

    @Autowired
    private ErrorService eServiceAW;

    
    /**
     * Class needed to allow using Autowired-annotation in a static class 
     */
    
    @PostConstruct
    public void ErrorLogger() {
        eService = this.eServiceAW;
    }

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
     *
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
//        System.out.println("eService is: " + eService);
        eService.add(error);

    }

}
