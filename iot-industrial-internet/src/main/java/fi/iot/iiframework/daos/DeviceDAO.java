/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.daos;

import fi.iot.iiframework.dataobject.Device;
import java.util.List;

/**
 * DAO for Device
 */
public interface DeviceDAO{
    /**
     * Save the Device to Database
     * @param device device to be saved
     */
    public void save(Device device);
    /**
     * Get a Device with the given id
     * @param id id of the device
     * @return 
     */
    public Device get(String id);
    /**
     * Retrieves all Devices in the database.
     * @return 
     */
    public List<Device> getAll();
    /**
     * Removes a Device with the given id
     * @param id id of device to be removed
     */
    public void remove(String id);

    /**
     * Updates a Device with the given id
     *
     * @param device updated device
     */
    public void update(Device device);
}
