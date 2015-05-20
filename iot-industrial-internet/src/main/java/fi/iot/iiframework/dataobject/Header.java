/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.iot.iiframework.dataobject;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ooppa
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Header {
    @XmlElement
    protected String response;
    
    @XmlElement
    protected int uptime;

    public Header() {
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String responce) {
        this.response = responce;
    }

    public int getUptime() {
        return uptime;
    }

    public void setUptime(int uptime) {
        this.uptime = uptime;
    }
    
}
