/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import fi.iot.iiframework.application.TestConfig;
import java.net.MalformedURLException;
import javax.xml.bind.JAXBException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@TransactionConfiguration(defaultRollback = true)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestConfig.class})
public class InformationSourceManagerTest {

    @Autowired
    InformationSourceManager manager;

    @Before
    public void setUp() {
        manager = new InformationSourceManager();
    }

    @Test
    public void createdSourceIsAddedToSources() throws JAXBException, MalformedURLException {
//        InformationSourceConfiguration config = new InformationSourceConfiguration();
//        config.setUrl("http://t-teesalmi.users.cs.helsinki.fi/MafiaTools/source.xml");
//        config.setType(InformationSourceType.XML);
//        manager.createSource(config);
//        assertEquals(1, manager.getSources().size());
//        assertEquals("http://t-teesalmi.users.cs.helsinki.fi/MafiaTools/source.xml", manager.getAllSourceConfigsFromDB().get(0).getUrl());
    }
}
