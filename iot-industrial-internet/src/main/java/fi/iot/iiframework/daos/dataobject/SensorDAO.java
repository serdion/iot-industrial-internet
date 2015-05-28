/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.daos.dataobject;

import fi.iot.iiframework.daos.GenericDAO;
import fi.iot.iiframework.dataobject.Device;
import fi.iot.iiframework.dataobject.Sensor;
import java.util.List;

/**
 * DAO for Sensor
 */
public interface SensorDAO extends GenericDAO<Sensor, String> {

    public List<Sensor> getBy(Device device);

    public List<Sensor> getBy(int from, int to, Device device);
}
