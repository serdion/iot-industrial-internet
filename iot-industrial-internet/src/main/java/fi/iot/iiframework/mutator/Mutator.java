/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.mutator;

import fi.iot.iiframework.domain.InformationSourceObject;

public interface Mutator {
    
    public InformationSourceObject mutate(InformationSourceObject informationSourceObject);
    
}
