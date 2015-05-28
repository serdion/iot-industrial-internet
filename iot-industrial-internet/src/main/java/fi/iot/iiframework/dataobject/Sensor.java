/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "sensor")
public class Sensor implements Serializable {

    @Id
    @XmlAttribute
    protected String id;

    protected String sensorid;

    @JsonIgnore
    @XmlElement(name = "readout")
    @XmlElementWrapper(name = "readouts")
    @OneToMany(fetch = FetchType.LAZY)
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.REMOVE})
    @JoinColumn(name = "sensor")
    protected Set<Readout> readouts;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="device")
    protected Device device;

    public Sensor() {
    }

    public Sensor(String id, String sensorid, Set<Readout> readouts) {
        this.id = id;
        this.sensorid = sensorid;
        this.readouts = readouts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSensorid() {
        return sensorid;
    }

    public void setSensorid(String sensorid) {
        this.sensorid = sensorid;
    }

    public Set<Readout> getReadouts() {
        return readouts;
    }

    public void setReadouts(Set<Readout> readouts) {
        this.readouts = readouts;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

}
