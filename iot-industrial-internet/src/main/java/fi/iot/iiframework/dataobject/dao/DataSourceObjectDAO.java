/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.dataobject.dao;

import fi.iot.iiframework.dataobject.DataSourceObject;
import java.util.List;

/**
 * DAO for DataSourceObject
 */
public interface DataSourceObjectDAO {

    /**
     * Save the DataSourceObject to Database
     *
     * @param dso object to be saved
     */
    public void save(DataSourceObject dso);

    /**
     * Get a DataSourceObject with the given id
     *
     * @param id id of the object
     * @return
     */
    public DataSourceObject get(String id);

    /**
     * Retrieves all DataSourceObjects in the database.
     *
     * @return
     */
    public List<DataSourceObject> getAll();

    /**
     * Removes a DataSourceObject with given id
     *
     * @param id id of object to be removed
     */
    public void remove(String id);

    /**
     * Updates a DataSourceObject with the given id
     *
     * @param dso updated object
     */
    public void update(DataSourceObject dso);
}
