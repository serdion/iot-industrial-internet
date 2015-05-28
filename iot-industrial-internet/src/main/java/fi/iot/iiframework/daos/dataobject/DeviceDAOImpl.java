/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.daos.dataobject;

import fi.iot.iiframework.daos.GenericHibernateDAO;
import fi.iot.iiframework.dataobject.DataSourceObject;
import fi.iot.iiframework.dataobject.Device;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class DeviceDAOImpl
        extends GenericHibernateDAO<Device, String>
        implements DeviceDAO {

    @Override
    public List<Device> getBy(DataSourceObject dso) {
        return getBy(0, Integer.MAX_VALUE, dso);
    }

    @Override
    public List<Device> getBy(int from, int to, DataSourceObject dso) {
        return findByCriteriaFromTo(from, to,
                Restrictions.eq("source_id", dso.getId()));
    }

}
