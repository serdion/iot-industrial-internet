/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.errors;

public enum ErrorSeverity {

    ENDOFTHEWORLD(1000),
    HIGH(10),
    MEDIUM(8),
    LOW(6),
    NOTIFICATION(1);

    private final int severity;

    ErrorSeverity(int severity) {
        this.severity = severity;
    }

}
