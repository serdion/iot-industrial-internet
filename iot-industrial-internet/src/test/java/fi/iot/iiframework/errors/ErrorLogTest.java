/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.errors;

import java.util.ArrayList;
import java.util.Date;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ErrorLogTest {

    private ErrorLog testLog;

    @Before
    public void setUp() {
        testLog = new ErrorLog();
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
