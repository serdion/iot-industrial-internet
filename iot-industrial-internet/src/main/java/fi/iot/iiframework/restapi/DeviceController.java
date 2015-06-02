/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi;

import fi.iot.iiframework.application.ApplicationSettings;
import fi.iot.iiframework.domain.InformationSourceObject;
import fi.iot.iiframework.domain.Device;
import fi.iot.iiframework.restapi.exceptions.InvalidParametersException;
import fi.iot.iiframework.restapi.exceptions.ResourceNotFoundException;
import fi.iot.iiframework.services.domain.DeviceService;
import fi.iot.iiframework.services.domain.InformationSourceObjectService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("1.0/devices")
public class DeviceController {
    
    @Autowired
    private DeviceService deviceservice;
    
    @Autowired
    private RestAPIHelper helper;
    
    @Autowired
    private ApplicationSettings settings;
    
    @Autowired
    private InformationSourceObjectService datasourceservice;
    
    @RequestMapping(value = "/{datasourceid}/list", produces = "application/json")
    @ResponseBody
    public List<Device> listDevices(
            @PathVariable String datasourceid,
            @RequestParam(required = false) Map<String, String> params
    ) throws ResourceNotFoundException {
        InformationSourceObject source = (InformationSourceObject) helper.returnOrException(datasourceservice.get(datasourceid));
        return deviceservice.getBy(0, settings.getDefaultAmountOfDevicesRetrievedFromDatabase(), source);
    }

    @RequestMapping(value = "/{datasourceid}/list/{amount}", produces = "application/json")
    @ResponseBody
    public List<Device> listDevicesAmount(
            @PathVariable String datasourceid,
            @PathVariable int amount,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException, ResourceNotFoundException {
        InformationSourceObject source = (InformationSourceObject) helper.returnOrException(datasourceservice.get(datasourceid));
        helper.exceptionIfWrongLimits(0, amount);
        return deviceservice.getBy(0, amount, source);
    }

    @RequestMapping(value = "/{datasourceid}/list/{from}/{to}", produces = "application/json")
    @ResponseBody
    public List<Device> listDevicesFromTo(
            @PathVariable String datasourceid,
            @PathVariable int from,
            @PathVariable int to,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException, ResourceNotFoundException {
        InformationSourceObject source = (InformationSourceObject) helper.returnOrException(datasourceservice.get(datasourceid));
        helper.exceptionIfWrongLimits(from, to);
        return deviceservice.getBy(to, from, source);
    }

    @RequestMapping(value = "/{deviceid}/view", produces = "application/json")
    @ResponseBody
    public Device getDevice(
            @PathVariable String deviceid,
            @RequestParam(required = false) Map<String, String> params
    ) throws ResourceNotFoundException {
        return (Device) helper.returnOrException(deviceservice.get(Long.parseLong(deviceid)));
    }
}
