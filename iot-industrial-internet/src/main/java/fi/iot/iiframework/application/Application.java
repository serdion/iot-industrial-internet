/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.application;

import fi.iot.iiframework.source.InformationSourceConfiguration;
import fi.iot.iiframework.source.InformationSourceManager;
import fi.iot.iiframework.source.InformationSourceType;
import java.net.MalformedURLException;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("fi.iot.iiframework")
public class Application {

    private static final Logger logger = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) throws JAXBException, MalformedURLException {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);        

        InformationSourceManager ism = ctx.getBean(InformationSourceManager.class);
        InformationSourceConfiguration isc = new InformationSourceConfiguration();
        isc.setId("1");
        isc.setType(InformationSourceType.XML);
        isc.setUrl("http://axwikstr.users.cs.helsinki.fi/data.xml");
        ism.createSource(isc);
        ism.getSources().get(0).readAndWrite();
    }

}
