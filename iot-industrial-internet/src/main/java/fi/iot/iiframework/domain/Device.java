/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.iot.iiframework.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "device")
@Entity
@Data
@EqualsAndHashCode(exclude = {"status", "sensors"})
@ToString(exclude = {"status", "sensors", "source"})
public class Device implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    protected String id;

    @XmlAttribute(name = "id")
    protected String deviceId;

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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "source", nullable = false, updatable = false)
    @Cascade({CascadeType.SAVE_UPDATE})
    protected InformationSourceObject source;

    public Device() {
    }

    public Device(String deviceId) {
        this.deviceId = deviceId;
    }

    public void afterUnmarshal(Unmarshaller u, Object parent) {
        this.source = (InformationSourceObject) parent;
    }
}
