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
import fi.iot.iiframework.restapi.filters.CriterionFactory;
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

    /**
     * Lists the default amount (1000) of Readouts from the system.
     *
     * @param sensorid ID of the Sensor to read the Readouts from
     * @param params   Parameters to limit the Readouts
     *
     * @return List of Readouts with max length of 1000
     *
     * @see CriterionFactory
     * @throws ResourceNotFoundException thrown if the resource requested could
     *                                   not be found
     */
    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/{sensorid}/list", produces = "application/json")
    @ResponseBody
    public List<Readout> listReadoutsList(
            @PathVariable long sensorid,
            @RequestParam(required = false) Map<String, String> params
    ) throws ResourceNotFoundException {
        Sensor sensor = (Sensor) helper.returnOrException(sensorservice.get(sensorid));
        return readoutservice.getBy(0, 1000, createCriterion(sensor, params));
    }

    /**
     * Returns the amount of Readouts in one sensor with given ID.
     *
     * @param sensorid ID of the sensor
     *
     * @return StatObject containing the number of readouts in a single sensor
     */
    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/{sensorid}/count", produces = "application/json")
    @ResponseBody
    public StatObject getReadoutsCountForSensor(@PathVariable long sensorid) {
        return new StatObject("numberOfReadouts", "", readoutservice.countBy(sensorservice.get(sensorid)));
    }

    /**
     * Returns the amount of Readouts in the whole system.
     *
     * @return StatObject containing the number of readouts in the system
     */
    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/count", produces = "application/json")
    @ResponseBody
    public StatObject getReadoutsCount() {
        return new StatObject("numberOfReadouts", "", readoutservice.count());
    }

    /**
     * List given amount of Readouts from a single Sensor with given ID.
     *
     * @param sensorid ID of the Sensor
     * @param amount   amount of Readouts to display
     * @param params   Parameters to limit the Readouts
     *
     * @return List of Readouts from a single Sensor
     *
     * @see CriterionFactory
     * @throws ResourceNotFoundException  thrown if the resource requested could
     *                                    not be found
     * @throws InvalidParametersException thrown if given parameters were
     *                                    incorrect or in conflict
     */
    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/{sensorid}/list/{amount}", produces = "application/json")
    @ResponseBody
    public List<Readout> listReadoutsAmount(
            @PathVariable long sensorid,
            @PathVariable int amount,
            @RequestParam(required = false) Map<String, String> params
    ) throws ResourceNotFoundException, InvalidParametersException {
        Sensor sensor = (Sensor) helper.returnOrException(sensorservice.get(sensorid));
        helper.exceptionIfWrongLimits(0, amount);
        return readoutservice.getBy(0, amount, createCriterion(sensor, params));
    }

    /**
     * Returns a list of Readouts in a single Sensor with given ID from index
     * [to] to
     * [from].
     *
     * @param sensorid ID of the Sensor
     * @param from     Starting index to list InformationSources from
     * @param to       Ending index to list InformationSources to
     * @param params   Parameters to limit the Readouts
     *
     * @return
     *
     * @see CriterionFactory
     * @throws InvalidParametersException thrown if given parameters were
     *                                    incorrect or in conflict
     * @throws ResourceNotFoundException  thrown if the resource requested could
     *                                    not be found
     */
    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/{sensorid}/list/{from}/{to}", produces = "application/json")
    @ResponseBody
    public List<Readout> listReadoutsFromTo(
            @PathVariable long sensorid,
            @PathVariable int from,
            @PathVariable int to,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException, ResourceNotFoundException {
        Sensor sensor = (Sensor) helper.returnOrException(sensorservice.get(sensorid));
        helper.exceptionIfWrongLimits(from, to);
        return readoutservice.getBy(from, to, createCriterion(sensor, params));
    }

    /**
     * Returns a single Readout with given ID.
     *
     * @param readoutid ID of the Readout to retrieve
     *
     * @return a single Readout
     */
    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/{readoutid}/view", produces = "application/json")
    @ResponseBody
    public Readout getReadout(
            @PathVariable long readoutid
    ) {
        return readoutservice.get(readoutid);
    }

    private List<Criterion> createCriterion(Sensor sensor, Map<String, String> params) {
        List<Criterion> readoutCriterion = criterionfactory.getReadoutCriterion(params);
        readoutCriterion.add(Restrictions.eq("sensor", sensor));
        return readoutCriterion;
    }

    /**
     * Set CriterionFactory used in this Controller (for tests).
     *
     * @param criterionfactory CriterionFactory to be set
     */
    public void setCriterionfactory(CriterionFactory criterionfactory) {
        this.criterionfactory = criterionfactory;
    }

    /**
     * Set RestAPIHelper used in this Controller (for tests).
     *
     * @param helper RestAPIHelper to be set
     */
    public void setHelper(RestAPIHelper helper) {
        this.helper = helper;
    }

    /**
     * Set ReadoutService used in this Controller (for tests).
     *
     * @param readoutservice ReadoutService to be set
     */
    public void setReadoutservice(ReadoutService readoutservice) {
        this.readoutservice = readoutservice;
    }

    /**
     * Set SensorService used in this Controller (for tests).
     *
     * @param sensorservice SensorService to be set
     */
    public void setSensorservice(SensorService sensorservice) {
        this.sensorservice = sensorservice;
    }

}
