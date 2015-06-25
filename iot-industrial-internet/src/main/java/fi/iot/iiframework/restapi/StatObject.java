/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi;

import lombok.Data;

/*
 * Defines a returnable object that will be transformed into a JSON form.
 */
@Data
public class StatObject {

    private final String name;
    private final String description;
    private Object value;

    /**
     * Creates a new StatObject with given name, description and value.
     *
     * @param name Name of the object as a String
     * @param description Description of the object as a String
     * @param value Value associated with the object
     */
    public StatObject(final String name, final String description, Object value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }

}
