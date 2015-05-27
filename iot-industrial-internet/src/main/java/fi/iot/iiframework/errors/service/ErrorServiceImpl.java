/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.errors.service;

import fi.iot.iiframework.errors.SysError;
import fi.iot.iiframework.errors.dao.ErrorDAO;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class ErrorServiceImpl implements ErrorService {

    @Autowired
    private ErrorDAO errorDAO;

    @Override
    public void add(SysError e) {
        errorDAO.save(e);
    }

    @Override
    public void update(SysError e) {
        errorDAO.equals(e);
    }

    @Override
    public SysError get(String id) {
        SysError e  = errorDAO.get(id);
        return e;
    }

    @Override
    public void delete(String id) {
        errorDAO.remove(id);
    }

    @Override
    public List<SysError> getAll() {
       List<SysError> list =  errorDAO.getAll();
       return list;
    }


}
