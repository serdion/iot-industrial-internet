/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi;

import fi.iot.iiframework.restapi.filters.CriterionFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.Criterion;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

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
        acceptedSysErrorFilters.put("type", "PARSE_ERROR");

        acceptedSysErrorFilters.put("after", "123456");
        acceptedSysErrorFilters.put("before", "123456");

        acceptedSysErrorFilters.put("severity", "MEDIUM");
    }

    @Test
    public void testReadoutCriterionCreation() {
        ArrayList<String> critStrings = new ArrayList<>();

        for (Criterion crit : readoutCriterion) {
            critStrings.add(crit.toString());
        }

        assertTrue(critStrings.contains("unit=U"));
        assertTrue(critStrings.contains("quantity=Q"));
        assertTrue(critStrings.contains("time<123456"));
        assertTrue(critStrings.contains("time>123456"));
        assertTrue(critStrings.contains("value>10.0"));
        assertTrue(critStrings.contains("value<10.0"));
    }

    @Test
    public void testSysErrorCriterionCreation() {
        ArrayList<String> critStrings = new ArrayList<>();

        for (Criterion crit : sysErrorCriterion) {
            critStrings.add(crit.toString());
        }

        assertTrue(critStrings.contains("severity=MEDIUM"));
        assertTrue(critStrings.contains("errordate<123456"));
        assertTrue(critStrings.contains("errordate>123456"));
        assertTrue(critStrings.contains("type=PARSE_ERROR"));
    }

}
