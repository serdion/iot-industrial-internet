/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fi.iot.iiframework.database.Saveable;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "sensor")
@Data
@EqualsAndHashCode(exclude = {"readouts"})
@ToString(exclude = {"readouts", "device"})
public class Sensor implements Saveable<String> {

    @Id
    @XmlAttribute
    protected String id;

    @JsonIgnore
    @XmlElement(name = "readout")
    @XmlElementWrapper(name = "readouts")
    @OneToMany(fetch = FetchType.LAZY)
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.REMOVE})
    @JoinColumn(name = "sensor")
    protected Set<Readout> readouts;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "device", nullable = false, updatable = false)
    @Cascade({CascadeType.SAVE_UPDATE})
    protected Device device;

    public Sensor() {
    }

    public Sensor(String id) {
        this.id = id;
    }

    public void afterUnmarshal(Unmarshaller u, Object parent) {
        this.device = (Device) parent;
    }
}
