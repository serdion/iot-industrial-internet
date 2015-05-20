/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.application;

import fi.iot.iiframework.DemoSaveable;
import fi.iot.iiframework.database.HibernateUtil;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        DemoSaveable demo = new DemoSaveable(10, "ten");
        session.beginTransaction();
        session.save(demo);
        session.getTransaction().commit();
        
    }

}