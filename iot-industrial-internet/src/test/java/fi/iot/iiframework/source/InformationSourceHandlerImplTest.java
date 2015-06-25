/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import fi.iot.iiframework.domain.InformationSource;
import fi.iot.iiframework.domain.Sensor;
import fi.iot.iiframework.parsers.Parser;
import fi.iot.iiframework.parsers.ParserContainer;
import fi.iot.iiframework.services.domain.InformationSourceObjectProvider;
import java.io.IOException;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Matchers;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

public class InformationSourceHandlerImplTest {

    private InformationSourceHandler handler;
    private InformationSource source;
    @Mock
    private InformationSourcePersistence mockPersistence;
    @Mock
    private ReadScheduler mockScheduler;
    @Mock
    private Parser mockParser;

    private List<Sensor> examples;

    @Before
    public void setUp() throws JAXBException, IOException {
        MockitoAnnotations.initMocks(this);
        
        ParserContainer.getParsers().put(InformationSourceType.JSON, mockParser);
        
        source = new InformationSource();
        source.setActive(false);
        source.setName("test");
        source.setType(InformationSourceType.JSON);
        source.setUrl("http://testurl.fi");

        handler = new InformationSourceHandlerImpl(source, mockPersistence);
        handler.setScheduler(mockScheduler);
        
        examples = InformationSourceObjectProvider.provideSensorsWithChildren();
        
        when(mockParser.parse(Matchers.anyString())).thenReturn(examples);
    }

    @Test
    public void readReadsSuccesfullyFromReader() throws JAXBException, IOException {
        examples.forEach(s ->{
            assertTrue(handler.read().contains(s));
        });
    }
    
    @Test
    public void readAndWriteWritesTheReadObjectsToDatabase() {
        handler.readAndWrite();
        verify(mockPersistence).writeReadoutsToSource(source, examples);
    }
}
