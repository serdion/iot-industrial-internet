/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services;

import fi.iot.iiframework.dataobject.DataSourceObject;
import java.util.List;

public interface DataSourceObjectService {

    public void add(DataSourceObject dso);

    public void update(DataSourceObject dso);

    public DataSourceObject get(String id);

    public void delete(String id);

    public List<DataSourceObject> getAll();
}
