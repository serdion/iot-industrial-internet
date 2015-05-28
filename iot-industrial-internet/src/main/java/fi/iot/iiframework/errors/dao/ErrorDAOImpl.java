/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.errors.dao;

import fi.iot.iiframework.daos.GenericHibernateDAO;
import fi.iot.iiframework.errors.SysError;
import org.springframework.stereotype.Repository;

@Repository
public class ErrorDAOImpl extends GenericHibernateDAO<SysError, String>
        implements ErrorDAO {

}
