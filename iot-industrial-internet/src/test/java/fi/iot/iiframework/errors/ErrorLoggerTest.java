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

    public ErrorLoggerTest() {
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
        ErrorLogger.log(ErrorType.TEST_ERROR, ErrorSeverity.NOTIFICATION, "TEST_ERROR");
        List<SysError> allErrors = ErrorLogger.getAllErrors();
        assertTrue(allErrors.get(sizeOfErrorList).getDescription().equalsIgnoreCase("no description"));
    }

    @Test
    public void newErrorISCreatedWithPresetError() {

        int sizeOfErrorList = ErrorLogger.getAllErrors().size();
        SysError e = new SysError(ErrorType.TEST_ERROR, ErrorSeverity.NOTIFICATION, "I was added directly...");
        ErrorLogger.log(e);

        List<SysError> allErrors = ErrorLogger.getAllErrors();
        assertTrue(allErrors.get(sizeOfErrorList).getDescription().equalsIgnoreCase("I was added directly..."));
    }
    
    @Test
    public void newErrorTimeIsSaved() {
        ErrorLogger.log(ErrorType.TEST_ERROR, ErrorSeverity.NOTIFICATION, "TEST_ERROR");
        assertNotNull(ErrorLogger.getAllErrors().get(0).getTime());
        
        Date now = new Date();
        ErrorLogger.getAllErrors().get(0).setTime(now);
        Date nowtest = ErrorLogger.getAllErrors().get(0).getTime();
        System.out.println("Now: " + now + " Now in db: " + nowtest);

    }

}
