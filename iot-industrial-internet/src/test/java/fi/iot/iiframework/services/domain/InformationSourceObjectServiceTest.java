/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services.domain;

import fi.iot.iiframework.application.TestConfig;
import fi.iot.iiframework.domain.InformationSourceObject;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@TransactionConfiguration(defaultRollback = true)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestConfig.class})
public class InformationSourceObjectServiceTest {

    InformationSourceObject dso1;
    InformationSourceObject dso2;

    @Autowired
    private InformationSourceObjectService service;

    @Before
    public void setUp() {
        dso1 = new InformationSourceObject();
        dso1.setId("ssds");
        dso2 = new InformationSourceObject();
        dso2.setId("ssds2");
        
        dso1.setName("test");
        
        service.save(dso1);
        service.save(dso2);
    }

    @Test
    public void aDataSourceObjectCanBeSavedAndRetrievedFromDatabase() {
        InformationSourceObject dso3 = service.get(dso1.getId());
        assertEquals(dso1.getId(), dso3.getId());
    }
    
    @Test
    public void anIdIsGeneratedAutomaticallyWhenSaved() {
        assertNotNull(dso1.getId());
    }

    @Test
    public void sourceCanBeSavedAndRetrieved() {
        assertEquals(dso1, service.get(dso1.getId()));
    }

    @Test
    public void allInformationSourceObjectsCanBeRetrieved() {
        List<InformationSourceObject> sources = service.getAll();

        assertTrue(sources.contains(dso1));
        assertTrue(sources.contains(dso2));
    }

    @Test
    public void sourcesCanBeFoundFromIndexToIndex() {
        List<InformationSourceObject> sources = service.get(0, 0);

        assertEquals(1, sources.size());
    }
    
    @Test
    public void sourcesCanBeCounted() {
        assertEquals(2, (long) service.count());
    }
    
    @Test
    public void sourcesCanBeFiltered() {
        List<Criterion> criterions = new ArrayList<>();
        Criterion c1 = Restrictions.eq("name", "test");
        criterions.add(c1);
        assertEquals(1, (long) service.getBy(0, 1, criterions).size());
    }
}