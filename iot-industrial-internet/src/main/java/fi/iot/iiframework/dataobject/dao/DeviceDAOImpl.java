/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.dataobject.dao;

import fi.iot.iiframework.database.HibernateUtil;
import fi.iot.iiframework.dataobject.Device;
import java.util.List;
import org.hibernate.Session;

public class DeviceDAOImpl implements DeviceDAO {

    @Override
    public void save(Device device) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(device);
    }

    @Override
    public Device get(String id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return null;
    }

    @Override
    public List<Device> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return null;
    }

    @Override
    public void remove(String id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
}
