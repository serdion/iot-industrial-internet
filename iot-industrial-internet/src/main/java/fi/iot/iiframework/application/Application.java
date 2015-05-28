/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.application;

import fi.iot.iiframework.errors.ErrorLogger;
import fi.iot.iiframework.errors.ErrorType;
import fi.iot.iiframework.errors.SysError;
import fi.iot.iiframework.source.InformationSourceConfiguration;
import fi.iot.iiframework.source.InformationSourceManager;
import fi.iot.iiframework.source.InformationSourceType;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
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
        logger.log(Level.CONFIG, "Following beans found:\t{0}", Arrays.toString(ctx.getBeanDefinitionNames()));

        InformationSourceManager infSourceManager = ctx.getBean(InformationSourceManager.class);

        InformationSourceConfiguration infSourceConfiguration = new InformationSourceConfiguration();
        infSourceConfiguration.setName("Example Config");
        infSourceConfiguration.setReadFrequency(1000);
        infSourceConfiguration.setType(InformationSourceType.XML);
        infSourceConfiguration.setUrl("http://axwikstr.users.cs.helsinki.fi/data.xml");
        infSourceManager.createSource(infSourceConfiguration);
        infSourceManager.getSources().get(0).readAndWrite();

        SysError e = new SysError(ErrorType.TEST_ERROR, new Date(), "ZZZZ");
        ErrorLogger.newError(e);
//        System.out.println(infSourceManager.getAllFromDB().get(0).getUrl());

    }

}
