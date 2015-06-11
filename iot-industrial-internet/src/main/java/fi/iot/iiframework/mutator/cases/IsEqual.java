/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.mutator.cases;

public class IsEqual implements Case {
    
    private Object a;
    private Object b;

    public IsEqual(Object a, Object b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean isTrue() {
        return a.equals(b);
    }
    
}
