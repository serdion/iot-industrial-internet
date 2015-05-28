/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services;

import fi.iot.iiframework.application.TestConfig;
import fi.iot.iiframework.dataobject.Device;
import fi.iot.iiframework.services.dataobject.DeviceService;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestConfig.class})
public class DeviceServiceTest {

    Device d1;
    Device d2;

    @Autowired
    private DeviceService service;

    @Before
    public void setUp() {
        d1 = new Device();
        d1.setId("ssds");
        d1.setDeviceid("ss");
    }

    @Test
    public void anEntityCanBeSavedAndRetrievedFromDatabase() {
        service.save(d1);
        Device d2 = service.get(d1.getId());
        assertEquals(d1.getId(), d2.getId());
        assertEquals(d1.getDeviceid(), d2.getDeviceid());
    }

}
