/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.daos.domain;

import fi.iot.iiframework.daos.GenericHibernateDAO;
import fi.iot.iiframework.domain.Readout;
import fi.iot.iiframework.domain.Sensor;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class ReadoutDAOImpl
        extends GenericHibernateDAO<Readout, Long>
        implements ReadoutDAO {

    public ReadoutDAOImpl() {
        super();
        defaultOrder.add(Order.desc("time"));
        defaultOrder.add(Order.asc("sensor"));
    }
    
    @Override
    public Readout save(Readout readout) {
        Query query = getSession().createQuery("INSERT INTO readouts "
                + "(readout_time, readout_value, sensor, flag) "
                + "VALUES (:readout_time, :readout_value, :sensor, :flag) "
                + "WHERE NOT EXISTS "
                + "(SELECT 1 FROM readouts "
                + "WHERE sensor = :sensor AND readout_time = :readout_time");
        query.setProperties(readout);
        query.executeUpdate();
        return readout;
    }

    @Override
    public List<Readout> getBy(Sensor sensor) {
        return getBy(0, Integer.MAX_VALUE, sensor);
    }

    @Override
    public List<Readout> getBy(int from, int to, Sensor sensor) {
        List<Criterion> criterion = new ArrayList<>();
        criterion.add(Restrictions.eq("sensor", sensor));
        return findByCriteriaFromTo(from, to, criterion);
    }

    @Override
    public List<Readout> getBy(int amount, Sensor sensor) {
        return getBy(0, amount - 1, sensor);
    }
}
