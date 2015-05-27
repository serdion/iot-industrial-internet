/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.errors;

import java.util.ArrayList;
import java.util.Date;

/**
 * Stores Errors in an ArrayList
 *
 */
public class ErrorLog {

    private ArrayList<SysError> errorLog;

    public ErrorLog() {
        errorLog = new ArrayList<SysError>();

    }
    
    /**
     * Creates a new error and places it into the list.
     * @param type
     * @param date optional, if not specified, will use current time
     * @param desc optional
     */

    public void newError(ErrorType type, Date date, String desc) {
        errorLog.add(new SysError(type, date, desc));
    }

    public void newError(ErrorType type, Date date) {
        errorLog.add(new SysError(type, date, "no description"));
    }

    public void newError(ErrorType type) {
        errorLog.add(new SysError(type, new Date(), "no description"));
    }

    public SysError getError(int i) {
        return errorLog.get(i);
    }

    public int size() {
        return errorLog.size();
    }

    public ArrayList<SysError> getErrorList() {
        return errorLog;
    }
    
    public void clearLog(){
        errorLog.clear();
    }

}
