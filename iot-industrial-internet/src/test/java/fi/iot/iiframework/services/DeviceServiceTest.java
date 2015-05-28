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
import java.util.UUID;
import static org.junit.Assert.assertEquals;
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
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestConfig.class})
public class DeviceServiceTest extends GenericServiceTest<Device, String> {

    Device d1;
    Device d2;
    Device d3;

    @Autowired
    private DeviceService deviceService;

    @Before
    public void setUp() {
        service = deviceService;
        s1 = d1 = new Device(UUID.randomUUID().toString());
        s2 = d2 = new Device(UUID.randomUUID().toString());
        s3 = d3 = new Device(UUID.randomUUID().toString());
    }

}
