/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services;

import fi.iot.iiframework.dataobject.Sensor;
import fi.iot.iiframework.daos.SensorDAO;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SensorServiceImpl implements SensorService {
    
    @Autowired
    private SensorDAO sensorDAO;

    @Override
    public void add(Sensor sensor) {
        sensorDAO.save(sensor);
    }

    @Override
    public void update(Sensor sensor) {
        sensorDAO.update(sensor);
    }

    @Override
    public Sensor get(String id) {
        return sensorDAO.get(id);
    }

    @Override
    public void delete(String id) {
        sensorDAO.remove(id);
    }

    @Override
    public List<Sensor> getAll() {
        return sensorDAO.getAll();
    }

}
