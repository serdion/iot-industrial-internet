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

    public MarkReadoutAsErronousIfValueIs(ValueCondition condition) {
        this.condition = condition;
    }

    @Override
    public void mutateAll(InformationSource source) {
        for (Sensor sensor : source.getSensors()) {
            mutateOneSensor(sensor);
        }
    }

    public void mutateOneSensor(Sensor sensor) {
        for (Readout readout : sensor.getReadouts()) {
            try {
                if(condition==ValueCondition.HIGHER_THAN){
                    mutateOneReadout(readout, condition, sensor.getThresholdMax());
                } else if (condition==ValueCondition.LOWER_THAN){
                    mutateOneReadout(readout, condition, sensor.getThresholdMin());
                }
                
                mutateOneReadout(readout, condition, 1.0);
            } catch (NullPointerException npe) {
                // No such sensor with this id, first time reading
            }
        }
    }

    public void mutateOneReadout(Readout readout, ValueCondition condition, double theshold) {
        if (isNotDefaultThreshold(theshold)) {
            if (ValueCondition.compare(condition, readout.getValue(), theshold)) {
                addError(readout.getValue(), theshold, condition, readout.getSensor());
                readout.setFlag(ReadoutFlag.TOO_HIGH_VALUE);
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
    
    private boolean isNotDefaultThreshold(double theshold){
        return theshold != Integer.MIN_VALUE || theshold != Integer.MAX_VALUE;
    }

}
