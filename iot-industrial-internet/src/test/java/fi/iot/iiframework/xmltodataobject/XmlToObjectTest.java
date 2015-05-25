/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.xmltodataobject;

import fi.iot.iiframework.dataobject.DataSourceObject;
import fi.iot.iiframework.dataobject.Device;
import fi.iot.iiframework.dataobject.Readout;
import fi.iot.iiframework.dataobject.Sensor;
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

    static DataSourceObject data;

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
        assertFalse(data.getDevices().isEmpty());
        Device device = (Device) data.getDevices().toArray()[0];
        assertFalse(device.getSensors().isEmpty());
        Sensor sensor = (Sensor) device.getSensors().toArray()[0];
        assertFalse(sensor.getReadouts().isEmpty());
        Readout readout = (Readout) sensor.getReadouts().toArray()[0];
        assertNotNull(readout);
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void malformedUrlThrown() throws JAXBException, MalformedURLException {
        exception.expect(MalformedURLException.class);
        DataSourceObject malformed = convertXml("h://ptpihlaj.users.cs.helsinki.fi/test.xml");

    }

}
