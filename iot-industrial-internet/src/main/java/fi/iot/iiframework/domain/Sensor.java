/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "sensor")
@Data
@ToString(exclude = {"readouts", "sensorConfiguration"})
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
    @OneToMany(mappedBy = "sensor", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    protected Set<Readout> readouts = new HashSet<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source", nullable = false, updatable = false)
    protected InformationSource source;

    @JsonIgnore
    @OneToOne(targetEntity = SensorConfiguration.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
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

    /**
     * Returns the SesorConfiguration for the Sensor and if the sensor doesn't
     * have a configuration a default configuration is returned instead.
     *
     * @return SensorConfiguration
     */
    public SensorConfiguration getSensorConfiguration() {
        if (sensorConfiguration == null) {
            return getDefaultSensorConfiguration();
        }

        return sensorConfiguration;
    }

    private SensorConfiguration getDefaultSensorConfiguration() {
        SensorConfiguration configuration = new SensorConfiguration();
        configuration.active = true;
        configuration.thresholdMax = Integer.MAX_VALUE;
        configuration.thresholdMin = Integer.MIN_VALUE;
        configuration.quantity = "Not set";
        configuration.unit = "Not set";

        return configuration;
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
        return (this.source == null ? other.source == null : this.source.id.equals(other.source.id));
    }

}
