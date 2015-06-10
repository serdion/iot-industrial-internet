/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services.domain;

import fi.iot.iiframework.daos.domain.SensorConfigurationDAO;
import fi.iot.iiframework.domain.SensorConfiguration;
import fi.iot.iiframework.services.GenericHibernateService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SensorConfigurationServiceImpl extends GenericHibernateService<SensorConfiguration, String> implements SensorConfigurationService {

    @Autowired
    public SensorConfigurationServiceImpl(SensorConfigurationDAO dao) {
        super.dao = dao;
    }
}
