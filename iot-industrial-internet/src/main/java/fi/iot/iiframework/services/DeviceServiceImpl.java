/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services;

import fi.iot.iiframework.dataobject.Device;
import fi.iot.iiframework.daos.DeviceDAO;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {
    
    @Autowired
    private DeviceDAO deviceDAO;

    @Override
    public void add(Device device) {
        deviceDAO.save(device);
    }

    @Override
    public void update(Device device) {
        deviceDAO.update(device);
    }

    @Override
    public Device get(String id) {
        return deviceDAO.get(id);
    }

    @Override
    public void delete(String id) {
        deviceDAO.remove(id);
    }

    @Override
    public List<Device> getAll() {
        return deviceDAO.getAll();
    }

}