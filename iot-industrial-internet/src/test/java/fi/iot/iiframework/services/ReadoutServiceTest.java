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
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@TransactionConfiguration(defaultRollback = true)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestConfig.class})
public class ReadoutServiceTest extends GenericServiceTest<Readout, Long> {

    @Autowired
    private ReadoutService readoutService;
    @Autowired
    private SensorService sensorService;

    Readout r1;
    Readout r2;
    Readout r3;

    Set<Readout> readouts;
    Sensor s;

    @Before
    public void setUp() {
        super.service = readoutService;
        s1 = r1 = new Readout("134214158", 20.2, "C", "Temperature");
        s2 = r2 = new Readout("134214858", 19.8, "C", "Temperature");
        s3 = r3 = new Readout("13184174a", 7.8, "C", "Temperature");

        readouts = new HashSet<>();
        readouts.add(s1);
        readouts.add(s2);
        s = new Sensor("dkjawkdja", readouts);
    }

    @Test
    public void anIdIsGeneratedAutomaticallyWhenSaved() {
        readoutService.save(s1);
        assertNotNull(s1.getId());
    }

    @Test
    public void readoutsCanBeFoundBySensor() {
        sensorService.save(s);

        List<Readout> readReadouts = readoutService.getBy(s);
        assertTrue(readReadouts.contains(s1));
        assertTrue(readReadouts.contains(s2));
        assertTrue(false);
    }

    @Test
    public void readoutsNotConnectedToSensorNotReturnedWhenSearchingBySensor() {
        sensorService.save(s);
        readoutService.save(s3);

        List<Readout> readReadouts = readoutService.getBy(s);
        assertFalse(readReadouts.contains(s3));
    }

}
