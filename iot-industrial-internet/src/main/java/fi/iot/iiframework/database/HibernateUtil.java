package fi.iot.iiframework.database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static final String CONFIGFILE = "hibernate_h2.cfg.xml";

    private static SessionFactory buildSessionFactory() {
        try {
//            AnnotationConfiguration configuration = new AnnotationConfiguration().configure(CONFIGFILE);
//            configuration.addAnnotatedClass(DemoSaveable.class);
//            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                    .applySettings(configuration.getProperties()).build();
//            return configuration.buildSessionFactory(serviceRegistry);
            SessionFactory sessionFactory = new Configuration().configure(CONFIGFILE)
                    .buildSessionFactory();

            return sessionFactory;

        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
