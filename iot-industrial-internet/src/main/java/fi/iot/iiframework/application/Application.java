/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.application;

import fi.iot.iiframework.database.HibernateUtil;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
@ComponentScan("fi.iot.iiframework.database")
@ImportResource("classpath:spring-context.xml")
public class Application {
    
    private static final Logger logger = Logger.getLogger(Application.class.getName());

//    static HibernateConfiguration hc = new HibernateConfiguration();
    
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        
        HibernateUtil.getSessionFactory().openSession();
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
