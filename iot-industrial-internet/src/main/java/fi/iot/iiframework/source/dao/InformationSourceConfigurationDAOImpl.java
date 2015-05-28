/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source.dao;

import fi.iot.iiframework.source.InformationSourceConfiguration;
import fi.iot.iiframework.source.InformationSourceType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class InformationSourceConfigurationDAOImpl implements InformationSourceConfigurationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(InformationSourceConfiguration isc) {
        sessionFactory.getCurrentSession().persist(isc);

    }

    @Override
    public InformationSourceConfiguration get(String id) {
        return (InformationSourceConfiguration) sessionFactory.getCurrentSession().get(InformationSourceConfiguration.class, id);
    }

    @Override
    public List<InformationSourceConfiguration> getAll() {
        sessionFactory.openSession();
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(InformationSourceConfiguration.class);
        return criteria.list();
    }

    @Override
    public void remove(String id) {
        InformationSourceConfiguration isc = get(id);
        sessionFactory.getCurrentSession().delete(isc);
    }

    @Override
    public void update(InformationSourceConfiguration isc) {
        InformationSourceConfiguration updatedIsc = get(isc.getId());
        
        updatedIsc.setName(isc.getName());
        updatedIsc.setReadFrequency(isc.getReadFrequency());
        updatedIsc.setType(isc.getType());
        updatedIsc.setUrl(isc.getUrl());

        sessionFactory.getCurrentSession().update(updatedIsc);
    }

}
