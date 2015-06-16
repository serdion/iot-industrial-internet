/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.parsers;

import fi.iot.iiframework.domain.Sensor;
import java.util.Collection;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


public class SparkFunDataParserTest {

    List<Sensor> sensors;

    @Before
    public void setUp() {
        sensors = SparkfunDataParser.parse("https://data.sparkfun.com/output/dZ4EVmE8yGCRGx5XRX1W.json?page=1");
    }

    @Test
    public void sparkFunDataParserFindsSensors() {
        sensors.contains(new Sensor("humidity"));
        sensors.contains(new Sensor("dailyrainin"));
        sensors.contains(new Sensor("tempf"));
    }

    @Test
    public void sensorsGetReadouts() {
        Sensor sensor = sensors.get(0);
        assertTrue(sensor.getReadouts().size() > 10);
    }
    
    @Test
    public void readoutsHaveValueSet() {
        Sensor sensor = sensors.get(0);
        sensor.getReadouts().forEach(r -> {
            assertTrue(r.getValue() != 0);
        });
    }

    @Test
    public void readoutsHaveTimeSet() {
        Sensor sensor = sensors.get(0);
        sensor.getReadouts().forEach(r -> {
            assertTrue(r.getTime() != 0);
        });
    }
}
