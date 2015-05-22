/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.daos;

import fi.iot.iiframework.database.HibernateUtil;
import fi.iot.iiframework.dataobject.Readout;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ReadoutDAOImpl implements ReadoutDAO {
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Readout readout) {
        sessionFactory.getCurrentSession().persist(readout);
    }

    @Override
    public Readout get(Long id) {
        return (Readout) sessionFactory.getCurrentSession().get(Readout.class, id);
    }

    @Override
    public List<Readout> getAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Readout.class);
        return criteria.list();
    }

    @Override
    public void remove(Long id) {
        Readout readout = get(id);
        sessionFactory.getCurrentSession().delete(readout);
    }

    @Override
    public void update(Readout readout) {
        Readout readoutToUpdate = get(readout.getId());
        readoutToUpdate.setQuantity(readout.getQuantity());
        readoutToUpdate.setTime(readout.getTime());
        readoutToUpdate.setUnit(readout.getUnit());
        readoutToUpdate.setValue(readout.getValue());
        sessionFactory.getCurrentSession().update(readoutToUpdate);
    }
    
}
