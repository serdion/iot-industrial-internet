/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.daos;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author atte
 * @param <T>
 * @param <ID>
 */
public abstract class GenericHibernateDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {

    private final Class<T> persistentClass;

    public GenericHibernateDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public T save(T t) {
        getSession().saveOrUpdate(t);
        return t;
    }

    /**
     * Read all objects that fulfill the criteria
     * @param criterion
     * @return 
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(Criterion... criterion) {
        return findByCriteriaFromTo(0, Integer.MAX_VALUE, criterion);
    }
    
    /**
     * Read all objects that fulfill the criteria from index from to index to
     * @param from
     * @param to
     * @param criterion
     * @return 
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByCriteriaFromTo(int from, int to, Criterion... criterion) {
        Criteria crit = getSession().createCriteria(getPersistentClass())
                .setFirstResult(from)
                .setMaxResults(to - from);
        for (Criterion c : criterion) {
            crit.add(c);
        }
        return crit.list();
    }


    /**
     * Get an object of type T with the given id.
     * @param id
     * @return 
     */
    @Override
    public T get(ID id) {
        return (T) getSession().get(getPersistentClass(), id);
    }

    /**
     * Get all objects of type T
     * @return 
     */
    @Override
    public List<T> getAll() {
        return findByCriteria();
    }
    
    /**
     * Get all objects of type T from index from to index to.
     * @param from
     * @param to
     * @return 
     */
    @Override
    public List<T> get(int from, int to) {
        return findByCriteriaFromTo(from, to);
    }

    /**
     * Removes an object from the database.
     * @param t 
     */
    @Override
    public void remove(T t) {
        getSession().delete(t);
    }
    
    public void flush() {
        getSession().flush();
    }
    
    public void clear() {
        getSession().clear();
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Class<T> getPersistentClass() {
        return persistentClass;
    }

}
