/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fi.iot.iiframework.domain.InformationSourceObject;
import fi.iot.iiframework.domain.Validatable;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


/**
 *
 * Configures a data source object based on database configuration information
 */

@Entity
@Table(name = "informationsources")
@Data
@EqualsAndHashCode(exclude = {"id", "readFrequency"})
public class InformationSourceConfiguration implements Serializable, Validatable {

    /**
     * Information source id.
     */
    @Id
    @GeneratedValue
    protected String id;
    /**
     * Information source name.
     */
    protected String name;
    /**
     * Information source type xml/mbus/etc.
     */
    protected InformationSourceType type;
    /**
     * URL read from.
     */
    protected String url;
    /**
     * Read in intervals
     * TODO: handle this intelligently.
     */
    protected boolean active = true;
    /**
     * How often read (in seconds).
     */
    protected int readFrequency;

    @JsonIgnore
    @OneToOne(fetch =  FetchType.LAZY, mappedBy = "informationSource", orphanRemoval = true)
    @Cascade({CascadeType.DELETE})
    protected InformationSourceObject dataObject;

    
    @Override
    public boolean isValid() {
        try {
            new URL(url);
        } catch (MalformedURLException ex) {
            return false;
        }

        return readFrequency > 0;
    }

}
