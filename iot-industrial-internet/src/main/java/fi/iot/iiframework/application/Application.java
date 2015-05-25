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
import fi.iot.iiframework.source.InformationSourceConfiguration;
import fi.iot.iiframework.source.InformationSourceManager;
import fi.iot.iiframework.source.InformationSourceType;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
@ComponentScan("fi.iot.iiframework")
@ImportResource("classpath:spring-context.xml")
public class Application {

    private static final Logger logger = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) throws JAXBException, MalformedURLException {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        Session session = ctx.getBean(SessionFactory.class).openSession();

        InformationSourceManager ism = ctx.getBean(InformationSourceManager.class);

        InformationSourceConfiguration isc = new InformationSourceConfiguration();
        isc.setId("1");
        isc.setType(InformationSourceType.XML);
        isc.setUrl("http://axwikstr.users.cs.helsinki.fi/data.xml");
        ism.createSource(isc);
        ism.getSources().get(0).readAndWrite();

        //System.out.println(Arrays.asList(ctx.getBeanDefinitionNames()));
    }
    
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static final String CONFIGFILE = "hibernate_h2.cfg.xml";

    private static SessionFactory buildSessionFactory() {
        try {
            SessionFactory sessionFactory = new Configuration().configure(CONFIGFILE)
                    .buildSessionFactory();

            return sessionFactory;

        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Bean
    public SessionFactory sessionFactory() {
        return sessionFactory;
    }
    
//    @Bean
//    HibernateTransactionManager transactionManager() {
//        return hc.transactionManager();
//    }
//    
//    @Bean
//    DataSource dataSource() {
//        return hc.dataSource();
//    }
//    
//    @Bean
//    LocalSessionFactoryBean sessionFactory() {
//        return hc.sessionFactory();
//    }

}
