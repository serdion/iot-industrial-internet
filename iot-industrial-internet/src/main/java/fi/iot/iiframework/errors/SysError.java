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

public class SysError implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "type")
    private ErrorType type;

    @Column(name = "errordate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date time;

    @Column(name = "description")
    private String description;
    
    @Column(name = "severity")
    private ErrorSeverity severity;

    /**
     *
     * @param type ErrorType of error
     * @param severity ErrorSeverity of the error
     * @param desc Description of error
     */
    public SysError(ErrorType type, ErrorSeverity severity, String desc) {
        this.type = type;
        this.description = desc;
        this.severity = severity;
        
        this.time = new Date();

    }



    public SysError() {
        // Hibernate requires an empty constructor
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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
