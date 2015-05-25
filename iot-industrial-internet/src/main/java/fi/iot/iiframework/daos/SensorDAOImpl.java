/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.daos;

import fi.iot.iiframework.database.HibernateUtil;
import fi.iot.iiframework.dataobject.Sensor;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SensorDAOImpl implements SensorDAO {
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Sensor sensor) {
        sessionFactory.getCurrentSession().persist(sensor);
    }

    @Override
    public Sensor get(String id) {
        return (Sensor) sessionFactory.getCurrentSession().get(Sensor.class, id);
    }

    @Override
    public List<Sensor> getAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Sensor.class);
        return criteria.list();
    }

    @Override
    public void remove(String id) {
        Sensor sensor = get(id);
        sessionFactory.getCurrentSession().delete(sensor);
    }

    @Override
    public void update(Sensor sensor) {
        Sensor sensorToUpdate = get(sensor.getId());
        sensorToUpdate.setReadouts(sensor.getReadouts());
        sessionFactory.getCurrentSession().update(sensorToUpdate);
    }
    
}
