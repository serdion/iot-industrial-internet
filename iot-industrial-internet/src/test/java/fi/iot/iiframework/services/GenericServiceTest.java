/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services;

import fi.iot.iiframework.application.TestConfig;
import fi.iot.iiframework.database.Saveable;
import java.io.Serializable;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author atte
 * @param <T>
 * @param <ID>
 */
@TransactionConfiguration(defaultRollback = true)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestConfig.class})
public abstract class GenericServiceTest<T extends Saveable, ID extends Serializable> {

    T s1;
    T s2;
    T s3;

    GenericService service;

    @Test
    public void objectCanBeSavedAndRetrievedFromDatabase() {
        service.save(s1);
        assertEquals(s1, service.get(s1.getId()));
    }

    @Test
    public void objectsCanBeDeleted() {
        service.save(s1);
        service.save(s2);
        service.delete(s1);

        assertFalse(service.getAll().contains(s1));
        assertEquals(1, service.getAll().size());
    }

    @Test
    public void allObjectsCanBeRead() {
        service.save(s1);
        service.save(s2);
        service.save(s3);

        List<T> objects = service.getAll();
        assertTrue(objects.contains(s1));
        assertTrue(objects.contains(s2));
        assertTrue(objects.contains(s3));
        assertEquals(3, objects.size());
    }

    @Test
    public void objectsCanBeReadFromDesiredIndexToAnotherIndex() {
        service.save(s1);
        service.save(s2);
        service.save(s3);

        List<T> objects = service.get(1, 2);
        assertFalse(objects.contains(s1));
        assertTrue(objects.contains(s2));
        assertTrue(objects.contains(s3));
        assertEquals(2, objects.size());
    }
}
