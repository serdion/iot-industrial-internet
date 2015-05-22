/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.dataobject.dao;

import fi.iot.iiframework.database.HibernateUtil;
import fi.iot.iiframework.dataobject.Sensor;
import java.util.List;
import org.hibernate.Session;

public class SensorDAOImpl implements SensorDAO {

    @Override
    public void save(Sensor sensor) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(sensor);
    }

    @Override
    public Sensor get(String id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return null;
    }

    @Override
    public List<Sensor> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return null;
    }

    @Override
    public void remove(String id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
}
