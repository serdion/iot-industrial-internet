/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.iot.iitframework.dataobject;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ooppa
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "sensor")
public class Sensor {
    @XmlAttribute
    protected String id;
    
    @XmlElement(name = "readout")
    @XmlElementWrapper(name = "readouts")
    protected List<Readout> readouts;

    public Sensor() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Readout> getReadouts() {
        return readouts;
    }

    public void setReadouts(List<Readout> readouts) {
        this.readouts = readouts;
    }

}
