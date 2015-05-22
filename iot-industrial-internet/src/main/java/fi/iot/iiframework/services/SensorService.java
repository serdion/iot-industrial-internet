/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services;

import fi.iot.iiframework.dataobject.Sensor;
import java.util.List;

public interface SensorService {
    public void add(Sensor sensor);

    public void update(Sensor sensor);

    public Sensor get(String id);

    public void delete(String id);

    public List<Sensor> getAll();
}
