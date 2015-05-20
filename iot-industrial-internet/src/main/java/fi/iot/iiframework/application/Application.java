/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
        session.save(demo);
        
    }

}
