/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import fi.iot.iiframework.domain.InformationSource;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.MockitoAnnotations.initMocks;

public class InformationSourceManagerTest {

    private InformationSourceManager manager;

    @Mock
    private InformationSourcePersistence mockPersistence;

    private InformationSource source;

    @Before
    public void setUp() {
        initMocks(this);
        manager = new InformationSourceManager(mockPersistence);

        source = new InformationSource();
        source.setId(1l);
        source.setName("Example");
        source.setUrl("http://axwikstr.users.cs.helsinki.fi/data.xml");
        source.setType(InformationSourceType.JSON);
        source.setActive(false);

        Mockito.when(mockPersistence.addSource(source))
                .thenReturn(source);
        Mockito.when(mockPersistence.updateSource(source))
                .thenReturn(source);
    }

    @Test
    public void aNewInformationSourceIsCreatedSuccesfully() {
        manager.createSource(source);
        Map<Long, InformationSourceHandler> sources = manager.getSources();
        assertEquals(source, sources.get(1l).getSource());
    }

    @Test
    public void whenCreatedTheConfigurationIsSavedInDB() {
        manager.createSource(source);
        Mockito.verify(mockPersistence).addSource(source);
    }

    @Test
    public void updateUpdatesTheConfigurationInDatabase() {
        manager.createSource(source);
        source.setName("Another example");
        manager.updateSource(source);
        Mockito.verify(mockPersistence).updateSource(source);
    }

    @Test
    public void removeRemovesTheConfiguration() {
        manager.createSource(source);

        manager.removeSource(source.getId());
        assertTrue(manager.getSources().isEmpty());
        Mockito.verify(mockPersistence, Mockito.times(1)).deleteSource(source);
    }

}
