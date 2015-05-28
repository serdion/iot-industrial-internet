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
import fi.iot.iiframework.services.dataobject.SensorService;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
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
public class SensorServiceTest extends GenericServiceTest<Sensor, String> {

    Sensor se1;
    Sensor se2;
    Sensor se3;

    @Autowired
    private SensorService sensorService;

    @Before
    public void setUp() {
        service = sensorService;
        s1 = se1 = new Sensor(UUID.randomUUID().toString(), new HashSet<>());
        s2 = se2 = new Sensor(UUID.randomUUID().toString(), new HashSet<>());
        s3 = se3 = new Sensor(UUID.randomUUID().toString(), new HashSet<>());
    }

}