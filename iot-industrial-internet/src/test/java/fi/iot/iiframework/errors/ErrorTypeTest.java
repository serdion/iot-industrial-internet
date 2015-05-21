/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.errors;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ErrorTypeTest {

    @Test
    public void returnsUnknownErrorIfNoProperID() {
        assertEquals(ErrorType.UNKNOWN_ERROR, ErrorType.getType("sddss"));
    }
    
}
