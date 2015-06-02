/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services.dataobject;

import fi.iot.iiframework.domain.Device;
import fi.iot.iiframework.domain.Sensor;
import fi.iot.iiframework.services.GenericService;
import java.util.List;

public interface SensorService extends GenericService<Sensor, Long> {

    public List<Sensor> getBy(Device device);

    public List<Sensor> getBy(int from, int to, Device device);
}
