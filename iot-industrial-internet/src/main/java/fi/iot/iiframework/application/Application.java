/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.application;

import fi.iot.iiframework.database.HibernateUtil;
import fi.iot.iiframework.dataobject.DataObjectFactory;
import fi.iot.iiframework.dataobject.DataSourceObject;
import fi.iot.iiframework.dataobject.Readout;
import fi.iot.iiframework.source.InformationSourceManager;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    private static final Logger logger = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        DataSourceObject dso = DataObjectFactory.getRandomDataObject();
        session.save(dso);
        dso = DataObjectFactory.getRandomDataObject();
        session.save(dso);
        session.getTransaction().commit();

        session.beginTransaction();
        Criteria criteria = session.createCriteria(DataSourceObject.class);
        List<DataSourceObject> list = criteria.list();
        session.getTransaction().commit();
    }

    @Bean
    public InformationSourceManager bean() {
        return new InformationSourceManager();
    }

}
