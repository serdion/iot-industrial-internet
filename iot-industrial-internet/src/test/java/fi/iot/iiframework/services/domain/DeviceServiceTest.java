/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services.domain;

import fi.iot.iiframework.application.TestConfig;
import fi.iot.iiframework.domain.DataSourceObject;
import fi.iot.iiframework.domain.Device;
import java.util.List;
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
public class DeviceServiceTest {

    DataSourceObject dso1;
    DataSourceObject dso2;

    Device d1;
    Device d2;
    Device d3;

    @Autowired
    private DeviceService service;

    @Before
    public void setUp() {
        dso1 = InformationSourceObjectProvider.provideDataObject();
        dso2 = InformationSourceObjectProvider.provideDataObject();

        d1 = InformationSourceObjectProvider.provideDevice();
        d2 = InformationSourceObjectProvider.provideDevice();
        d3 = InformationSourceObjectProvider.provideDevice();
        
        d1.setSource(dso1);
        d2.setSource(dso1);
        d3.setSource(dso2);
        
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
}
