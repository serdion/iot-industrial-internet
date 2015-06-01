/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.errors;

import fi.iot.iiframework.errors.service.ErrorService;
import fi.iot.iiframework.services.GenericService;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Create and save new errors to database.
 *
 */
@Component
public class ErrorLogger {

    private static ErrorService gService;

    @Autowired
    private ErrorService gServiceAW;

    /**
     * Class needed to allow using Autowired-annotation in a static class
     */
    @PostConstruct
    public void ErrorLogger() {
        gService = this.gServiceAW;
    }

    /**
     * Creates a new error and calls newError to save it to database
     *
     * @param e ErrorType of error
     * @param es ErrorSeverity of error
     * 
     * @param desc Optional description
     */
    public static void newError(ErrorType e, ErrorSeverity es, String desc) {
        SysError error = new SysError(e, es, desc);
        saveError(error);
    }

    /**
     * Creates a new error and calls newError to save it to database
     *
     * @param e ErrorType of error
     * @param es ErrorSeverity of error
     * 
     */
    public static void newError(ErrorType e, ErrorSeverity es) {
        SysError error = new SysError(e, es, "no description");
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
        gService.save(error);

    }

    public static List<SysError> getAllErrors() {
        return gService.getAll();
    }

}
