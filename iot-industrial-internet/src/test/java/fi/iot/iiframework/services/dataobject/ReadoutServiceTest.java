/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services.dataobject;

import fi.iot.iiframework.application.TestConfig;
import fi.iot.iiframework.dataobject.DataSourceObject;
import fi.iot.iiframework.dataobject.Device;
import fi.iot.iiframework.dataobject.Readout;
import fi.iot.iiframework.dataobject.Sensor;
import java.util.Arrays;
import java.util.List;
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
@SpringApplicationConfiguration(classes = {TestConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ReadoutServiceTest {

    @Autowired
    private ReadoutService service;

    Readout r1;
    Readout r2;
    Readout r3;

    Sensor s1;
    Sensor s2;

    @Before
    public void setUp() {
        DataSourceObject dso = DataObjectProvider.provideDataObject();

        Device dev = DataObjectProvider.provideDevice();
        dev.setSource(dso);

        s1 = DataObjectProvider.provideSensor();
        s2 = DataObjectProvider.provideSensor();
        s1.setDevice(dev);
        s2.setDevice(dev);

        r1 = DataObjectProvider.provideReadout();
        r2 = DataObjectProvider.provideReadout();
        r3 = DataObjectProvider.provideReadout();
        r1.setSensor(s1);
        r2.setSensor(s1);
        r3.setSensor(s2);

        service.save(r1);
        service.save(r2);
        service.save(r3);
    }

    @Test
    public void anIdIsGeneratedAutomaticallyWhenSaved() {
        service.save(r1);
        assertNotNull(r1.getId());
    }

    @Test
    public void readoutCanBeSavedAndRetrieved() {
        service.save(r1);
        assertEquals(r1, service.get(r1.getId()));
    }

    @Test
    public void allReadoutsCanBeRetrieved() {
        List<Readout> readouts = service.getAll();

        assertTrue(readouts.contains(r1));
        assertTrue(readouts.contains(r2));
        assertTrue(readouts.contains(r3));
    }

    @Test
    public void readoutsCanBeFoundFromIndexToIndex() {
        List<Readout> readouts = service.get(1, 2);

        assertEquals(2, readouts.size());
    }

    @Test
    public void readoutsCanBeFoundBySensor() {
        List<Readout> readReadouts = service.getBy(s1);

        System.out.println(Arrays.toString(readReadouts.toArray()));
        System.out.println(r1);
        System.out.println(r2);
        assertTrue(readReadouts.contains(r1));
        assertTrue(readReadouts.contains(r2));
    }

    @Test
    public void readoutsNotConnectedToSensorNotReturnedWhenSearchingBySensor() {
        List<Readout> readReadouts = service.getBy(s1);
        assertFalse(readReadouts.contains(r3));
    }

}
