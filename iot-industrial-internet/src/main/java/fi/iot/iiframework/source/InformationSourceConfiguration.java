/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import fi.iot.iiframework.restapi.Validatable;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;


/**
 *
 * Configures a data source object based on database configuration information
 */

@Entity
@Table(name = "infosourceconfigs")
public class InformationSourceConfiguration implements Serializable, Validatable {

    /**
     * Information source id
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    protected String id;
    /**
     * Information source name
     */
    @Column(name = "name")
    protected String name;
    /**
     * Information source type xml/mbus/etc
     */
    @Column(name = "type")
    protected InformationSourceType type;
    /**
     * Url read from
     */
    @Column(name = "url")
    protected String url;
    /**
     * How often read (in seconds)
     */
    @Column(name = "readfrequency")
    protected int readFrequency;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InformationSourceType getType() {
        return type;
    }

    public void setType(InformationSourceType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getReadFrequency() {
        return readFrequency;
    }

    public void setReadFrequency(int readFrequency) {
        this.readFrequency = readFrequency;
    }

    @Override
    public boolean isValid() {
        try {
            new URL(url);
        } catch (MalformedURLException ex) {
            return false;
        }

        if(readFrequency<=0){
            return false;
        }

        return true;
    }

}
