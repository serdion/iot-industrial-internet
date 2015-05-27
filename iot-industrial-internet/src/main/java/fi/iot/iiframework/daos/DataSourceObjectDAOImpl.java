/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.daos;

import fi.iot.iiframework.dataobject.DataSourceObject;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DataSourceObjectDAOImpl implements DataSourceObjectDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(DataSourceObject dso) {
        sessionFactory.getCurrentSession().persist(dso);
    }

    @Override
    public DataSourceObject get(String id) {
        return (DataSourceObject) sessionFactory.getCurrentSession().get(DataSourceObject.class, id);
    }

    @Override
    public List<DataSourceObject> getAll() {
        sessionFactory.openSession();
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DataSourceObject.class);
        return criteria.list();
    }

    @Override
    public void remove(String id) {
        DataSourceObject dso = get(id);
        sessionFactory.getCurrentSession().delete(dso);
    }

    @Override
    public void update(DataSourceObject dso) {
        DataSourceObject dsoToUpdate = get(dso.getId());
        dsoToUpdate.setDevices(dso.getDevices());
        dsoToUpdate.setHeader(dso.getHeader());
        sessionFactory.getCurrentSession().update(dsoToUpdate);
    }

}
