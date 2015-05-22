/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.dataobject.service;

import fi.iot.iiframework.dataobject.Readout;
import java.util.List;

public interface ReadoutService {
    public void add(Readout readout);

    public void update(Readout readout);

    public Readout get(Long id);

    public void delete(Long id);

    public List<Readout> getAll();
}
