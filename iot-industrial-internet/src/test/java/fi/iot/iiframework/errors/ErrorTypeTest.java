/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.errors;

import org.junit.Test;
import static org.junit.Assert.*;

public class ErrorTypeTest {
    
    private void assertType(ErrorType type, String id){
        assertEquals(ErrorType.getType(id), type);
    }

    @Test
    public void returnsUnknownErrorIfNoProperID() {
        assertEquals(ErrorType.UNKNOWN_ERROR, ErrorType.getType("Ooppa.fi"));
    }
    
    @Test
    public void testGetType() {
        ErrorType[] values = ErrorType.values();
        
        for (int i = 0; i < values.length; i++) {
            assertType(values[i], values[i].getId());
        }
    }
    
}
