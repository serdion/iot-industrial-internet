/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi;

import fi.iot.iiframework.application.ApplicationSettings;
import fi.iot.iiframework.dataobject.Device;
import fi.iot.iiframework.dataobject.Sensor;
import fi.iot.iiframework.restapi.exceptions.InvalidParametersException;
import fi.iot.iiframework.restapi.exceptions.ResourceNotFoundException;
import fi.iot.iiframework.services.dataobject.DeviceService;
import fi.iot.iiframework.services.dataobject.SensorService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("1.0/sensors")
public class SensorController {
    
    @Autowired
    private DeviceService deviceservice;
    
    @Autowired
    private SensorService sensorservice;
    
    @Autowired
    private RestAPIHelper helper;
    
    @Autowired
    private ApplicationSettings settings;
    
    @RequestMapping(value = "/{sensorid}/view", produces = "application/json")
    @ResponseBody
    public Sensor getSensor(
            @PathVariable String sensorid,
            @RequestParam(required = false) Map<String, String> params
    ) throws ResourceNotFoundException {
        return (Sensor) helper.returnOrException(sensorservice.get(Long.parseLong(sensorid)));
    }

    @RequestMapping(value = "/{deviceid}/list", produces = "application/json")
    @ResponseBody
    public List<Sensor> listSensors(
            @PathVariable String deviceid,
            @RequestParam(required = false) Map<String, String> params
    ) throws ResourceNotFoundException {
        Device device = (Device) helper.returnOrException(deviceservice.get(Long.parseLong(deviceid)));
        return sensorservice.getBy(0, settings.getDefaultAmountOfSensorsRetrievedFromDatabase(), device);
    }

    @RequestMapping(value = "/{deviceid}/list/{amount}", produces = "application/json")
    @ResponseBody
    public List<Sensor> listSensorsAmount(
            @PathVariable String deviceid,
            @PathVariable int amount,
            @RequestParam(required = false) Map<String, String> params
    ) throws ResourceNotFoundException, InvalidParametersException {
        Device device = (Device) helper.returnOrException(deviceservice.get(Long.parseLong(deviceid)));
        helper.exceptionIfWrongLimits(0, amount);
        return sensorservice.getBy(0, amount, device);
    }

    @RequestMapping(value = "/{deviceid}/list/{from}/{to}", produces = "application/json")
    @ResponseBody
    public List<Sensor> listSensorsFromTo(
            @PathVariable String deviceid,
            @PathVariable int from,
            @PathVariable int to,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException, ResourceNotFoundException {
        Device device = (Device) helper.returnOrException(deviceservice.get(Long.parseLong(deviceid)));
        helper.exceptionIfWrongLimits(to, from);
        return sensorservice.getBy(from, to, device);
    }
}
