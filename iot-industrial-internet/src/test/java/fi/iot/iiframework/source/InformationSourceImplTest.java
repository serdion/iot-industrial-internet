/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import fi.iot.iiframework.domain.InformationSourceConfiguration;
import fi.iot.iiframework.readers.InformationSourceReader;
import fi.iot.iiframework.domain.InformationSourceObject;
import fi.iot.iiframework.services.domain.InformationSourceObjectService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class InformationSourceImplTest {

    private InformationSource source;
    private InformationSourceConfiguration config;
    @Mock
    private InformationSourceObjectService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        config = new InformationSourceConfiguration();
        config.setReadFrequency(11);
        config.setName("test");
        config.setType(InformationSourceType.XML);
        config.setUrl("http://t-teesalmi.users.cs.helsinki.fi/MafiaTools/source.xml");
        source = new InformationSourceImpl(config, service);

    }

    @Test
    public void canCreateAnXMLReader() {
//        assertNotEquals(null, impl);
    }
}
