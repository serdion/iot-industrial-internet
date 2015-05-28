/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "datasource")
@Entity
@Table(name = "datasource")
public class DataSourceObject implements Serializable {    
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
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDatasourceid() {
        return datasourceid;
    }

    public void setDatasourceid(String datasourceid) {
        this.datasourceid = datasourceid;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }

}
