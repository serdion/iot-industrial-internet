/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.daos.dataobject;

import fi.iot.iiframework.daos.GenericHibernateDAO;
import fi.iot.iiframework.dataobject.Device;
import fi.iot.iiframework.dataobject.Readout;
import fi.iot.iiframework.dataobject.Sensor;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author atte
 */
@Repository
public class ReadoutDAOImpl
        extends GenericHibernateDAO<Readout, Long>
        implements ReadoutDAO {

    @Override
    public List<Readout> getBy(Sensor sensor) {
        return getBy(0, Integer.MAX_VALUE, sensor);
    }

    @Override
    public List<Readout> getBy(int from, int to, Sensor sensor) {
        return findByCriteriaFromTo(from, to, 
                Restrictions.eq("SENSOR_ID", sensor.getId()));
    }

}