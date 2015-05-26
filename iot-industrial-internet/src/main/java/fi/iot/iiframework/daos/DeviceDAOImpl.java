/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.daos;

import fi.iot.iiframework.dataobject.Device;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DeviceDAOImpl implements DeviceDAO {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public void save(Device device) {
        sessionFactory.getCurrentSession().persist(device);
    }

    @Override
    public Device get(String id) {
        return (Device) sessionFactory.getCurrentSession().get(Device.class, id);
    }

    @Override
    public List<Device> getAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Device.class);
        return criteria.list();
    }

    @Override
    public void remove(String id) {
        Device device = get(id);
        sessionFactory.getCurrentSession().delete(device);
    }

    @Override
    public void update(Device device) {
        Device deviceToUpdate = get(device.getId());
        deviceToUpdate.setSensors(device.getSensors());
        sessionFactory.getCurrentSession().update(deviceToUpdate);
    }
    
}
