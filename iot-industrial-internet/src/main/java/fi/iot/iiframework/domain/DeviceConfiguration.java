/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "deviceconfigurations")
@Data
@EqualsAndHashCode(exclude = {"id"})
public class DeviceConfiguration implements Serializable, Validatable {

    @Id
    @GeneratedValue
    protected String id;

    @JsonIgnore
    @OneToOne(fetch =  FetchType.LAZY, orphanRemoval = true)
    protected Device device;
    
    /*
        Actual configurations below, add more if needed
    */
    
    protected boolean active = true;

    @Override
    public boolean isValid() {
        return device!=null;
    }
    
    
    
}
