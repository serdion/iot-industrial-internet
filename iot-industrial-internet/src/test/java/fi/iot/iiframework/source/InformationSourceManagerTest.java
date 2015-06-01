/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import fi.iot.iiframework.application.TestConfig;
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
    private InformationSourceManager manager;
    private InformationSourceConfiguration config;
    private InformationSourceConfiguration config2;
//    private boolean setUpIsDone = false;

    @Before
    public void setUp() {
//        if (setUpIsDone) {
//            return;
//        }
        config = new InformationSourceConfiguration();
        config.setUrl("http://axwikstr.users.cs.helsinki.fi/data.xml");
        config.setType(InformationSourceType.XML);
        manager.createSource(config);
        config2 = new InformationSourceConfiguration();
        config2.setName("Example Config");
        config2.setType(InformationSourceType.XML);
        config2.setUrl("http://t-teesalmi.users.cs.helsinki.fi/MafiaTools/source.xml");
        manager.createSource(config2);
//        setUpIsDone = true;
    }

    @Test
    public void sourcesCanBeCreatedAndReplacedWithNewSources() {
        assertEquals(2, manager.getSources().size());
        assertEquals("http://axwikstr.users.cs.helsinki.fi/data.xml", manager.getAllSourceConfigsFromDB().get(0).getUrl());
        config2.setUrl("updated url");
        manager.updateSource(config2.getId(), config2);
        assertEquals("updated url", manager.getSources().get(1).getConfig().getUrl());
        assertEquals("updated url", manager.getAllSourceConfigsFromDB().get(1).getUrl());
    }
}