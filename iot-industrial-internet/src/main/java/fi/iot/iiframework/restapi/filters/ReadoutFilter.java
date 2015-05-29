/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi.filters;

import org.hibernate.criterion.Criterion;

public abstract class ReadoutFilter {
    
    private String field;

    public ReadoutFilter(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
    
    public Criterion createCriterion(String...filters) throws ArrayIndexOutOfBoundsException {
        return null;
    };
}
