/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.errors.service;

import fi.iot.iiframework.dataobject.Sensor;
import fi.iot.iiframework.errors.dao.ErrorDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class ErrorServiceImpl implements ErrorService {

    @Autowired
    private ErrorDAO errorDAO;

    @Override
    public void add(Error e) {
        errorDAO.save(e);
    }

    @Override
    public void update(Error e) {
        errorDAO.equals(e);
    }

    @Override
    public Error get(String id) {
        Error e  = errorDAO.get(id);
        return e;
    }

    @Override
    public void delete(String id) {
        errorDAO.remove(id);
    }

    @Override
    public List<Error> getAll() {
       List<Error> list =  errorDAO.getAll();
       return list;
    }


}
