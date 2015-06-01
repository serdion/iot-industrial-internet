/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.daos.dataobject;

import fi.iot.iiframework.daos.GenericHibernateDAO;
import fi.iot.iiframework.dataobject.Device;
import fi.iot.iiframework.dataobject.Sensor;
import java.util.List;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class SensorDAOImpl
        extends GenericHibernateDAO<Sensor, Long>
        implements SensorDAO {

    public SensorDAOImpl() {
        super();
        defaultOrder.add(Order.asc("device"));
    }

    @Override
    public List<Sensor> getBy(Device device) {
        return getBy(0, Integer.MAX_VALUE, device);
    }

    @Override
    public List<Sensor> getBy(int from, int to, Device device) {
        return findByCriteriaFromTo(from, to,
                Restrictions.eq("device", device));
    }
}
