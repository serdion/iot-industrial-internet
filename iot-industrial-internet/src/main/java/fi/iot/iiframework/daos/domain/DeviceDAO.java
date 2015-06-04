/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.daos.domain;

import fi.iot.iiframework.daos.GenericDAO;
import fi.iot.iiframework.domain.InformationSourceObject;
import fi.iot.iiframework.domain.Device;
import java.util.List;

/**
 * DAO for Device
 */
public interface DeviceDAO extends GenericDAO<Device, String> {

    public List<Device> getBy(InformationSourceObject dso);

    public List<Device> getBy(int from, int to, InformationSourceObject dso);
}
