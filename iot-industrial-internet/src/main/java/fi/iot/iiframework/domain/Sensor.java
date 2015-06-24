/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name = "sensors")
@ToString(exclude = {"readouts", "source"})
@JsonIgnoreProperties("numberOfReadouts")
public class Sensor implements Serializable {

    @Id
    @GeneratedValue
    protected Long id;

    protected String name;

    @JsonIgnore
    @OneToMany(mappedBy = "sensor", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Cascade(CascadeType.SAVE_UPDATE)
    @LazyCollection(LazyCollectionOption.EXTRA)
    protected Set<Readout> readouts;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source", nullable = false, updatable = false)
    protected InformationSource source;

    protected String quantity;

    protected String unit;

    protected boolean active;

    protected Double thresholdMax;

    protected Double thresholdMin;

    public Sensor() {
        active = true;
    }

    public Sensor(String name) {
        this.name = name;
    }

    protected void setReadouts(Set<Readout> readouts) {
        this.readouts = readouts;
    }

    public Set<Readout> getReadouts() {
        return readouts;
    }

    @JsonProperty(required = false)
    public long numberOfReadouts() {
        return readouts.size();
    }
    
    public void addReadout(Readout readout) {
        if (readouts == null) {
            readouts = new HashSet<>();
        }
        readout.setSensor(this);
        readouts.add(readout);
    }

    public void addReadouts(Collection<Readout> readouts) {
        List<Readout> readoutsToAdd = new ArrayList<>();
        readouts.forEach(r -> readoutsToAdd.add(r));
        readoutsToAdd.forEach(r -> addReadout(r));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        if (this.source != null) {
            if (this.source.id != null) {
                hash = 97 * hash + Objects.hashCode(this.source.id);
            }
        }
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
        return (Objects.equals(this.source, other.source));
    }

}
