/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.daos.domain;

import fi.iot.iiframework.daos.GenericDAO;
import fi.iot.iiframework.domain.Device;
import fi.iot.iiframework.domain.DeviceConfiguration;
import java.util.List;

public interface DeviceConfigurationDAO extends GenericDAO<DeviceConfiguration, String> {

    public List<DeviceConfiguration> getBy(Device device);

    public List<DeviceConfiguration> getBy(int from, int to, Device device);

    public List<DeviceConfiguration> getBy(int amount, Device device);
}
