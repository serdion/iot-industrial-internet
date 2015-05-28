/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services.dataobject;

import fi.iot.iiframework.dataobject.Sensor;
import fi.iot.iiframework.daos.dataobject.SensorDAO;
import fi.iot.iiframework.services.GenericHibernateService;
import java.io.Serializable;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SensorServiceImpl
        extends GenericHibernateService<Sensor, String>
        implements SensorService {

    @Autowired
    public SensorServiceImpl(SensorDAO dao) {
        super.dao = dao;
    }

}
