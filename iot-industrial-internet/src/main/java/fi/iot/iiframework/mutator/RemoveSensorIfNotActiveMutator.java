/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.mutator;

import fi.iot.iiframework.mutator.cases.Case;
import fi.iot.iiframework.domain.Sensor;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RemoveSensorIfNotActiveMutator implements Mutator {

    @Override
    public void mutate(List<Sensor> sensors){
        Iterator<Sensor> iterator = sensors.iterator();
        
        while(iterator.hasNext()){
            Sensor sensor = iterator.next();
            if (sensor.getSensorConfiguration() == null)
                continue;
            if(!sensor.getSensorConfiguration().isActive()){
                sensors.remove(sensor);
            }
        }
    };

    @Override
    public void mutateIf(List<Sensor> sensor, Case mutateInCase) {
        
    }
}
