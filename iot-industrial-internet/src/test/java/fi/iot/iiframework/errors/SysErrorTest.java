/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.errors;

import java.util.Date;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SysErrorTest {

    private static SysError[] testErrors;
    private static Date now;

    @BeforeClass
    public static void setUpClass() {
        now = new Date();
        testErrors = new SysError[3];

    }

    @Before
    public void setUp() {
        testErrors[0] = new SysError(ErrorType.BAD_CONFIGURATION, ErrorSeverity.NOTIFICATION, "Hello");
        testErrors[1] = new SysError(ErrorType.TIMEOUT_ERROR, ErrorSeverity.NOTIFICATION, "muh");
        testErrors[2] = new SysError(ErrorType.CONFLICT_ERROR, ErrorSeverity.NOTIFICATION, "muh");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void correctErrorNameAndIdSaved() {
        assertTrue(testErrors[1].getType().getName().equals(ErrorType.TIMEOUT_ERROR.getName()));
        assertTrue(testErrors[0].getType().getId().equals(ErrorType.BAD_CONFIGURATION.getId()));
    }

    @Test
    public void correctDescriptionSaved() {
        assertTrue(testErrors[2].getDescription().equals("muh"));
    }

    @Test
    public void changedTypeSaved() {
        testErrors[0].setType(ErrorType.NOT_FOUND);
        assertTrue(testErrors[0].getType() == ErrorType.NOT_FOUND);
    }

    @Test
    public void changedDescpritionSaved() {
        testErrors[2].setDescription("test");
        assertTrue(testErrors[2].getDescription().equals("test"));
    }

}
