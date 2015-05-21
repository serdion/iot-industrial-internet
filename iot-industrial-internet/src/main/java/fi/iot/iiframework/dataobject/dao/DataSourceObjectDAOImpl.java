/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.dataobject.dao;

import fi.iot.iiframework.database.HibernateUtil;
import fi.iot.iiframework.dataobject.DataSourceObject;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author atte
 */
public class DataSourceObjectDAOImpl implements DataSourceObjectDAO {

    @Override
    public void save(DataSourceObject dbo) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(dbo);
    }

    @Override
    public DataSourceObject get(String id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return null;
    }

    @Override
    public List<DataSourceObject> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return null;
    }

    @Override
    public void remove(String id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
}
