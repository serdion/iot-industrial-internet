/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.database;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class HibernateUtil {
    
    @Autowired
    private static SessionFactory sessionFactory;
    //private static final String CONFIGFILE = "hibernate_h2.cfg.xml";

//    private static SessionFactory buildSessionFactory() {
//        try {
//            SessionFactory sessionFactory = new Configuration().configure(CONFIGFILE)
//                    .buildSessionFactory();
//
//            return sessionFactory;
//
//        } catch (Throwable ex) {
//            // Make sure you log the exception, as it might be swallowed
//            System.err.println("Initial SessionFactory creation failed." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
