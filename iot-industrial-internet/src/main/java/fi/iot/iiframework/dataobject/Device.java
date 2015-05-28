/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.iot.iiframework.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fi.iot.iiframework.database.Saveable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "device")
@Entity
@Data
@EqualsAndHashCode(exclude = {"status", "sensors", "source"})
@ToString(exclude = {"status", "sensors", "source"})
public class Device implements Saveable<String> {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source")
    protected DataSourceObject source;

    public Device() {
    }

    public Device(String id) {
        this.id = id;
    }
}