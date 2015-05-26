package fi.iot.iiframework.source.dao;


import fi.iot.iiframework.source.InformationSource;
import java.util.List;

/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */

/**
 *
 * @author ptpihlaj
 */
public interface InformationSourceDAO {

    public void save(InformationSource is);

    public InformationSource get(int id);

    public List<InformationSource> getAll();

    public void remove(int id);

    public void update(InformationSource is);
}
