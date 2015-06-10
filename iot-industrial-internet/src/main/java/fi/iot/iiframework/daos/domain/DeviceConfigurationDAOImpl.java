/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.daos.domain;

import fi.iot.iiframework.daos.GenericHibernateDAO;
import fi.iot.iiframework.domain.Device;
import fi.iot.iiframework.domain.DeviceConfiguration;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class DeviceConfigurationDAOImpl extends GenericHibernateDAO<DeviceConfiguration, String> implements DeviceConfigurationDAO {

    @Override
    public List<DeviceConfiguration> getBy(Device device) {
        return getBy(0, Integer.MAX_VALUE, device);
    }

    @Override
    public List<DeviceConfiguration> getBy(int from, int to, Device device) {
        List<Criterion> criterion = new ArrayList<>();
        criterion.add(Restrictions.eq("device", device));
        return findByCriteriaFromTo(from, to, criterion);
    }

    @Override
    public List<DeviceConfiguration> getBy(int amount, Device device) {
        return getBy(0, amount-1, device);
    }

}
