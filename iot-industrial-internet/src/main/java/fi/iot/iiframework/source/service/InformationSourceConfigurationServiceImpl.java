/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source.service;

import fi.iot.iiframework.services.GenericHibernateService;
import fi.iot.iiframework.source.InformationSourceConfiguration;
import fi.iot.iiframework.source.dao.InformationSourceConfigurationDAO;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional

public class InformationSourceConfigurationServiceImpl
        extends GenericHibernateService<InformationSourceConfiguration, String>
        implements InformationSourceConfigurationService {

    @Autowired
    public InformationSourceConfigurationServiceImpl(InformationSourceConfigurationDAO dao) {
        super.dao = dao;
    }
}
