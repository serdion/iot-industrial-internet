/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.errors;

import testingtools.EqualsTester;
import java.util.Date;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SysErrorTest {

    private static SysError[] testErrors;

    @BeforeClass
    public static void setUpClass() {
        testErrors = new SysError[4];

    }

    @Before
    public void setUp() {
        testErrors[0] = new SysError(ErrorType.BAD_CONFIGURATION, ErrorSeverity.NOTIFICATION, "Hello");
        testErrors[1] = new SysError(ErrorType.TIMEOUT_ERROR, ErrorSeverity.NOTIFICATION, "muh");
        testErrors[2] = new SysError(ErrorType.CONFLICT_ERROR, ErrorSeverity.NOTIFICATION, "muh");
        testErrors[3] = new SysError(ErrorType.CONFLICT_ERROR, ErrorSeverity.NOTIFICATION, "muh", "additional info");

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
    public void correctDescriptionNameSeveritySaved() {
        assertTrue(testErrors[2].getDescription().equals("muh"));
        assertTrue(testErrors[2].getName().equals(ErrorType.CONFLICT_ERROR.getName()));
        assertTrue(testErrors[2].getSeverity().equals(ErrorSeverity.NOTIFICATION));
    }

    @Test
    public void correctHashCodes() {
        assertFalse(testErrors[1].hashCode() == testErrors[2].hashCode());
        SysError e = new SysError(ErrorType.CONFLICT_ERROR, ErrorSeverity.NOTIFICATION, "muh");
        SysError e2 = e;
        assertTrue(e.hashCode() == e2.hashCode());
    }

    @Test
    public void changedTypeSaved() {
        testErrors[0].setType(ErrorType.NOT_FOUND);
        assertTrue(testErrors[0].getType() == ErrorType.NOT_FOUND);
    }

    @Test
    public void changedDescritionSaved() {
        testErrors[2].setDescription("test");
        assertTrue(testErrors[2].getDescription().equals("test"));
    }

    @Test
    public void testSysErrorEqualsAndHashCode() {
        
        Date startTime = new Date();
        EqualsTester<SysError> tester = EqualsTester.newInstance(new SysError(ErrorType.UNKNOWN_ERROR, ErrorSeverity.NONE, "test"));
        tester.assertImplementsEqualsAndHashCode();
        tester.assertEqual(
                new SysError(ErrorType.UNKNOWN_ERROR, ErrorSeverity.NONE, "test"),
                new SysError(ErrorType.UNKNOWN_ERROR, ErrorSeverity.NONE, "test"),
                new SysError(ErrorType.UNKNOWN_ERROR, ErrorSeverity.NONE, "test"));
        
        tester.assertNotEqual(
                new SysError(ErrorType.UNKNOWN_ERROR, ErrorSeverity.NONE, "test"),
                new SysError(ErrorType.UNKNOWN_ERROR, ErrorSeverity.NONE, "unequal test"));
        
        tester.assertNotEqual(
                new SysError(ErrorType.UNKNOWN_ERROR, ErrorSeverity.FATAL, "test", startTime),
                new SysError(ErrorType.UNKNOWN_ERROR, ErrorSeverity.NONE, "test", startTime));

    }

}
