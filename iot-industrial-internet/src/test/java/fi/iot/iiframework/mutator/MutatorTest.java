/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.mutator;

import fi.iot.iiframework.domain.Readout;
import fi.iot.iiframework.domain.ReadoutFlag;
import fi.iot.iiframework.domain.Sensor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MutatorTest {

    private List<Sensor> sensors;
    private HashSet<Readout> readouts;

    public MutatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        sensors = new ArrayList<>();
        Sensor testsensor = new Sensor();

        readouts = new HashSet<>();

        readouts.add(new Readout(9, -50));
        readouts.add(new Readout(10, 0));
        readouts.add(new Readout(11, 10));
        readouts.add(new Readout(12, 50));
        readouts.add(new Readout(13, 100));
        readouts.add(new Readout(14, 1000));

        testsensor.setActive(true);
        testsensor.setThresholdMax(20000);
        testsensor.setThresholdMin(-1000);
        testsensor.setReadouts(readouts);

        sensors.add(testsensor);
    }

    @After
    public void tearDown() {
    }

//    @Test
    public void spotsValueThatIsTooHigh() {
        double max = 60;
        sensors.get(0).setThresholdMax(max);

        new MarkReadoutAsErronousIfValueIs(ValueCondition.HIGHER_THAN).mutateAll(sensors);
        for (Readout r : readouts) {

            if (r.getFlag() == ReadoutFlag.TOO_HIGH_VALUE) {
                assertTrue("Value " + r.getValue() + " was marked as too high when limit was " + max, r.getValue() > max);
            } else {
                assertTrue("Value " + r.getValue() + " was not marked as too high when limit was " + max, r.getValue() <= max);
            }
        }
    }

//    @Test
    public void spotsValueThatIsTooLow() {
        double min = -20;
        sensors.get(0).setThresholdMax(min);

        new MarkReadoutAsErronousIfValueIs(ValueCondition.HIGHER_THAN).mutateAll(sensors);
        for (Readout r : readouts) {
            if (r.getFlag() == ReadoutFlag.TOO_LOW_VALUE) {
                assertTrue("Value " + r.getValue() + " was marked as too low when limit was " + min, r.getValue() > min);
            } else {
                assertTrue("Value " + r.getValue() + " was not marked as too low when limit was " + min, r.getValue() <= min);
            }
        }
    }
}
