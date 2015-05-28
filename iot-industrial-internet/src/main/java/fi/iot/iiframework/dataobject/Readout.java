/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.dataobject;

import fi.iot.iiframework.database.Saveable;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "readout")
@Data
@EqualsAndHashCode(exclude = {"sensor"})
@ToString(exclude = {"sensor"})
public class Readout implements Saveable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @XmlAttribute
    @NotNull
    protected String time;

    @XmlAttribute
    @NotNull
    protected double value;

    @XmlAttribute
    @NotNull
    protected String unit;

    @XmlAttribute
    @NotNull
    protected String quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sensor")
    protected Sensor sensor;

    public Readout() {
    }

    public Readout(String time, double value, String unit, String quantity) {
        this.time = time;
        this.value = value;
        this.unit = unit;
        this.quantity = quantity;
    }

    /**
     * Returns the time of this Readout as Java Date.
     *
     * @return Date
     */
    public Date getTimeAsDate() {
        long timestamp = Long.parseLong(time);
        return new Date(timestamp);
    }
}
