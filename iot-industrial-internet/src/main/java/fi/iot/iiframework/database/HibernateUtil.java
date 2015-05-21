
package fi.iot.iiframework.database;

import fi.iot.iiframework.DemoSaveable;
import javax.imageio.spi.ServiceRegistry;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            AnnotationConfiguration configuration = new AnnotationConfiguration().configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(DemoSaveable.class);
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            return configuration.buildSessionFactory(serviceRegistry);
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
