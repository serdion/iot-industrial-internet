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
@EqualsAndHashCode(exclude = {"readouts", "device"})
@ToString(exclude = {"readouts", "device"})
public class Sensor implements Saveable<String> {

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

    public Sensor(String id,  Set<Readout> readouts) {
        this.id = id;
        this.readouts = readouts;
    }

}
