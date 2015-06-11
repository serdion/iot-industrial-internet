/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import org.hibernate.annotations.GenericGenerator;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "sensor")
@Data
@ToString(exclude = {"readouts", "sensorConfiguration", "source"})
public class Sensor implements Serializable {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    protected String id;

    @XmlAttribute(name = "name")
    protected String name;

    @JsonIgnore
    @XmlElement(name = "readout")
    @XmlElementWrapper(name = "readouts")
    @OneToMany(mappedBy = "sensor", fetch = FetchType.LAZY)
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.REMOVE})
    protected Set<Readout> readouts;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "source", nullable = false, updatable = false)
    @Cascade({CascadeType.SAVE_UPDATE})
    protected InformationSource source;
    
    @JsonIgnore
    @OneToOne(targetEntity = SensorConfiguration.class, fetch = FetchType.EAGER)
    protected SensorConfiguration sensorConfiguration;
    
    @XmlAttribute
    protected String quantity;
    
    @XmlAttribute
    protected String unit;

    public Sensor() {
    }

    public Sensor(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.source);
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
        final Sensor other = (Sensor) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return this.source.id.equals(other.source.id);
    }
    
    
}
