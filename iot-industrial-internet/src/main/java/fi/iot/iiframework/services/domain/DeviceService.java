/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services.domain;

import fi.iot.iiframework.domain.DataSourceObject;
import fi.iot.iiframework.domain.Device;
import fi.iot.iiframework.domain.Readout;
import fi.iot.iiframework.domain.Sensor;
import fi.iot.iiframework.services.GenericService;
import java.util.List;

public interface DeviceService
        extends GenericService<Device, Long> {
    public List<Device> getBy(DataSourceObject dso);
    public List<Device> getBy(int from, int to, DataSourceObject dso);
}
