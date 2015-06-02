/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi.exceptions;

public class InvalidParametersException extends Exception {

    public InvalidParametersException() {
    }

    public InvalidParametersException(String message) {
        super(message);
    }

    public InvalidParametersException(Throwable cause) {
        super(cause);
    }

    public InvalidParametersException(String message, Throwable cause) {
        super(message, cause);
    }

}
