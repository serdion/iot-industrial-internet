/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.errors;

import fi.iot.iiframework.application.TestConfig;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestConfig.class})
public class ErrorLoggerTest {

    private String test1 = "This is just a test error";
    private String test2 = "Hey, this is just a second test error";

    public ErrorLoggerTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void newErrorISCreatedWithoutDescription() {
        int sizeOfErrorList = ErrorLogger.getAllErrors().size();
        ErrorLogger.newError(ErrorType.TEST_ERROR, ErrorSeverity.NOTIFICATION);
        List<SysError> allErrors = ErrorLogger.getAllErrors();
        assertTrue(allErrors.get(sizeOfErrorList).getDescription().equalsIgnoreCase("no description"));
    }

    @Test
    public void newErrorISCreatedWithPresetError() {

        int sizeOfErrorList = ErrorLogger.getAllErrors().size();
        SysError e = new SysError(ErrorType.TEST_ERROR, ErrorSeverity.NOTIFICATION, "I was added directly...");
        ErrorLogger.newError(e);

        List<SysError> allErrors = ErrorLogger.getAllErrors();
        assertTrue(allErrors.get(sizeOfErrorList).getDescription().equalsIgnoreCase("I was added directly..."));
    }
    
    @Test
    public void newErrorTimeIsSaved() {
        ErrorLogger.newError(ErrorType.TEST_ERROR, ErrorSeverity.NOTIFICATION);
        assertNotNull(ErrorLogger.getAllErrors().get(0).getDate());
        
        Date now = new Date();
        ErrorLogger.getAllErrors().get(0).setDate(now);
        Date nowtest = ErrorLogger.getAllErrors().get(0).getDate();
        System.out.println("Now: " + now + " Now in db: " + nowtest);

    }

}
