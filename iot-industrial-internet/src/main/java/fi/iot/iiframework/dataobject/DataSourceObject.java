/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fi.iot.iiframework.database.Saveable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "datasource")
@Entity
@Table(name = "datasource")
@Data
@EqualsAndHashCode(exclude = {"devices", "header"})
@ToString(exclude = {"devices", "header"})
public class DataSourceObject implements Saveable<String> {    
    @XmlAttribute
    @Id
    protected String id;
    
    protected String datasourceid;
    
    @XmlElement
    @Transient
    protected Header header;
    
    @JsonIgnore
    @XmlElementWrapper(name = "devices")
    @XmlElement(name = "device")
    @OneToMany(fetch = FetchType.LAZY)
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.REMOVE})
    @JoinColumn(name = "source")
    protected Set<Device> devices;    
}
