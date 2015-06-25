/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.domain;

import fi.iot.iiframework.errors.ErrorSeverity;
import fi.iot.iiframework.errors.ErrorType;
import fi.iot.iiframework.errors.SysError;
import fi.iot.iiframework.source.InformationSourceType;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import testingtools.EqualsTester;

public class InformationSourceTest {

    public InformationSourceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void informationSourceEqualsAndHashCodeTest() {

        EqualsTester<InformationSource> tester = EqualsTester.newInstance(new InformationSource());
        tester.assertImplementsEqualsAndHashCode();
        tester.assertEqual(
                new InformationSource(),
                new InformationSource());

        InformationSource testSourceXML = new InformationSource();
        InformationSource testSourceJSON = new InformationSource();
        testSourceXML.setType(InformationSourceType.XML);
        testSourceJSON.setType(InformationSourceType.JSON);

        tester.assertNotEqual(
                testSourceXML,
                testSourceJSON);
    }
   
}
