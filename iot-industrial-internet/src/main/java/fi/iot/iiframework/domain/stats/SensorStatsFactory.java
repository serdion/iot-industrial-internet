/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.domain.stats;

import fi.iot.iiframework.domain.Sensor;
import fi.iot.iiframework.services.domain.ReadoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SensorStatsFactory {
    @Autowired
    ReadoutService readoutService;
    
    
    
    public SensorStats getStats(Sensor sensor){
        return new SensorStats(readoutService.countBy(sensor));
    }
}
