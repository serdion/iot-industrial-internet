/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.dataobject;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class DataObjectFactoryTest {
    
    private DataSourceObject obj;

    @Before
    public void setUp() {
        obj = DataObjectFactory.getRandomDataObject();
    }

    @Test
    public void headerIsGeneratedProperly() {
        assertTrue(obj.getHeader().getUptime() > 1000 && obj.getHeader().getUptime() < 10000);
        assertEquals("success", obj.getHeader().getResponse());
    }

}
