/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.errors.service;

import fi.iot.iiframework.errors.SysError;
import java.util.List;

public interface ErrorService {

    public void add(SysError e);

    public SysError get(String id);

    public void delete(String id);

    public List<SysError> getAll();

}
