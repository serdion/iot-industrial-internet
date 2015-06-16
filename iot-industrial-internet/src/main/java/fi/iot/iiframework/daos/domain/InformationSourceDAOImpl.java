/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.daos.domain;

import fi.iot.iiframework.daos.GenericHibernateDAO;
import fi.iot.iiframework.domain.InformationSource;
import org.springframework.stereotype.Repository;

@Repository
public class InformationSourceDAOImpl extends GenericHibernateDAO<InformationSource, String>
        implements InformationSourceDAO {

}
