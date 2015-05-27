/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.errors.dao;

import java.util.List;
import fi.iot.iiframework.errors.SysError;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ErrorDAOImpl implements ErrorDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(SysError error) {
        Session s = sessionFactory.getCurrentSession();
        s.persist(error);
    }

    @Override
    public SysError get(String id) {
        Session s = sessionFactory.getCurrentSession();
        SysError error = (SysError) s.get(SysError.class, id);
        return error;
    }

    @Override
    public List<SysError> getAll() {
        Session s = sessionFactory.getCurrentSession();
        Criteria criteria = s.createCriteria(SysError.class);
        return criteria.list();
    }

    @Override
    public void remove(String id) {
        Session s = sessionFactory.getCurrentSession();
        SysError dso = get(id);
        s.delete(dso);
    }

}
