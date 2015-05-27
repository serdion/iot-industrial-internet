/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.iot.iiframework.dataobject;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "device")
@Entity
public class Device implements Serializable {
    
    @XmlAttribute
    @Id
    protected String id;
    
    protected String deviceid;

    @XmlAttribute
    @NotNull
    protected boolean status;

    @XmlElement(name = "sensor")
    @XmlElementWrapper(name = "sensors")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "DEVICE_SENSORS",
            joinColumns = @JoinColumn(name = "DEVICE_ID", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name = "SENSOR_ID", referencedColumnName="id")
    )
    protected Set<Sensor> sensors;

    public Device() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(Set<Sensor> sensors) {
        this.sensors = sensors;
    }

}
