/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.mutator;

import fi.iot.iiframework.application.Application;
import fi.iot.iiframework.domain.InformationSource;
import fi.iot.iiframework.domain.Readout;
import fi.iot.iiframework.domain.ReadoutFlag;
import fi.iot.iiframework.domain.Sensor;
import fi.iot.iiframework.errors.ErrorLogger;
import fi.iot.iiframework.errors.ErrorSeverity;
import fi.iot.iiframework.errors.ErrorType;
import fi.iot.iiframework.errors.SysError;
import fi.iot.iiframework.services.domain.SensorService;
import java.util.List;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Autowired;

public class MarkReadoutAsErronousIfValueIs implements Mutator {

    private ValueCondition condition;

    @Autowired
    private SensorService sensorService;

    public MarkReadoutAsErronousIfValueIs(ValueCondition condition) {
        this.condition = condition;
    }

    @Override
    public void mutateAll(List<Sensor> sensors) {
        for (Sensor sensor : sensors) {
            for (Readout readout : sensor.getReadouts()) {
                try {
                    Sensor fetchedSensor = sensorService.get(sensor.getId());

                    // IF condition is "Higher Than" and the maximum threshold is not the default one, else skip
                    if (condition == ValueCondition.HIGHER_THAN && fetchedSensor.getThresholdMax() != Integer.MAX_VALUE) {
                        if (ValueCondition.compare(condition, readout.getValue(), sensor.getThresholdMax())) {
                            addError(readout.getValue(), sensor.getThresholdMax(), condition, sensor);
                            readout.setFlag(ReadoutFlag.TOO_HIGH_VALUE);
                        }
                        // IF condition is "Lower Than" and the minimum threshold is not the default one, else skip
                    } else if (condition == ValueCondition.LOWER_THAN && fetchedSensor.getThresholdMin() != Integer.MIN_VALUE) {
                        if (ValueCondition.compare(condition, readout.getValue(), sensor.getThresholdMin())) {
                            addError(readout.getValue(), sensor.getThresholdMin(), condition, sensor);
                            readout.setFlag(ReadoutFlag.TOO_LOW_VALUE);
                        }
                    }
                } catch (NullPointerException npe) {
                    // No such sensor with this id, first time reading
                }
            }

        }
    }

    private void addError(double readout, double threshold, ValueCondition condition, Sensor sensor) {
        SysError error = new SysError(ErrorType.ERRONOUS_DATA,
                ErrorSeverity.NOTIFICATION,
                "Erronous data was detected because the threshold value "
                + threshold + " was " + condition.getLiteral() + " value " + readout + " found in the sensor.",
                "This error was caused in sensor [id: " + sensor.getId() + "]");
        ErrorLogger.log(error);
    }

    public void mutateAll(InformationSource source) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
