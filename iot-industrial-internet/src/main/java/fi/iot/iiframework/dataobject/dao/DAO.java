/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.dataobject.dao;

import java.io.Serializable;
import java.util.List;

public interface DAO <T extends Serializable> {
    public void add(T t);
    public void update(T t);
    public List<T> list();
    public <E> T getById(E id);
    public <E> void remove(E id);
}
