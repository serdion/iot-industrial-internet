/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services.domain;

import fi.iot.iiframework.domain.Device;
import fi.iot.iiframework.domain.Sensor;
import fi.iot.iiframework.services.GenericService;
import java.util.List;

public interface SensorService extends GenericService<Sensor, Long> {

    /**
     * Gets all sensors associated with the given device
     * @param device
     * @return 
     */
    public List<Sensor> getBy(Device device);

    /**
     * Gets sensors associated with the given device from the given range.
     * @param from
     * @param to
     * @param device
     * @return 
     */
    public List<Sensor> getBy(int from, int to, Device device);
    
    /**
     * Returns number of sensors associated with the given device
     * @param dev
     * @return 
     */
    public int countBy(Device dev);
    
    /**
     * Returns number of sensors
     * @return 
     */
    public int count();
}
