/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services;

import fi.iot.iiframework.dataobject.DataSourceObject;
import fi.iot.iiframework.daos.DataSourceObjectDAO;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DataSourceObjectServiceImpl implements DataSourceObjectService {
    
    @Autowired
    private DataSourceObjectDAO dsoDAO;

    @Override
    public void add(DataSourceObject dso) {
        dsoDAO.save(dso);
    }

    @Override
    public void update(DataSourceObject dso) {
        dsoDAO.update(dso);
    }

    @Override
    public DataSourceObject get(String id) {
        return dsoDAO.get(id);
    }

    @Override
    public void delete(String id) {
        dsoDAO.remove(id);
    }

    @Override
    public List<DataSourceObject> getAll() {
        return dsoDAO.getAll();
    }

}
