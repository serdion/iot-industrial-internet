/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.application;

import fi.iot.iiframework.errors.ErrorLogger;
import fi.iot.iiframework.errors.ErrorSeverity;
import fi.iot.iiframework.errors.ErrorType;
import fi.iot.iiframework.errors.SysError;
import fi.iot.iiframework.source.InformationSourceConfiguration;
import fi.iot.iiframework.source.InformationSourceManager;
import fi.iot.iiframework.source.InformationSourceType;
import java.io.IOException;
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

    public static final Logger logger = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) throws JAXBException, MalformedURLException, IOException {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        logger.log(Level.CONFIG, "Following beans found:\t{0}", Arrays.toString(ctx.getBeanDefinitionNames()));

        InformationSourceManager infSourceManager = ctx.getBean(InformationSourceManager.class);

        InformationSourceConfiguration infSourceConfiguration = new InformationSourceConfiguration();
        infSourceConfiguration.setName("Example Config");
        infSourceConfiguration.setType(InformationSourceType.XML);
        infSourceConfiguration.setUrl("http://axwikstr.users.cs.helsinki.fi/data.xml");
        infSourceManager.createSource(infSourceConfiguration);
        InformationSourceConfiguration infSourceConfiguration2 = new InformationSourceConfiguration();
        infSourceConfiguration2.setName("Example Config");
        infSourceConfiguration2.setType(InformationSourceType.XML);
        infSourceConfiguration2.setUrl("http://t-teesalmi.users.cs.helsinki.fi/MafiaTools/source.xml");
        infSourceManager.createSource(infSourceConfiguration2);
        infSourceManager.getSources().get(0).readAndWrite();
        System.out.println(infSourceManager.getAllSourceConfigsFromDB().get(0).getUrl());
        System.out.println(infSourceManager.getAllSourceConfigsFromDB().get(1).getUrl());
        infSourceManager.removeSource(infSourceConfiguration.getId());
        System.out.println(infSourceManager.getAllSourceConfigsFromDB().get(0).getUrl());
        infSourceConfiguration2.setUrl("updated url");
        infSourceManager.updateSource(infSourceConfiguration2.getId(), infSourceConfiguration2);
        System.out.println(infSourceManager.getAllSourceConfigsFromDB().get(0).getUrl());

        SysError e = new SysError(ErrorType.TEST_ERROR, ErrorSeverity.NOTIFICATION, "This is a test error");
        ErrorLogger.newError(e);

    }

}
