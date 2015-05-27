/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services;

import fi.iot.iiframework.dataobject.DataSourceObject;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author atte
 * @param <T>
 * @param <ID>
 */
public interface GenericService<T, ID extends Serializable> {
    public void save(T t);

    public T get(ID id);

    public void delete(T t);

    public List<DataSourceObject> getAll();
}
