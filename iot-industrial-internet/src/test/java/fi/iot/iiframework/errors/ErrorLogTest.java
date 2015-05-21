/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.iot.iiframework.errors;

import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author ptpihlaj
 */
public class ErrorLogTest {

    private ErrorLog testLog;

    public ErrorLogTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        testLog = new ErrorLog();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void newErrorsAreSaved() {
        testLog.newError(ErrorType.CONFLICT_ERROR);
        testLog.newError(ErrorType.IO_ERROR, new Date());
        testLog.newError(ErrorType.TIMEOUT_ERROR, new Date(), "Incredible!");
        assertEquals(testLog.size(), 3);

    }

    @Test
    public void errorLogIsCleared() {
        testLog.newError(ErrorType.CONFLICT_ERROR);
        assertEquals(testLog.size(), 1);

        testLog.clearLog();
        assertEquals(testLog.size(), 0);

    }

    @Test
    public void getErrorByIndexNumber() {
        testLog.newError(ErrorType.CONFLICT_ERROR);
        testLog.newError(ErrorType.IO_ERROR);
        testLog.newError(ErrorType.CONFLICT_ERROR);
        assertTrue(testLog.getError(1).getType().equals(ErrorType.IO_ERROR));
        assertFalse(testLog.getError(0).getType().equals(ErrorType.BAD_CONFIGURATION));

    }

    @Test
    public void getErrorArrayList() {
        testLog.newError(ErrorType.CONFLICT_ERROR);
        testLog.newError(ErrorType.IO_ERROR);
        ArrayList<Error>  list = testLog.getErrorList();
        assertTrue(list.get(0).getType().equals(ErrorType.CONFLICT_ERROR));

    }

}
