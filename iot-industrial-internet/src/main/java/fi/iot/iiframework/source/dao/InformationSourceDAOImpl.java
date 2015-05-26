/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source.dao;

import fi.iot.iiframework.source.InformationSource;
import fi.iot.iiframework.source.InformationSourceConfiguration;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class InformationSourceDAOImpl implements InformationSourceDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(InformationSource is) {
        sessionFactory.getCurrentSession().persist(is);
    }

    @Override
    public InformationSource get(int id) {
        return (InformationSource) sessionFactory.getCurrentSession().get(InformationSource.class, id);
    }

    @Override
    public List<InformationSource> getAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(InformationSource.class);
        return criteria.list();
    }

    @Override
    public void remove(int id) {
        InformationSource is = get(id);
        sessionFactory.getCurrentSession().delete(is);
    }

    @Override
    public void update(InformationSource is) {
        InformationSource toUpdate = this.get(is.getId());
        InformationSourceConfiguration newConfig = toUpdate.getConfig();
        newConfig.setName(is.getConfig().getName());
        newConfig.setUrl((is.getConfig().getUrl()));
        newConfig.setReadFrequency(is.getConfig().getReadFrequency());
        toUpdate.setConfig(newConfig);
        sessionFactory.getCurrentSession().update(toUpdate);

    }

}
