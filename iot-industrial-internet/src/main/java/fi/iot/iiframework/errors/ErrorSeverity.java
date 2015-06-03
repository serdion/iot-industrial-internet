/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.errors;

public enum ErrorSeverity {

    NONE(0, "NONE"),
    NOTIFICATION(1, "NOTIFICATION"),
    LOW(2, "LOW"),
    MEDIUM(3, "MEDIUM"),
    HIGH(4, "HIGH"),
    FATAL(5, "FATAL");

    private final int severity;
    private final String id;

    ErrorSeverity(int severity, String id) {
        this.severity = severity;
        this.id = id;
    }

    public int getSeverity() {
        return this.severity;
    }

    public String getId() {
        return id;
    }

    /**
     * Returns ErrorSeverity from the given ID.
     * 
     * @param id Severity identifier
     * @return ErrorSeverity
     */
    public static ErrorSeverity getType(String id) {
        ErrorSeverity[] values = ErrorSeverity.values();

        for (ErrorSeverity value : values) {
            if (value.getId().equals(id)) {
                return value;
            }
        }

        return ErrorSeverity.NONE;
    }

}
