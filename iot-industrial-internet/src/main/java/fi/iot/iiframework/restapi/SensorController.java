/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi;

import fi.iot.iiframework.domain.InformationSource;
import fi.iot.iiframework.domain.Sensor;
import fi.iot.iiframework.restapi.exceptions.InvalidParametersException;
import fi.iot.iiframework.restapi.exceptions.ResourceNotFoundException;
import fi.iot.iiframework.services.domain.InformationSourceService;
import fi.iot.iiframework.services.domain.SensorService;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("1.0/sensors")
public class SensorController {

    @Autowired
    private InformationSourceService sourceService;

    @Autowired
    private SensorService sensorservice;

    @Autowired
    private RestAPIHelper helper;

    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/{sensorid}/view", produces = "application/json")
    @ResponseBody
    public Sensor getSensor(
            @PathVariable String sensorid,
            @RequestParam(required = false) Map<String, String> params
    ) throws ResourceNotFoundException {
        return (Sensor) helper.returnOrException(sensorservice.get(sensorid));
    }

    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/{sourceid}/list", produces = "application/json")
    @ResponseBody
    public Set<Sensor> listSensors(
            @PathVariable String sourceid,
            @RequestParam(required = false) Map<String, String> params
    ) throws ResourceNotFoundException {
        InformationSource source = (InformationSource) helper.returnOrException(sourceService.get(sourceid));
        return source.getSensors();
    }

    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/{sourceid}/list/{amount}", produces = "application/json")
    @ResponseBody
    public List<Sensor> listSensorsAmount(
            @PathVariable String sourceid,
            @PathVariable int amount,
            @RequestParam(required = false) Map<String, String> params
    ) throws ResourceNotFoundException, InvalidParametersException {
        InformationSource source
                = (InformationSource) helper.returnOrException(sourceService.get(sourceid));
        helper.exceptionIfWrongLimits(0, amount);
        return sensorservice.getBy(0, amount, source);
    }

    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/{sourceid}/list/{from}/{to}", produces = "application/json")
    @ResponseBody
    public List<Sensor> listSensorsFromTo(
            @PathVariable String sourceid,
            @PathVariable int from,
            @PathVariable int to,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException, ResourceNotFoundException {
        InformationSource source
                = (InformationSource) helper.returnOrException(sourceService.get(sourceid));
        helper.exceptionIfWrongLimits(to, from);
        return sensorservice.getBy(from, to, source);
    }
}
