/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.daos.dataobject;

import fi.iot.iiframework.daos.GenericHibernateDAO;
import fi.iot.iiframework.domain.DataSourceObject;
import fi.iot.iiframework.domain.Device;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class DeviceDAOImpl
        extends GenericHibernateDAO<Device, Long>
        implements DeviceDAO {

    public DeviceDAOImpl() {
        super();
        defaultOrder.add(Order.asc("source"));
        defaultOrder.add(Order.asc("deviceId"));
    }

    @Override
    public List<Device> getBy(DataSourceObject dso) {
        return getBy(0, Integer.MAX_VALUE, dso);
    }

    @Override
    public List<Device> getBy(int from, int to, DataSourceObject dso) {
        List<Criterion> criterion = new ArrayList<>();
        criterion.add(Restrictions.eq("source", dso));
        return findByCriteriaFromTo(from, to, criterion);
    }

}
