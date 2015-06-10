/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.mutator.cases;

import fi.iot.iiframework.domain.Readout;

public class ReadoutValueIsHigher implements Case {
    
    private Readout readout;
    private double value;

    @Override
    public boolean isTrue() {
        return readout.getValue() > value;
    }

}
