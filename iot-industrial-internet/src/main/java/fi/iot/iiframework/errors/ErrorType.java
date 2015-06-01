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

    NOT_FOUND("Not found", "notfound"),
    IO_ERROR("IO Error", "ioerror"),
    BAD_REQUEST("Bad request", "badrequest"),
    BAD_CONFIGURATION("Bad configuration", "badconfig"),
    NOT_ACCEPTED("Not accepted", "notaccepted"),
    TIMEOUT_ERROR("Request timeout", "timeout"),
    CONFLICT_ERROR("Conflict", "conflict"),
    PARSE_ERROR("Parse error", "parseerror"),
    READ_ERROR("Read error", "readerror"),
    TEST_ERROR("Error for testing", "testerror"),
    INVALID_OBJECT("Object was invalid or wrong type", "invalidobject"),
    UNKNOWN_ERROR("Unknown error", "unknown");

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
