/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.dataobject.dao;

import fi.iot.iiframework.dataobject.Readout;
import java.util.List;

/**
 * DAO for Readout
 */
public interface ReadoutDAO{
    /**
     * Save the Readout to Database
     * @param readout readout to be saved
     */
    public void save(Readout readout);
    /**
     * Get a Readout with the given id
     * @param id id of the readout
     * @return 
     */
    public Readout get(Long id);
    /**
     * Retrieves all Readouts in the database.
     * @return 
     */
    public List<Readout> getAll();
    /**
     * Removes a Readout with given id
     * @param id id of readout to be removed
     */
    public void remove(Long id);
}
