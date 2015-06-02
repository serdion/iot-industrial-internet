/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services.domain;

import fi.iot.iiframework.domain.Device;
import fi.iot.iiframework.daos.domain.DeviceDAO;
import fi.iot.iiframework.daos.domain.ReadoutDAO;
import fi.iot.iiframework.domain.InformationSourceObject;
import fi.iot.iiframework.services.GenericHibernateService;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DeviceServiceImpl
        extends GenericHibernateService<Device, Long>
        implements DeviceService {

    DeviceDAO deviceDAO;

    @Autowired
    public DeviceServiceImpl(DeviceDAO dao) {
        deviceDAO = dao;
        super.dao = dao;
    }

    @Override
    public List<Device> getBy(InformationSourceObject dso) {
        return getBy(0, Integer.MAX_VALUE, dso);
    }

    @Override
    public List<Device> getBy(int from, int to, InformationSourceObject dso) {
        return deviceDAO.getBy(from, to, dso);
    }
    
    @Override
    public Long countBy(InformationSourceObject source) {
        return countByCriteria(
                buildCriterionList(Restrictions.eq("source", source))
        );
    }

}
