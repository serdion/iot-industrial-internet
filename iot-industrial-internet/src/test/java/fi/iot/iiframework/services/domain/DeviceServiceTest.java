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
import fi.iot.iiframework.domain.Device;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
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
public class DeviceServiceTest {

    InformationSourceObject dso1;
    InformationSourceObject dso2;

    Device d1;
    Device d2;
    Device d3;

    @Autowired
    private DeviceService service;

    @Before
    public void setUp() {
        dso1 = InformationSourceObjectProvider.provideInformationSourceObject();
        dso2 = InformationSourceObjectProvider.provideInformationSourceObject();

        d1 = InformationSourceObjectProvider.provideDevice();
        d2 = InformationSourceObjectProvider.provideDevice();
        d3 = InformationSourceObjectProvider.provideDevice();
        
        d1.setSource(dso1);
        d2.setSource(dso1);
        d3.setSource(dso2);
        
        d1.setStatus(true);
        d2.setStatus(true);
        d3.setStatus(false);
        
        service.save(d1);
        service.save(d2);
        service.save(d3);
    }

    @Test
    public void devicesCanBeFoundByDataSourceObject() {
        List<Device> devices = service.getBy(dso1);
        assertTrue(devices.contains(d1));
        assertTrue(devices.contains(d2));
        assertFalse(devices.contains(d3));
        
        devices = service.getBy(dso2);
        assertTrue(devices.contains(d3));
        assertFalse(devices.contains(d1));
        assertFalse(devices.contains(d2));
    }
    
    @Test
    public void anIdIsGeneratedAutomaticallyWhenSaved() {
        service.save(d1);
        assertNotNull(d1.getId());
    }

    @Test
    public void deviceCanBeSavedAndRetrieved() {
        service.save(d1);
        assertEquals(d1, service.get(d1.getId()));
    }

    @Test
    public void allDevicesCanBeRetrieved() {
        List<Device> devices = service.getAll();

        assertTrue(devices.contains(d1));
        assertTrue(devices.contains(d2));
    }

    @Test
    public void devicesCanBeFoundFromIndexToIndex() {
        List<Device> devices = service.get(0, 1);

        assertEquals(2, devices.size());
    }
    
    @Test
    public void devicesCanBeCounted() {
        assertEquals(3, (long) service.count());
    }
    
    @Test
    public void devicesCanBeCountedBySource() {
        assertEquals(2, (long) service.countBy(dso1));
    }
    
    @Test
    public void devicesCanBeFiltered() {
        List<Criterion> criterions = new ArrayList<>();
        Criterion c1 = Restrictions.eq("status", false);
        criterions.add(c1);
        assertEquals(1, (long) service.getBy(0, 2, criterions).size());
    }
}
