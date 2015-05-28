/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.dataobject;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
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

    @XmlElement(name = "readout")
    @XmlElementWrapper(name = "readouts")
    @OneToMany(fetch = FetchType.EAGER)
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.REMOVE})
    @JoinColumn(name="sensor_id")
    protected Set<Readout> readouts;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "device_id")
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
