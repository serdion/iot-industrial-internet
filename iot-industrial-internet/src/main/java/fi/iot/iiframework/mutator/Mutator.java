/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.mutator;

import fi.iot.iiframework.domain.Sensor;

public interface Mutator {

    /**
     * Mutates all readouts found in a single sensor.
     *
     * @param sensor Sensor to mutate
     * @see Sensor
     */
    public void mutateAll(Sensor sensor);

}
