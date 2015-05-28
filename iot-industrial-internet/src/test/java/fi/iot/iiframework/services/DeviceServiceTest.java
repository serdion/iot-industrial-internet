/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services;

import fi.iot.iiframework.application.TestConfig;
import fi.iot.iiframework.dataobject.DataSourceObject;
import fi.iot.iiframework.dataobject.Device;
import fi.iot.iiframework.dataobject.Sensor;
import fi.iot.iiframework.services.dataobject.DeviceService;
import java.io.Serializable;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestConfig.class})
public class DeviceServiceTest implements Serializable {

    Device d1;
    Device d2;

    @Autowired
    private DeviceService service;

    @Before
    public void setUp() {
        d1 = new Device();
        d1.setId("ssds");
    }

    @Test
    public void anEntityCanBeSavedAndRetrievedFromDatabase() {
        service.save(d1);
        Device d2 = service.get(d1.getId());
        d2.setId("ssds");
        assertEquals(d1.getId(), d2.getId());
        assertEquals(d1.getDeviceid(), d2.getDeviceid());
        assertEquals(d1.getSensors(), d2.getSensors());
        assertEquals(d1.getDataSourceObject(), d2.getDataSourceObject());
    }

}
