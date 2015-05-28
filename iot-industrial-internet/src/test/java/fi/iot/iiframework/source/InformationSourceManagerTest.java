/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class InformationSourceManagerTest {

    InformationSourceManager manager;

    @Before
    public void setUp() {
        manager = new InformationSourceManager();
    }

    @Test
    public void createdSourceIsAddedToSources() {
//        InformationSourceConfiguration config = new InformationSourceConfiguration();
//        config.type = InformationSourceType.XML;
//        config.url = "http://t-teesalmi.users.cs.helsinki.fi/MafiaTools/source.xml";
//        manager.createSource(config);
//        assertEquals(1, manager.getSources().size());
    }
}
