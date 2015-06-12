/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "readout")
@Data
@EqualsAndHashCode(exclude = {"id", "value"})
@ToString(exclude = {"sensor"})
public class Readout implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @XmlAttribute
    @NotNull
    @Column(name = "READOUT_TIME")
    protected long time;

    @XmlAttribute
    @NotNull
    @Column(name = "READOUT_VALUE")
    protected double value;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sensor")
    @Cascade(CascadeType.SAVE_UPDATE)
    protected Sensor sensor;

    public Readout() {
    }

    public Readout(long time, double value) {
        this.time = time;
        this.value = value;
    }

    /**
     * Returns the time of this Readout as Java Date.
     *
     * @return Date
     */
    @JsonIgnore
    public Date getTimeAsDate() {
        return new Date(time);
    }

    public void afterUnmarshal(Unmarshaller u, Object parent) {
        this.sensor = (Sensor) parent;
    }

}
