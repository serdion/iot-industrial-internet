/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi;

import fi.iot.iiframework.restapi.filters.After;
import fi.iot.iiframework.restapi.filters.EqualsErrorSeverity;
import fi.iot.iiframework.restapi.filters.EqualsErrorType;
import fi.iot.iiframework.restapi.filters.LessThan;
import fi.iot.iiframework.restapi.filters.MoreThan;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CriterionFactoryTest {
    
    private CriterionFactory factory;
    
    private Map<String, String> acceptedReadoutFilters;
    private Map<String, String> acceptedSysErrorFilters;
    
    private List<Criterion> readoutCriterion;
    private List<Criterion> sysErrorCriterion;
    
    @Before
    public void setUp() {
        factory = new CriterionFactory();
        
        acceptedReadoutFilters = new HashMap<>();
        acceptedSysErrorFilters = new HashMap<>();
        
        initAcceptedReadoutFilters();
        initAcceptedSysErrorFilters();
        
        readoutCriterion = factory.getReadoutCriterion(acceptedReadoutFilters);
        sysErrorCriterion = factory.getSysErrorCriterion(acceptedSysErrorFilters);
    }
    
    private void initAcceptedReadoutFilters() {
        acceptedReadoutFilters.put("unit", "U");
        acceptedReadoutFilters.put("quantity", "Q");

        acceptedReadoutFilters.put("more", "10.0");
        acceptedReadoutFilters.put("less", "10.0");

        acceptedReadoutFilters.put("after", "123456");
        acceptedReadoutFilters.put("before", "123456");
    }
    
    private void initAcceptedSysErrorFilters() {
        acceptedSysErrorFilters.put("type", "T");

        acceptedSysErrorFilters.put("after", "123456");
        acceptedSysErrorFilters.put("before", "123456");

        acceptedSysErrorFilters.put("severity", "SEVERITY");
    }

    @Test
    public void testReadoutCriterionCreation() {
        for (Criterion crit : readoutCriterion) {
            // TODO
        }
    }

    @Test
    public void testSysErrorCriterionCreation() {

    }
    
}
