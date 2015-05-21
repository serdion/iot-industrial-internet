/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.xmltodataobject;

import fi.iot.iiframework.dataobject.DataObject;
import static fi.iot.iiframework.xmltodataobject.XmlToObject.convertXml;
import java.net.MalformedURLException;
import javax.xml.bind.JAXBException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class XmlToObjectTest {

    static DataObject data;

    public XmlToObjectTest() {
    }

    @BeforeClass
    public static void setUpClass() throws JAXBException, MalformedURLException {
        data = convertXml("http://ptpihlaj.users.cs.helsinki.fi/test.xml");
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
    public void headerUptimeReadCorrectly() {
        assertEquals(data.getHeader().getUptime(), 1725);
    }

    @Test
    public void firstReadoutUnitReadCorrectly() {
        assertTrue(data.getDevices().get(0).getSensors().get(0).getReadouts().get(0).getQuantity().equals("Temperature"));
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void malformedUrlThrown() throws JAXBException, MalformedURLException {
        exception.expect(MalformedURLException.class);
        DataObject malformed = convertXml("h://ptpihlaj.users.cs.helsinki.fi/test.xml");

    }

}
