/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.database;

import java.io.Serializable;

/**
 *
 * @author atte
 * @param <T>
 */
public interface Saveable<T extends Serializable> extends Serializable{
    public T getId();
    public void setId(T id);
}
