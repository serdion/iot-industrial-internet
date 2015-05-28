/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.iot.iiframework.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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

    @JsonIgnore
    @XmlElement(name = "sensor")
    @XmlElementWrapper(name = "sensors")
    @OneToMany(fetch = FetchType.LAZY)
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.REMOVE})
    @JoinColumn(name = "device")
    protected Set<Sensor> sensors;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "source")
    protected DataSourceObject source;

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

    public DataSourceObject getSource() {
        return source;
    }

    public void setSource(DataSourceObject source) {
        this.source = source;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.deviceid);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Device other = (Device) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.deviceid, other.deviceid)) {
            return false;
        }
        return true;
    }

}
