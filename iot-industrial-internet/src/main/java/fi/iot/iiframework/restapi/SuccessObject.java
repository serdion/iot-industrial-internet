/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi;

import lombok.Data;

/*
 * Defines a returnable Object that will be transformed into JSON form.
 */
@Data
public class SuccessObject {

    private final String message;

    /**
     * Creates a new SuccessObject that contains single String.
     *
     * @param message Message as a String
     */
    public SuccessObject(final String message) {
        this.message = message;
    }

}
