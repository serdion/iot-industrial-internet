/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.iot.iiframework.errors;

import java.util.Date;




/**
 * Contains information about an error
 */
public class Error {

    private ErrorType type;
    private Date time;
    private String description;

    /**
     *
     * @param type ErrorType of error
     * @param date Date of error
     * @param desc Description of error, optional.
     */
    public Error(ErrorType type, Date date, String desc) {
        this.type = type;
        this.time = date;
        this.description = desc;

    }

    /**
     *
     * @param type ErrorType of error
     * @param date Date of error
     */


    public void setType(ErrorType type) {
        this.type = type;
    }

    public void setDate(Date newtime) {
        this.time = newtime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ErrorType getType() {
        return type;
    }

    public Date getDate() {
        return time;
    }

    public String getDescription() {
        return description;
    }
    
    
    

}
