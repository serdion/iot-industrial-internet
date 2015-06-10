/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services.domain;

import fi.iot.iiframework.domain.Device;
import fi.iot.iiframework.domain.DeviceConfiguration;
import fi.iot.iiframework.services.GenericService;
import java.util.List;

public interface DeviceConfigurationService extends GenericService<DeviceConfiguration, String> {
    
    List<DeviceConfiguration> getBy(Device device);
    
}
