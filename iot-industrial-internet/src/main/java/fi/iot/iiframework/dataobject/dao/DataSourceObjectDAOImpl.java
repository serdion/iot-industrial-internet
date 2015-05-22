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
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 *
 * @author atte
 */
@Repository
public class DataSourceObjectDAOImpl implements DataSourceObjectDAO {

    @Override
    public void save(DataSourceObject dso) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(dso);
    }

    @Override
    public DataSourceObject get(String id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return (DataSourceObject) session.get(DataSourceObject.class, id);
    }

    @Override
    public List<DataSourceObject> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(DataSourceObject.class);
        return criteria.list();
    }

    @Override
    public void remove(String id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
}
