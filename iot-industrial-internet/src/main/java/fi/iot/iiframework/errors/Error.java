/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.iot.iiframework.errors;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Contains information about an error
 */
@Entity
@Table(name = "Errors")

public class Error implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "type")
    private ErrorType type;
    
    @Column(name = "errordate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date time;
    
    @Column(name = "description")
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

    public Error() {
        // Hibernate requires an empty constructor
    }

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
