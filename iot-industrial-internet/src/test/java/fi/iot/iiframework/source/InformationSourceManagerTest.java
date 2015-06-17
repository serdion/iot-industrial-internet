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
import org.junit.Ignore;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.MockitoAnnotations.initMocks;

@Ignore
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
        source.setType(InformationSourceType.XML);
        source.setActive(false);
        manager.createSource(source);

        Mockito.when(mockPersistence.updateSource(Matchers.any(InformationSource.class)))
                .thenReturn(source);
        Mockito.when(mockPersistence.updateSource(Matchers.any(InformationSource.class)))
                .thenReturn(source);
    }

    @Test
    public void aNewInformationSourceIsCreatedSuccesfully() {
        Map<Long, InformationSourceHandler> sources = manager.getSources();
        assertEquals(source, sources.get(1l).getSource());
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
