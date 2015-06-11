/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import fi.iot.iiframework.domain.InformationSource;
import fi.iot.iiframework.application.TestConfig;
import fi.iot.iiframework.services.domain.InformationSourceService;
import fi.iot.iiframework.services.domain.SensorService;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.MockitoAnnotations.initMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestConfig.class})
@Ignore
public class InformationSourceManagerTest {

    @Autowired
    private InformationSourceManagerImpl manager;

    @Mock
    private InformationSourcePersistence mockPersistence;

    private InformationSource source;

    @Before
    public void setUp() {
        initMocks(this);
        manager.setPersistence(mockPersistence);

        source = new InformationSource();
        source.setId("1");
        source.setName("Example");
        source.setUrl("http://axwikstr.users.cs.helsinki.fi/data.xml");
        source.setType(InformationSourceType.XML);
        source.setActive(false);
        source.setReadFrequency(0);
        manager.createSource(source);
    }

    @Test
    public void aNewInformationSourceIsCreatedSuccesfully() {
        Map<String, InformationSourceHandler> sources = manager.getSources();
        assertEquals(source, sources.get("1").getConfig());
    }

    @Test
    public void whenCreatedTheConfigurationIsSavedInDB() {
        Mockito.verify(mockPersistence).addSource(source);
    }

    @Test
    public void updateUpdatesTheConfigurationInDatabase() {
        source.setName("Another example");
        manager.updateSource(source);
        Mockito.verify(mockPersistence).updateSource(source);
    }
    
    @Test
    public void removeRemovesTheConfiguration() {
        manager.removeSource(source.getId());
        assertTrue(manager.getSources().isEmpty());
        Mockito.verify(mockPersistence, Mockito.times(1)).deleteSource(source);
    }

}
