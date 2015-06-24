/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.mutator;

import fi.iot.iiframework.application.TestConfig;
import fi.iot.iiframework.domain.ReadoutFlag;
import fi.iot.iiframework.domain.Sensor;
import fi.iot.iiframework.domain.Readout;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestConfig.class})
public class MutatorTest {

    private Set<Sensor> sensors;
    private Set<Readout> readouts;
    private Sensor testsensor;

    public MutatorTest() {
    }

    @Before
    public void setUp() {
        sensors = new HashSet<>();
        testsensor = new Sensor();

        readouts = new HashSet<>();

        testsensor.setActive(true);
        testsensor.setThresholdMax(20000d);
        testsensor.setThresholdMin(-1000d);

        testsensor.addReadout(new Readout(9, -500, testsensor));
        testsensor.addReadout(new Readout(10, 0, testsensor));
        testsensor.addReadout(new Readout(11, 10, testsensor));
        testsensor.addReadout(new Readout(12, 50, testsensor));
        testsensor.addReadout(new Readout(13, 100, testsensor));
        testsensor.addReadout(new Readout(14, 1000, testsensor));
        
        sensors.add(testsensor);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void spotsValueThatIsTooHigh() {
        double max = 50;
        testsensor.setThresholdMax(max);

        new MarkReadoutAsErronousIfValueIs(ValueCondition.HIGHER_THAN).mutateAll(testsensor);
        new MarkReadoutAsErronousIfValueIs(ValueCondition.LOWER_THAN).mutateAll(testsensor);

        readouts = testsensor.getReadouts();

        for (Readout r : readouts) {
            if (r.getFlag() == ReadoutFlag.TOO_HIGH_VALUE) {
                assertTrue("Value " + r.getValue() + " was marked as too high when limit was " + max, r.getValue() > max);
            } else {
                assertTrue("Value " + r.getValue() + " was not marked as too high when limit was " + max, r.getValue() <= max);
            }
        }
    }

    @Test
    public void spotsValueThatIsTooLow() {
        double min = -60;
        testsensor.setThresholdMin(min);

        new MarkReadoutAsErronousIfValueIs(ValueCondition.HIGHER_THAN).mutateAll(testsensor);
        new MarkReadoutAsErronousIfValueIs(ValueCondition.LOWER_THAN).mutateAll(testsensor);

        readouts = testsensor.getReadouts();

        for (Readout r : readouts) {

            if (r.getFlag() == ReadoutFlag.TOO_LOW_VALUE) {
                assertTrue("Value " + r.getValue() + " was marked as too low when limit was " + min, r.getValue() < min);
            } else {
                assertTrue("Value " + r.getValue() + " was not marked as too low when limit was " + min, r.getValue() > min);
            }
        }
    }

    @Test
    @Ignore
    public void thresholdsUnsetNoFlagsAreSet() {
        testsensor.setThresholdMax(null);
        testsensor.setThresholdMin(null);

        new MarkReadoutAsErronousIfValueIs(ValueCondition.HIGHER_THAN).mutateAll(testsensor);
        new MarkReadoutAsErronousIfValueIs(ValueCondition.LOWER_THAN).mutateAll(testsensor);

        readouts = testsensor.getReadouts();

        for (Readout r : readouts) {
            assertTrue("A flag was set when thresholds were null!", r.getFlag() == ReadoutFlag.EMPTY);
        }
    }
}
