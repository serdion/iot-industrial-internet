/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import fi.iot.iiframework.datasourcereaders.InformationSourceReader;
import fi.iot.iiframework.services.domain.InformationSourceObjectService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class InformationSourceImplTest {

    private InformationSourceImpl impl;
    private InformationSourceConfiguration config;
    private InformationSourceReader reader;
    private InformationSourceObjectService service;



    @Before
    public void setUp() {
        config = new InformationSourceConfiguration();
        config.type = InformationSourceType.XML;
        config.readFrequency = 11;
        config.name = "test";
        config.type = InformationSourceType.XML;
        config.url = "http://t-teesalmi.users.cs.helsinki.fi/MafiaTools/source.xml";
        impl = new InformationSourceImpl(config, service);

    }

    @Test
    public void canCreateAnXMLReader() {
        assertNotEquals(null, impl);
    }
}
