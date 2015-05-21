/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.iot.iiframework.dataobject;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "datasource")
@Entity
@Table(name = "datasource")
public class DataObject implements Serializable {    
    @XmlAttribute
    @Id
    protected String id;
    
    @XmlElement
    protected Header header;
    
    @XmlElementWrapper(name = "devices")
    @XmlElement(name = "device")
    @OneToMany
    @JoinTable(
            name = "SOURCE_DEVICES",
            joinColumns = @JoinColumn(name = "SOURCE_ID"),
            inverseJoinColumns = @JoinColumn(name = "DEVICE_ID")
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
