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
import java.util.Properties;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.xml.bind.JAXBException;
import org.h2.engine.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@ComponentScan("fi.iot.iiframework")
@org.springframework.context.annotation.Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
//@ImportResource("classpath:spring-context.xml")
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

    private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
    private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
    private static final String PROPERTY_NAME_C3P0_MIN_SIZE = "hibernate.c3p0.min_size";
    private static final String PROPERTY_NAME_C3P0_MAX_SIZE = "hibernate.c3p0.max_size";
    private static final String PROPERTY_NAME_C3P0_TIMEOUT = "hibernate.c3p0.timeout";
    private static final String PROPERTY_NAME_C3P0_MAX_STATEMENTS = "hibernate.c3p0.max_statements";
    private static final String PROPERTY_NAME_C3P0_IDLE_TEST_PERIOD = "hibernate.c3p0.idle_test_period";
    
    @Resource
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
        dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
        dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));

        return dataSource;
    }

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        sessionBuilder.addPackages(env.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
        sessionBuilder.addAnnotatedClasses(User.class);
        sessionBuilder.setProperties(hibProperties());

        return sessionBuilder.buildSessionFactory();
    }

    private Properties hibProperties() {
        Properties properties = new Properties();
        properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
        properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
        properties.put("cache.provider_class", "org.hibernate.cache.internal.NoCacheProvider");
        properties.put(PROPERTY_NAME_C3P0_MIN_SIZE, env.getRequiredProperty(PROPERTY_NAME_C3P0_MIN_SIZE));
        properties.put(PROPERTY_NAME_C3P0_MAX_SIZE, env.getRequiredProperty(PROPERTY_NAME_C3P0_MAX_SIZE));
        properties.put(PROPERTY_NAME_C3P0_TIMEOUT, env.getRequiredProperty(PROPERTY_NAME_C3P0_TIMEOUT));
        properties.put(PROPERTY_NAME_C3P0_MAX_STATEMENTS, env.getRequiredProperty(PROPERTY_NAME_C3P0_MAX_STATEMENTS));
        properties.put(PROPERTY_NAME_C3P0_IDLE_TEST_PERIOD, env.getRequiredProperty(PROPERTY_NAME_C3P0_IDLE_TEST_PERIOD));
        return properties;
    }

    @Autowired
    @Bean(name = "txManager")
    public HibernateTransactionManager getTransactionManager(
            SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(
                sessionFactory);

        return transactionManager;
    }
}
