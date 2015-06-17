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

public class ErrorSeverityTest {
    
    @Before
    public void setUp() {
    }
    
    private void assertType(ErrorSeverity severity, String id){
        assertEquals(ErrorSeverity.getType(id), severity);
    }

    @Test
    public void testGetType() {
        ErrorSeverity[] values = ErrorSeverity.values();
        
        for (int i = 0; i < values.length; i++) {
            assertType(values[i], values[i].getId());
        }
    }

    @Test
    public void testCompare() {
        assertEquals(ErrorSeverity.compare(ErrorSeverity.HIGH, ErrorSeverity.LOW), 1);
        assertEquals(ErrorSeverity.compare(ErrorSeverity.LOW, ErrorSeverity.HIGH), -1);
        assertEquals(ErrorSeverity.compare(ErrorSeverity.MEDIUM, ErrorSeverity.MEDIUM), 0);
    }
    
    @Test
    public void testReturnsNoneIfNoProperId(){
        assertEquals(ErrorSeverity.NONE, ErrorSeverity.getType("Ooppa.fi"));
    }
    
}
