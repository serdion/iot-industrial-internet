/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services;

import fi.iot.iiframework.application.TestConfig;
import fi.iot.iiframework.dataobject.DataSourceObject;
import fi.iot.iiframework.dataobject.Header;
import fi.iot.iiframework.services.dataobject.DataSourceObjectService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestConfig.class})
public class DataSourceObjectServiceTest {

    DataSourceObject dso1;
    DataSourceObject dso2;

    @Autowired
    private DataSourceObjectService service;

    @Before
    public void setUp() {
        dso1 = new DataSourceObject();
        dso1.setId("ssds");
    }

    @Test
    public void aDataSourceObjectCanBeSavedAndRetrievedFromDatabase() {
        service.save(dso1);
        DataSourceObject dso2 = service.get(dso1.getId());
        assertEquals(dso1.getId(), dso2.getId());
    }

}