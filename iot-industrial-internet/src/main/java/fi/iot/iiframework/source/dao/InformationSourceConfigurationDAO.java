/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source.dao;

import fi.iot.iiframework.source.InformationSourceConfiguration;
import java.util.List;

public interface InformationSourceConfigurationDAO {

    public void save(InformationSourceConfiguration isc);

    public InformationSourceConfiguration get(String id);

    public List<InformationSourceConfiguration> getAll();

    public void remove(String id);

    public void update(InformationSourceConfiguration isc);

}
