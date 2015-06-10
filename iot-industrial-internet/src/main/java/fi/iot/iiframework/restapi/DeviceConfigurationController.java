/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi;

import fi.iot.iiframework.application.ApplicationSettings;
import fi.iot.iiframework.domain.Device;
import fi.iot.iiframework.domain.DeviceConfiguration;
import fi.iot.iiframework.restapi.exceptions.InvalidObjectException;
import fi.iot.iiframework.restapi.exceptions.InvalidParametersException;
import fi.iot.iiframework.restapi.exceptions.ResourceNotFoundException;
import fi.iot.iiframework.services.domain.DeviceConfigurationService;
import fi.iot.iiframework.services.domain.DeviceService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("1.0/configurations/devices")
public class DeviceConfigurationController {
    
    @Autowired
    private RestAPIHelper helper;

    @Autowired
    private ApplicationSettings settings;
    
    @Autowired
    private DeviceConfigurationService deviceConfigurationService;
    
    @Autowired
    private DeviceService deviceService;
    
    @RequestMapping(value = "/{deviceid}/view", produces = "application/json")
    @ResponseBody
    public DeviceConfiguration getDeviceConfiguration(
            @PathVariable String deviceid
    ) throws InvalidParametersException, ResourceNotFoundException {
        Device device = (Device) helper.returnOrException(deviceService.get(deviceid));
        return (DeviceConfiguration) helper.returnOrException(deviceConfigurationService.getBy(device));
    }
    
    @RequestMapping(
            value = "/{deviceid}/add",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json"
    )
    @ResponseBody
    public ResponseEntity<DeviceConfiguration> addDeviceConfiguration(
            @PathVariable String deviceid,
            @RequestBody DeviceConfiguration configuration
    ) throws InvalidParametersException, ResourceNotFoundException, InvalidObjectException {
        Device device = (Device) helper.returnOrException(deviceService.get(deviceid));
        configuration.setDevice(device);
        helper.checkIfObjectIsValid(configuration);
        deviceConfigurationService.save(configuration);
        return new ResponseEntity<>(configuration, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/{deviceid}/edit",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json"
    )
    @ResponseBody
    public ResponseEntity<DeviceConfiguration> editDeviceConfiguration(
            @PathVariable String deviceid,
            @RequestBody DeviceConfiguration configuration
    ) throws InvalidParametersException, ResourceNotFoundException, InvalidObjectException {
        Device device = (Device) helper.returnOrException(deviceService.get(deviceid));
        configuration.setDevice(device);
        device.setDeviceConfiguration(configuration);
        
        deviceConfigurationService.save(configuration);
        deviceService.save(device);
        return new ResponseEntity<>(configuration, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/{deviceid}/delete",
            method = RequestMethod.DELETE,
            produces = "application/json"
    )
    @ResponseBody
    public ResponseEntity<DeviceConfiguration> deleteDeviceConfiguration(
            @PathVariable String deviceid
    ) throws InvalidParametersException, ResourceNotFoundException {
        Device device = (Device) helper.returnOrException(deviceService.get(deviceid));
        
        DeviceConfiguration configuration = (DeviceConfiguration) helper.returnOrException(device.getDeviceConfiguration());
        
        deviceConfigurationService.delete(device.getDeviceConfiguration());
        return new ResponseEntity<>(configuration, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/list", produces = "application/json")
    @ResponseBody
    public List<DeviceConfiguration> listDeviceConfigurationsList(
            
    ) throws InvalidParametersException {
        return deviceConfigurationService.get(0, settings.getDefaultDeviceConfigurationsRetrievedFromDatabase());
    }

    @RequestMapping(value = "/list/{amount}", produces = "application/json")
    @ResponseBody
    public List<DeviceConfiguration> listDeviceConfigurationsListAmount(
            @PathVariable int amount
    ) throws InvalidParametersException {
        helper.exceptionIfWrongLimits(0, amount);
        return deviceConfigurationService.get(0, amount);
    }

    @RequestMapping(value = "/list/{to}/{from}", produces = "application/json")
    @ResponseBody
    public List<DeviceConfiguration> listDeviceConfigurationsListFromTo(
            @PathVariable int from,
            @PathVariable int to
    ) throws InvalidParametersException {
        helper.exceptionIfWrongLimits(to, from);
        return deviceConfigurationService.get(to, from);
    }
    
}
