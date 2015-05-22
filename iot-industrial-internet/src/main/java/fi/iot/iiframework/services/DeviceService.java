/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services;

import fi.iot.iiframework.dataobject.Device;
import java.util.List;

public interface DeviceService {
    public void add(Device device);

    public void update(Device device);

    public Device get(String id);

    public void delete(String id);

    public List<Device> getAll();
}
