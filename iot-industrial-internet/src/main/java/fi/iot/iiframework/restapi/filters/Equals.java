/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi.filters;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class Equals extends GeneralFilter {

    public Equals(String field) {
        super(field);
    }

    @Override
    public Criterion createCriterion(String... filters) throws ArrayIndexOutOfBoundsException {
        return Restrictions.eq(getField(), filters[0]);
    }

}
