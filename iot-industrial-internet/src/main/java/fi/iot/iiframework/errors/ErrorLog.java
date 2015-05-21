/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.iot.iiframework.errors;

import java.util.ArrayList;
import java.util.Date;

/**
 * Stores Errors in an ArrayList
 *
 */
public class ErrorLog {

    private ArrayList<Error> errorLog;

    public ErrorLog() {
        errorLog = new ArrayList<Error>();

    }
    
    /**
     * Creates a new error and places it into the list.
     * @param type
     * @param date optional, if not specified, will use current time
     * @param desc optional
     */

    public void newError(ErrorType type, Date date, String desc) {
        errorLog.add(new Error(type, date, desc));
    }

    public void newError(ErrorType type, Date date) {
        errorLog.add(new Error(type, date, "no description"));
    }

    public void newError(ErrorType type) {
        errorLog.add(new Error(type, new Date(), "no description"));
    }

    public Error getError(int i) {
        return errorLog.get(i);
    }

    public int size() {
        return errorLog.size();
    }

    public ArrayList<Error> getErrorList() {
        return errorLog;
    }
    
    public void clearLog(){
        errorLog.clear();
    }

}
