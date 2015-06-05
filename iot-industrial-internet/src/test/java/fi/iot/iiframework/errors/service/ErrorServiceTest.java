/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.errors.service;

import fi.iot.iiframework.services.errors.ErrorService;
import fi.iot.iiframework.application.TestConfig;
import fi.iot.iiframework.errors.ErrorSeverity;
import fi.iot.iiframework.errors.ErrorType;
import fi.iot.iiframework.errors.SysError;
import java.util.Date;
import java.util.List;
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
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@TransactionConfiguration(defaultRollback = true)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestConfig.class})
public class ErrorServiceTest {

    @Autowired
    ErrorService es;
    SysError e1;
    SysError e2;
    SysError e3;

    public ErrorServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        e1 = new SysError(ErrorType.UNKNOWN_ERROR, ErrorSeverity.NOTIFICATION, "aaaa");
        e2 = new SysError(ErrorType.UNKNOWN_ERROR, ErrorSeverity.NOTIFICATION, "bbbbbbbbbb 31231");
        e3 = new SysError(ErrorType.UNKNOWN_ERROR, ErrorSeverity.NOTIFICATION, "cccc cccc");
        es.save(e1);
        es.save(e2);
        es.save(e3);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void saveToDbAndFind() {
        Boolean found = false;
        Boolean found2 = false;
        int calc = 0;
        List<SysError> list = es.getAll();
        for (SysError e : list) {
            if (e.getDescription().equals("bbbbbbbbbb 31231")) {
                found = true;
            }

            calc++;
        }
        assertTrue("Could not find searched error message, "
                + "seached " + calc + "/" + list.size() + " items in list", found);
    }
}
