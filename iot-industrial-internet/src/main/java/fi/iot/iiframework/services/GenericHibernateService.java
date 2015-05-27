/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services;

import fi.iot.iiframework.daos.GenericDAO;
import fi.iot.iiframework.dataobject.DataSourceObject;
import java.io.Serializable;
import java.util.List;
import javax.transaction.Transactional;

/**
 *
 * @author atte
 * @param <T>
 * @param <ID>
 */
@Transactional
public class GenericHibernateService<T, ID extends Serializable>
    implements GenericService<T, ID> {
    
    
    protected GenericDAO dao;

    @Override
    public void save(T t) {
        dao.save(t);
    }

    @Override
    public T get(ID id) {
        return (T) dao.get(id);
    }

    @Override
    public void delete(T t) {
        dao.remove(t);
    }

    @Override
    public List<T> getAll() {
        return dao.getAll();
    }
    
}
