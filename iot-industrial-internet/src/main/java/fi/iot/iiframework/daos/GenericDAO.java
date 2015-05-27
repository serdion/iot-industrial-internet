/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.daos;

import fi.iot.iiframework.dataobject.Device;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author atte
 * @param <T>
 * @param <ID>
 */
public interface GenericDAO<T, ID extends Serializable> {

    /**
     * Save an object to database
     *
     * @param t object to be saved
     * @return 
     */
    public T save(T t);

    /**
     * Get an object with the given id
     *
     * @param id id of the object
     * @return
     */
    public T get(ID id);

    /**
     * Retrieves all objects of type T in the database.
     *
     * @return
     */
    public List<T> getAll();

    /**
     * Retrieves objects from index from to index to
     *
     * @param from
     * @param to
     * @return
     */
    public List<T> get(int from, int to);

    /**
     * Removes an object with the given id
     *
     * @param t to be removed
     */
    public void remove(T t);

}
