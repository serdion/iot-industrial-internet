/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi.exceptions;

public class ShouldBeBooleanException extends Exception {

    public ShouldBeBooleanException() {
    }

    public ShouldBeBooleanException(String message) {
        super(message);
    }

    public ShouldBeBooleanException(Throwable cause) {
        super(cause);
    }

    public ShouldBeBooleanException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
