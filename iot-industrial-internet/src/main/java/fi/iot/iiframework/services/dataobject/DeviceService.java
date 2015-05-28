/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services.dataobject;

import fi.iot.iiframework.dataobject.DataSourceObject;
import fi.iot.iiframework.dataobject.Device;
import fi.iot.iiframework.dataobject.Readout;
import fi.iot.iiframework.dataobject.Sensor;
import fi.iot.iiframework.services.GenericService;
import java.util.List;

public interface DeviceService
        extends GenericService<Device, String> {
    public List<Device> getBy(DataSourceObject dso);
    public List<Device> getBy(int from, int to, DataSourceObject dso);
}
