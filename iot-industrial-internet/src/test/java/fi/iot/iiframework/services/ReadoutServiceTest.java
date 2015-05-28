/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services;

import fi.iot.iiframework.application.TestConfig;
import fi.iot.iiframework.dataobject.Readout;
import fi.iot.iiframework.dataobject.Sensor;
import fi.iot.iiframework.services.dataobject.ReadoutService;
import fi.iot.iiframework.services.dataobject.SensorService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestConfig.class})
public class ReadoutServiceTest {

    Readout r1;
    Readout r2;
    Readout r3;

    @Autowired
    private ReadoutService readoutService;
    @Autowired
    private SensorService sensorService;

    @Before
    public void setUp() {
        r1 = new Readout("134214158", 20.2, "C", "Temperature");
        r2 = new Readout("134214858", 19.8, "C", "Temperature");
        r3 = new Readout("13184174a", 7.8, "C", "Temperature");
    }

    @Test
    public void anIdIsGeneratedAutomaticallyWhenSaved() {
        readoutService.save(r1);
        assertNotNull(r1.getId());
    }

    @Test
    public void readoutCanBeSavedAndRetrievedFromDatabase() {
        readoutService.save(r1);
        Readout rdb = readoutService.get(r1.getId());
        assertEquals(r1.getId(), rdb.getId());
        assertEquals(r1.getQuantity(), rdb.getQuantity());
        assertEquals(r1.getTime(), rdb.getTime());
        assertEquals(r1.getUnit(), rdb.getUnit());
        assertEquals(r1.getValue(), rdb.getValue(), 0.1);
    }
    
    @Test
    public void readoutsCanBeFoundBySensor() {
        Set<Readout> readouts = new HashSet<>();
        readouts.add(r1);
        readouts.add(r2);
        Sensor s = new Sensor("dkjawkdja", readouts);
        
        sensorService.save(s);
        readoutService.save(r1);
        readoutService.save(r2);
        readoutService.save(r3);
        List<Readout> readReadouts = readoutService.getBy(s);
        readReadouts.add(r3);
        assertTrue(readReadouts.contains(r1));
        assertTrue(readReadouts.contains(r2));
        assertFalse(readReadouts.contains(r3));
    }

}
