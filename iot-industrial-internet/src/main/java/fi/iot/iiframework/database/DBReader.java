/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.database;

import fi.iot.iiframework.dataobject.DataSourceObject;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author atte
 */
public class DBReader {
    

    public static List<DataSourceObject> readDataSourceObject() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(DataSourceObject.class);
        List<DataSourceObject> list = criteria.list();
        session.getTransaction().commit();
        
        return list;
    }
}
