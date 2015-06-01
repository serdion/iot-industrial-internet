/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.errors;

public enum ErrorSeverity {

    FATAL(10),
    HIGH(7),
    MEDIUM(5),
    LOW(3),
    NOTIFICATION(1);

    private final int severity;

    ErrorSeverity(int severity) {
        this.severity = severity;
    }

    public int getSeverity() {
        return this.severity;
    }
    
    
}
