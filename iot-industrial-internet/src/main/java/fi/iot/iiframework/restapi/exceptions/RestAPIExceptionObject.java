/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi.exceptions;

import fi.iot.iiframework.errors.ErrorType;

public class RestAPIExceptionObject {

    protected ErrorType type;

    protected String message;

    /**
     * Defines the object that is returned from RestAPI in case of an error.
     */
    public RestAPIExceptionObject() {
        setType(ErrorType.UNKNOWN_ERROR);
        setMessage("Unknown error has occured.");
    }

    /**
     * Defines the object that is returned from RestAPI in case of an error.
     *
     * @param type Type of the error
     * @param message Message included in the error
     */
    public RestAPIExceptionObject(ErrorType type, String message) {
        this.type = type;
        this.message = message;
    }

    public ErrorType getType() {
        return type;
    }

    public void setType(ErrorType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return getType().toString() + ": " + getMessage();
    }

}
