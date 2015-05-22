/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.errors.dao;

import fi.iot.iiframework.database.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author petri
 */
public class ErrorDAOImpl implements ErrorDAO {

    @Override
    public void save(Error error) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.persist(error);
    }

    @Override
    public Error get(String id) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Error error = (Error) s.get(Error.class, id);
        return error;
    }

    @Override
    public List<Error> getAll() {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Criteria criteria = s.createCriteria(Error.class);
        return criteria.list();
    }

    @Override
    public void remove(String id) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Error dso = get(id);
        s.delete(dso);
    }

}
