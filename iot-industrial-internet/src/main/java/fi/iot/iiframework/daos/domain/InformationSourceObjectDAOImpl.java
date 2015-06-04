/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.daos.domain;

import fi.iot.iiframework.daos.GenericHibernateDAO;
import fi.iot.iiframework.domain.InformationSourceObject;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository
public class InformationSourceObjectDAOImpl
        extends GenericHibernateDAO<InformationSourceObject, String>
        implements InformationSourceObjectDAO {

    public InformationSourceObjectDAOImpl() {
        defaultOrder.add(Order.asc("name"));
        defaultOrder.add(Order.asc("id"));
    }


    
}
