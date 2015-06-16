/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.domain;

/**
 *
 * @author Teemu
 */
public enum ReadInterval {

    NEVER("never", "NEVER"),
    DAILY("daily", "DAILY"),
    WEEKLY("weekly", "WEEKLY"),
    MONTHLY("monthly", "MONTHLY"),
    OTHER("other", "OTHER");

    private final String name;
    private final String id;

    private ReadInterval(String name, String id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "ReadInterval{" + "id=" + id + '}';
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
