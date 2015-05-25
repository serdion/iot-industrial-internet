/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.errors.service;

import java.util.List;


interface ErrorService {
    public void add(Error e);

    public void update(Error e);

    public Error get(String id);

    public void delete(String id);

    public List<Error> getAll();
}
