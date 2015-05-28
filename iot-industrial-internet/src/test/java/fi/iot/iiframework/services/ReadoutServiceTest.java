/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services;

import fi.iot.iiframework.application.TestConfig;
import fi.iot.iiframework.dataobject.Readout;
import fi.iot.iiframework.services.dataobject.ReadoutService;
import java.io.Serializable;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestConfig.class})
public class ReadoutServiceTest implements Serializable {

    Readout r1;
    Readout r2;

    @Autowired
    private ReadoutService service;

    @Before
    public void setUp() {
        r1 = new Readout("134214158", 20.2, "C", "Temperature");
        r2 = new Readout("134214858", 19.8, "C", "Temperature");
    }

    @Test
    public void anIdIsGeneratedAutomaticallyWhenSaved() {
        service.save(r1);
        assertNotNull(r1.getId());
    }

    @Test
    public void readoutCanBeSavedAndRetrievedFromDatabase() {
        service.save(r1);
        Readout rdb = service.get(r1.getId());
        assertEquals(r1.getId(), rdb.getId());
        assertEquals(r1.getQuantity(), rdb.getQuantity());
        assertEquals(r1.getTime(), rdb.getTime());
        assertEquals(r1.getUnit(), rdb.getUnit());
        assertEquals(r1.getValue(), rdb.getValue(), 0.1);
    }
    
    @Test
    public void readoutsCanBeFoundBySensor() {
        
    }

}
