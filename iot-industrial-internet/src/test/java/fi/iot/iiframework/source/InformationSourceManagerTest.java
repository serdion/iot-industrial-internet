/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import fi.iot.iiframework.application.TestConfig;
import fi.iot.iiframework.services.domain.InformationSourceObjectService;
import fi.iot.iiframework.services.source.InformationSourceConfigurationService;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestConfig.class})
@Ignore
public class InformationSourceManagerTest {

    @Autowired
    private InformationSourceManager manager;

    @Mock
    private InformationSourceConfigurationService mockConfigService;

    @Mock
    private InformationSourceObjectService mockService;

    private InformationSourceConfiguration config;
    private InformationSourceConfiguration config2;

    @Before
    public void setUp() {
        manager.setConfigService(mockConfigService);
        manager.setService(mockService);

        config = new InformationSourceConfiguration();
        config.setId("1");
        config.setName("Example");
        config.setUrl("http://axwikstr.users.cs.helsinki.fi/data.xml");
        config.setType(InformationSourceType.XML);
        config.setActive(false);
        config.setReadFrequency(0);
        manager.createSource(config);

//        config2 = new InformationSourceConfiguration();
//        config2.setId("2");
//        config2.setName("Example Config");
//        config2.setType(InformationSourceType.XML);
//        config2.setUrl("http://t-teesalmi.users.cs.helsinki.fi/MafiaTools/source.xml");
//        config2.setActive(false);
//        config2.setReadFrequency(0);
    }

    @Test
    public void aNewInformationSourceIsCreatedSuccesfully() {
        Map<String, InformationSource> sources = manager.getSources();
        assertEquals(config, sources.get("1").getConfig());
    }

    @Test
    public void whenCreatedTheConfigurationIsSavedInDB() {
        Mockito.verify(mockConfigService).save(config);
    }

    @Test
    public void updateUpdatesTheConfigurationInDatabase() {
        config.setName("Another example");
        manager.updateSource(config);
        Mockito.verify(mockConfigService, Mockito.times(2)).save(config);
    }
    
    @Test
    public void removeRemovesTheConfiguration() {
        
    }

}
