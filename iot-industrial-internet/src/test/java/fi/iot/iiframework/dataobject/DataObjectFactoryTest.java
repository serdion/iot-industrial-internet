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
    
    private DataObject obj;

    @Before
    public void setUp() {
        obj = DataObjectFactory.getRandomDataObject();
    }

    @Test
    public void headerIsGeneratedProperly() {
        assertTrue(obj.getHeader().getUptime() > 1000 && obj.getHeader().getUptime() < 10000);
        assertEquals("success", obj.getHeader().getResponse());
    }
    
    @Test
    public void devicesAreGeneratedProperly() {
        assertEquals(10, obj.getDevices().size());
        assertTrue(obj.getDevices().get(0).status);
        assertNotEquals(null, obj.getDevices().get(0).id);
    }
    
    @Test
    public void sensorsAreGeneratedProperly() {
        assertEquals(10, obj.getDevices().get(0).getSensors().size());
        assertNotEquals(null, obj.getDevices().get(0).getSensors().get(0));
    }
    
    @Test
    public void readoutsAreGeneratedProperly() {
        assertEquals(25, obj.getDevices().get(0).getSensors().get(0).getReadouts().size());
        assertEquals("Temperature", obj.getDevices().get(0).getSensors().get(0).getReadouts().get(0).quantity);
        assertEquals("C", obj.getDevices().get(0).getSensors().get(0).getReadouts().get(0).unit);
        assertTrue(obj.getDevices().get(0).getSensors().get(0).getReadouts().get(0).value >= 22.1 && obj.getDevices().get(0).getSensors().get(0).getReadouts().get(0).value <= 23.1);
    }

}
