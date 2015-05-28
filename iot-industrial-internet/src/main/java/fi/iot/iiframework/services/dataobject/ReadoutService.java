/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services.dataobject;

import fi.iot.iiframework.dataobject.Readout;
import fi.iot.iiframework.dataobject.Sensor;
import fi.iot.iiframework.services.GenericService;
import java.util.List;

public interface ReadoutService extends GenericService<Readout, Long> {
    public List<Readout> getBy(Sensor sensor);
    public List<Readout> getBy(int from, int to, Sensor sensor);
}