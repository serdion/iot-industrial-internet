/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.iot.iiframework.dataobject;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "datasource")
@Entity
@Table(name = "datasource")
public class DataObject implements Serializable {    
    @XmlAttribute
    @Id
    protected String id;
    
    @XmlElement
    @Transient
    protected Header header;
    
    @XmlElementWrapper(name = "devices")
    @XmlElement(name = "device")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "SOURCE_DEVICES",
            joinColumns = @JoinColumn(name = "SOURCE_ID", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name = "DEVICE_ID", referencedColumnName="id")
    )
    protected List<Device> devices;

    public DataObject() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

}
