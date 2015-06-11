/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.errors;

/**
 * Defines the various types of errors in the system.
 */
public enum ErrorType {

    NOT_FOUND("Not found", "NOT_FOUND"),
    IO_ERROR("IO Error", "IO_ERROR"),
    BAD_REQUEST("Bad request", "BAD_REQUEST"),
    BAD_CONFIGURATION("Bad configuration", "BAD_CONFIGURATION"),
    NOT_ACCEPTED("Not accepted", "NOT_ACCEPTED"),
    TIMEOUT_ERROR("Request timeout", "TIMEOUT_ERROR"),
    CONFLICT_ERROR("Conflict", "CONFLICT_ERROR"),
    PARSE_ERROR("Parse error", "PARSE_ERROR"),
    READ_ERROR("Read error", "READ_ERROR"),
    INVALID_OBJECT("Invalid Object", "INVALID_OBJECT"),
    ERRONOUS_DATA("Erronous Data", "ERRONOUS_DATA"),
    UNKNOWN_ERROR("Unknown error", "UNKNOWN_ERROR");

    private final String name;
    private final String id;

    private ErrorType(String name, String id) {
        this.name = name;
        this.id = id;
    }

    /**
     * Returns the ID of the ErrorType
     *
     * @return ID as a String
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the ErrorType
     *
     * @return Name as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Returns ErrorType from the given ID.
     *
     * @param id ID of the ErrorType
     * @return ErrorType
     */
    public static ErrorType getType(String id) {
        ErrorType[] values = ErrorType.values();

        for (ErrorType value : values) {
            if (value.getId().equals(id)) {
                return value;
            }
        }

        return ErrorType.UNKNOWN_ERROR;
    }

    @Override
    public String toString() {
        return getId();
    }

}
