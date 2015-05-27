/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.errors.dao;

import java.util.List;
import fi.iot.iiframework.errors.SysError;

/**
 * DAO for SysError
 *
 */
public interface ErrorDAO {
    
    /**
     * Save error to database
     * @param error 
     */

    public void save(SysError error);
    
    /**
     * Get error from database based on id
     * @param id of the error
     * @return 
     */

    public SysError get(String id);
    
    /**
     * Get a list of errors in database
     * @return 
     */

    public List<SysError> getAll();
    
    /**
     * Remove an error from database
     * @param id of the error
     */

    public void remove(String id);

}
