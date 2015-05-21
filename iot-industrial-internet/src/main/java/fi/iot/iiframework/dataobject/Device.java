/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.iot.iiframework.dataobject;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.*;

/**
 *
 * @author ooppa
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "device")
@Entity
public class Device implements Serializable {

    @XmlAttribute
    @Id
    protected String id;

    @XmlAttribute
    @NotNull
    protected boolean status;

    @XmlElement(name = "sensor")
    @XmlElementWrapper(name = "sensors")
    @OneToMany
    @JoinTable(
            name = "DEVICE_SENSORS",
            joinColumns = @JoinColumn(name = "DEVICE_ID"),
            inverseJoinColumns = @JoinColumn(name = "SENSOR_ID")
    )
    protected List<Sensor> sensors;

    public Device() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

}
