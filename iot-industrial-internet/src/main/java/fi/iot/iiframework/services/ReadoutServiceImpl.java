/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services;

import fi.iot.iiframework.dataobject.Readout;
import fi.iot.iiframework.daos.ReadoutDAO;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ReadoutServiceImpl implements ReadoutService {
    
    @Autowired
    private ReadoutDAO readoutDAO;

    @Override
    public void add(Readout readout) {
        readoutDAO.save(readout);
    }

    @Override
    public void update(Readout readout) {
        readoutDAO.update(readout);
    }

    @Override
    public Readout get(Long id) {
        return readoutDAO.get(id);
    }

    @Override
    public void delete(Long id) {
        readoutDAO.remove(id);
    }

    @Override
    public List<Readout> getAll() {
        return readoutDAO.getAll();
    }

}