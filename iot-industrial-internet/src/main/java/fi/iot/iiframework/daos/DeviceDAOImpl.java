/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.daos;

import fi.iot.iiframework.database.HibernateUtil;
import fi.iot.iiframework.dataobject.Device;
import java.util.List;
import org.hibernate.Criteria;
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
        return (Device) session.get(Device.class, id);
    }

    @Override
    public List<Device> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Device.class);
        return criteria.list();
    }

    @Override
    public void remove(String id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Device device = get(id);
        session.delete(device);
    }

    @Override
    public void update(Device device) {
        Device deviceToUpdate = get(device.getId());
        deviceToUpdate.setSensors(device.getSensors());
        HibernateUtil.getSessionFactory().getCurrentSession().update(deviceToUpdate);
    }
    
}
