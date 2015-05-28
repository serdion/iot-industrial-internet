/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services;

import fi.iot.iiframework.application.TestConfig;
import fi.iot.iiframework.dataobject.Sensor;
import fi.iot.iiframework.services.dataobject.SensorService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@TransactionConfiguration(defaultRollback = true)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestConfig.class})
public class SensorServiceTest {

    Sensor s1;
    Sensor s2;

    @Autowired
    private SensorService service;

    @Before
    public void setUp() {
        s1 = new Sensor();
        s1.setId("ssds");
    }

    @Test
    public void aSensorCanBeSavedAndRetrievedFromDatabase() {
        service.save(s1);
        Sensor s2 = service.get(s1.getId());
        assertEquals(s1.getId(), s2.getId());
    }

}