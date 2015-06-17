/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.mutator;

import fi.iot.iiframework.domain.InformationSource;
import fi.iot.iiframework.domain.Sensor;
import java.util.List;

public interface Mutator {

    public void mutateAll(List<Sensor> sensors);
    public void mutateAll(InformationSource source);
}
