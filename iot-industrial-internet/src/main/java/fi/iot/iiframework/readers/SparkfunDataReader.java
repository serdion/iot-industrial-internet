/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.readers;

import fi.iot.iiframework.domain.Sensor;
import fi.iot.iiframework.errors.ErrorLogger;
import fi.iot.iiframework.errors.ErrorSeverity;
import fi.iot.iiframework.errors.ErrorType;
import fi.iot.iiframework.mutator.MarkReadoutAsErronousIfValueIs;
import fi.iot.iiframework.mutator.RemoveSensorIfNotActiveMutator;
import fi.iot.iiframework.mutator.ValueCondition;
import fi.iot.iiframework.parsers.SparkfunDataParser;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author atte
 */
public class SparkfunDataReader implements InformationSourceReader {

    @Override
    public List<Sensor> read(String location) {
        Collection<Sensor> sensorsCol = SparkfunDataParser.parse(location);
        List<Sensor> sensors = Arrays.asList(sensorsCol.toArray(new Sensor[0]));
        
        if (sensors != null) {
            // Remove Sensor if it's not active
            new RemoveSensorIfNotActiveMutator().mutateAll(sensors);
            new MarkReadoutAsErronousIfValueIs(ValueCondition.HIGHER_THAN).mutateAll(sensors);
            new MarkReadoutAsErronousIfValueIs(ValueCondition.LOWER_THAN).mutateAll(sensors);

        } else {
            ErrorLogger.log(ErrorType.PARSE_ERROR, ErrorSeverity.LOW, "Attempted to mutate object that was null.");
        }
        
        return sensors;
    }

}