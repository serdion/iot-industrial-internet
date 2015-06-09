/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.parsers;

import fi.iot.iiframework.domain.InformationSourceObject;

public interface Parser {

    /**
     * Attempts to parse data from given location to a InformationSourceObject.
     *
     * @param location Location of the data
     * @return InformationSourceObject
     */
    public InformationSourceObject parse(String location);
}
        