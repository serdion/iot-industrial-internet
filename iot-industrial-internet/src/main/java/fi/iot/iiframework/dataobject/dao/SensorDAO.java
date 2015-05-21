/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.dataobject.dao;

import fi.iot.iiframework.dataobject.Sensor;
import java.util.List;

/**
 * DAO for Sensor
 */
public interface SensorDAO{
    /**
     * Save the Sensor to Database
     * @param sensor sensor to be saved
     */
    public void save(Sensor sensor);
    /**
     * Get a Sensor with the given id
     * @param id id of the sensor
     * @return 
     */
    public Sensor get(String id);
    /**
     * Retrieves all Sensors in the database.
     * @return 
     */
    public List<Sensor> getAll();
    /**
     * Removes a Sensor with given id
     * @param id id of sensor to be removed
     */
    public void remove(String id);
}
