/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.errors;

import fi.iot.iiframework.services.errors.ErrorService;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Create and save new errors to database.
 */
@Component
public class ErrorLogger {

    private static ErrorService staticErrorService;

    @Autowired
    private ErrorService errorService;

    /**
     * Class needed to allow using Autowired -annotation in a static class
     */
    @PostConstruct
    public void ErrorLogger() {
        staticErrorService = this.errorService;
    }

    /**
     * Creates a new error and calls newError to save it to database
     *
     * @param type ErrorType of error
     * @param severity ErrorSeverity of error
     * @param desc Description of error
     * @param additionalInformation Additional information about this error.
     */
    public static void log(ErrorType type, ErrorSeverity severity, String desc, String additionalInformation) {
        SysError error = new SysError(type, severity, trimString(desc));
        error.setAdditionalInformation(trimString(additionalInformation));
        saveError(error);
    }

    /**
     * Creates a new error and calls newError to save it to database with
     * location obtained from the stack trace.
     *
     * @param type ErrorType of error
     * @param severity ErrorSeverity of error
     * @param desc Optional description
     *
     * @see ErrorType
     * @see ErrorSeverity
     */
    public static void log(ErrorType type, ErrorSeverity severity, String desc) {
        SysError error = new SysError(type, severity, trimString(desc));

        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        error.setLocation(stackTraceElements[2].toString());

        saveError(error);
    }

    /**
     * Saves a predefined SysError to the database.
     *
     * @param error Error to add
     * @see SysError
     */
    public static void log(SysError error) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        error.setLocation(stackTraceElements[2].toString());
        error.setDescription(trimString(error.getDescription()));
        error.setAdditionalInformation(trimString(error.getAdditionalInformation()));
        saveError(error);
    }

    /**
     * Uses ErrorService to save SysError to database.
     *
     * @param error SysError to be saved
     */
    private static void saveError(SysError error) {
        staticErrorService.save(error);
    }

    /**
     * Returns all systems found in the ErrorService as a list.
     *
     * @return List of SysErrors
     * @see SysError
     */
    public static List<SysError> getAllErrors() {
        return staticErrorService.getAll();
    }

    private static String trimString(String string) {
        if (string == null || string.length() < 2) {
            string = "NaN";
        }

        return string.trim();
    }

}
