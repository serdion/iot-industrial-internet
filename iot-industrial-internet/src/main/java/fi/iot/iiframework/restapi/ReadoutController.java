/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi;

import fi.iot.iiframework.domain.Readout;
import fi.iot.iiframework.domain.Sensor;
import fi.iot.iiframework.restapi.exceptions.InvalidParametersException;
import fi.iot.iiframework.restapi.exceptions.ResourceNotFoundException;
import fi.iot.iiframework.services.domain.ReadoutService;
import fi.iot.iiframework.services.domain.SensorService;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("1.0/readouts")
public class ReadoutController {

    @Autowired
    private SensorService sensorservice;

    @Autowired
    private ReadoutService readoutservice;

    @Autowired
    private CriterionFactory criterionfactory;

    @Autowired
    private RestAPIHelper helper;

    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/{sensorid}/list", produces = "application/json")
    @ResponseBody
    public List<Readout> listReadoutsList(
            @PathVariable String sensorid,
            @RequestParam(required = false) Map<String, String> params
    ) throws ResourceNotFoundException {
        Sensor sensor = (Sensor) helper.returnOrException(sensorservice.get(sensorid));
        return readoutservice.getBy(0, 10000, createCriterion(sensor, params));
    }

    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/{sensorid}/list/{amount}", produces = "application/json")
    @ResponseBody
    public List<Readout> listReadoutsAmount(
            @PathVariable String sensorid,
            @PathVariable int amount,
            @RequestParam(required = false) Map<String, String> params
    ) throws ResourceNotFoundException, InvalidParametersException {
        Sensor sensor = (Sensor) helper.returnOrException(sensorservice.get(sensorid));
        helper.exceptionIfWrongLimits(0, amount);
        return readoutservice.getBy(0, amount, createCriterion(sensor, params));
    }

    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/{sensorid}/list/{from}/{to}", produces = "application/json")
    @ResponseBody
    public List<Readout> listReadoutsFromTo(
            @PathVariable String sensorid,
            @PathVariable int from,
            @PathVariable int to,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException, ResourceNotFoundException {
        Sensor sensor = (Sensor) helper.returnOrException(sensorservice.get(sensorid));
        helper.exceptionIfWrongLimits(from, to);
        return readoutservice.getBy(from, to, createCriterion(sensor, params));
    }

    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/{readoutid}/view", produces = "application/json")
    @ResponseBody
    public Readout getReadout(
            @PathVariable long readoutid,
            @RequestParam(required = false) Map<String, String> params
    ) {
        return readoutservice.get(readoutid);
    }

    private List<Criterion> createCriterion(Sensor sensor, Map<String, String> params) {
        List<Criterion> readoutCriterion = criterionfactory.getReadoutCriterion(params);
        readoutCriterion.add(Restrictions.eq("sensor", sensor));
        return readoutCriterion;
    }
}
