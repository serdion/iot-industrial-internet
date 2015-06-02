/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services.domain;

import fi.iot.iiframework.application.TestConfig;
import fi.iot.iiframework.domain.InformationSourceObject;
import fi.iot.iiframework.domain.Device;
import fi.iot.iiframework.domain.Sensor;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@TransactionConfiguration(defaultRollback = true)
@Transactional
@SpringApplicationConfiguration(classes = {TestConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class SensorServiceTest {

    Device d1;
    Device d2;
    
    Sensor s1;
    Sensor s2;

    @Autowired
    private SensorService service;

    @Before
    public void setUp() {
        InformationSourceObject dso = InformationSourceObjectProvider.provideDataObject();

        d1 = InformationSourceObjectProvider.provideDevice();
        d2 = InformationSourceObjectProvider.provideDevice();
        d1.setSource(dso);
        d2.setSource(dso);

        s1 = InformationSourceObjectProvider.provideSensor();
        s2 = InformationSourceObjectProvider.provideSensor();
        s1.setDevice(d1);
        s2.setDevice(d2);

        service.save(s1);
        service.save(s2);
    }
    
    @Test
    public void sensorsCanBeFoundByDevice() {
        List<Sensor> sensors = service.getBy(d1);
        assertTrue(sensors.contains(s1));
        assertFalse(sensors.contains(s2));
    }
    
    @Test
    public void sensorsCanBeCounted() {
        assertEquals(2, (long) service.count());
    }
    
    @Test
    public void sensorsCanBeCountedByDevice() {
        assertEquals(1, (long) service.countBy(d1));
    }

}
