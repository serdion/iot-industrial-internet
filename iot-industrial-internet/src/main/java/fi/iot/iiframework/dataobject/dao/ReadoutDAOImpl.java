/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.dataobject.dao;

import fi.iot.iiframework.database.HibernateUtil;
import fi.iot.iiframework.dataobject.Readout;
import java.util.List;
import org.hibernate.Session;

public class ReadoutDAOImpl implements ReadoutDAO {

    @Override
    public void save(Readout readout) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(readout);
    }

    @Override
    public Readout get(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return null;
    }

    @Override
    public List<Readout> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return null;
    }

    @Override
    public void remove(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
}
