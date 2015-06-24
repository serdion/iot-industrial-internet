/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi;

import testingtools.EqualsTester;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class StatObjectTest {

    public StatObjectTest() {
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
    public void testStatObjectEqualsAndHashCode() {
        //StatObject requires an object, we give it a Double.
        Double doubleObject = 2.2;

        EqualsTester<StatObject> tester = EqualsTester.newInstance(new StatObject("testobject", "object for testing", doubleObject));
        tester.assertImplementsEqualsAndHashCode();
        tester.assertEqual(
                new StatObject("testobject", "object for testing", doubleObject),
                new StatObject("testobject", "object for testing", doubleObject));

        tester.assertNotEqual(
                new StatObject("testobject", "object for testing", doubleObject),
                new StatObject("testobject", "object for testing", new Date()));

        tester.assertNotEqual(
                new StatObject("testobject", "object for testing", doubleObject),
                new StatObject("testobjec13123t", "object for testing", doubleObject));

    }
}
