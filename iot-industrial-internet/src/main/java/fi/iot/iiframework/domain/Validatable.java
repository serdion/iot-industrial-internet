/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.domain;

/**
 * Defines an object that can be validated.
 */
public interface Validatable {

    /**
     * Returns true if the object is valid and false if it's not.
     *
     * @return Validity as a boolean
     */
    public boolean isValid();
}
