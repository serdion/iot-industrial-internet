/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.errors;

import java.util.Date;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

/**
 * Create and save new errors to database. 
 *
 */
public class ErrorLogger {
    
    /**
     * 
     * @param e ErrorType
     * @param d Date
     * @param desc Optional
     */

    public static void newError(ErrorType e, Date d, String desc) {
        Error error = new Error(e,d,desc);
        saveError(error);
        
    }

    public static void newError(ErrorType e, Date d) {
        Error error = new Error(e,d);
        saveError(error);
        
    }

    private static void saveError(Error error) {

    }
    

}
