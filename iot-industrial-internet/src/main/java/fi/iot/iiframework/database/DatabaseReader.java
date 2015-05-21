/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.database;

import fi.iot.iiframework.dataobject.DataObject;

public interface DatabaseReader {
    //TODO: filters
    public DataObject readFromDb();
}
