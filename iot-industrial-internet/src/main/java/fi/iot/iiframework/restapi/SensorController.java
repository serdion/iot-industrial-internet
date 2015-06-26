/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi;

import fi.iot.iiframework.domain.InformationSource;
import fi.iot.iiframework.domain.Sensor;
import fi.iot.iiframework.restapi.exceptions.InvalidObjectException;
import fi.iot.iiframework.restapi.exceptions.InvalidParametersException;
import fi.iot.iiframework.restapi.exceptions.ResourceNotFoundException;
import fi.iot.iiframework.services.domain.InformationSourceService;
import fi.iot.iiframework.services.domain.SensorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("1.0/sensors")
public class SensorController {

    @Autowired
    private InformationSourceService sourceService;

    @Autowired
    private SensorService sensorService;

    @Autowired
    private RestAPIHelper helper;

    /**
     * Returns a single sensor with given ID.
     *
     * @param sensorid ID of the sensor
     *
     * @return Sensor with given ID
     *
     * @throws ResourceNotFoundException thrown if the resource requested could
     *                                   not be found
     */
    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/{sensorid}/view", produces = "application/json")
    @ResponseBody
    public Sensor getSensor(
            @PathVariable long sensorid
    ) throws ResourceNotFoundException {
        return (Sensor) helper.returnOrException(sensorService.get(sensorid));
    }

    /**
     * Edit a single Sensor with given ID.
     *
     * @param sensorid ID of the sensor to be edited
     * @param sensor   Sensor to be edited
     *
     * @return edited Sensor
     *
     * @throws InvalidParametersException thrown if given parameters were
     *                                    incorrect or in conflict
     * @throws ResourceNotFoundException  thrown if the resource requested could
     *                                    not be found
     * @throws InvalidObjectException     thrown if the object provided was
     *                                    invalid, which is determined by
     *                                    isValid() function
     * @throws Exception                  catches all database errors, should be
     *                                    removed later in the update cycle
     */
    @Secured("ROLE_MODERATOR")
    @RequestMapping(
            value = "/{sensorid}/edit",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json"
    )
    @ResponseBody
    public ResponseEntity<Sensor> editSensor(
            @PathVariable long sensorid,
            @RequestBody Sensor sensor
    ) throws InvalidParametersException, ResourceNotFoundException, InvalidObjectException, Exception {
        sensorService.update(sensor);
        return new ResponseEntity<>(sensor, HttpStatus.CREATED);
    }

    /**
     * List the default amount of Sensors in a single InformationSource with
     * given ID.
     *
     * @param sourceid ID of the source from which to get the sensors from
     *
     * @return List of sensors in a single InformationSource
     *
     * @throws ResourceNotFoundException thrown if the resource requested could
     *                                   not be found
     * @throws Exception                 catches all database errors, should be
     *                                   removed later in the update cycle
     */
    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/{sourceid}/list", produces = "application/json")
    @ResponseBody
    public List<Sensor> listSensors(
            @PathVariable long sourceid
    ) throws ResourceNotFoundException, Exception {
        InformationSource source = (InformationSource) helper.returnOrException(sourceService.get(sourceid));
        return sensorService.getBy(source);
    }

    /**
     * Returns the amount of Sensors in a single InformationSource with given
     * ID.
     *
     * @param sourceid ID of the InformationSource
     *
     * @return StatObject containing the number of Sensors in a single
     *         InformationSource
     */
    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/{sourceid}/count", produces = "application/json")
    @ResponseBody
    public StatObject getSensorCountForSource(
            @PathVariable long sourceid
    ) {
        return new StatObject("numberOfSensors", "", sensorService.countBy(sourceService.get(sourceid)));
    }

    /**
     * Returns the amount of Sensors in the whole system.
     *
     * @return StatObject containing the number of Sensors in the whole system
     */
    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/count", produces = "application/json")
    @ResponseBody
    public StatObject getSensorCount() {
        return new StatObject("numberOfSensors", "", sensorService.count());
    }

    /**
     * List given amount of sensors found in a single InformationSource
     *
     * @param sourceid ID of the InformationSource
     * @param amount   amount of sensors to retrieve
     *
     * @return List of Sensors
     *
     * @throws ResourceNotFoundException  thrown if the resource requested could
     *                                    not be found
     * @throws InvalidParametersException thrown if given parameters were
     *                                    incorrect or in conflict
     */
    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/{sourceid}/list/{amount}", produces = "application/json")
    @ResponseBody
    public List<Sensor> listSensorsAmount(
            @PathVariable long sourceid,
            @PathVariable int amount
    ) throws ResourceNotFoundException, InvalidParametersException {
        InformationSource source
                = (InformationSource) helper.returnOrException(sourceService.get(sourceid));
        helper.exceptionIfWrongLimits(0, amount);
        return sensorService.getBy(0, amount, source);
    }

    /**
     * Returns a list of Sensors in a single InformationSource with given ID
     * from index [to] to [from].
     *
     * @param sourceid IF of the InformationSource
     * @param from     starting index to list Sensors from
     * @param to       Ending index to list Sensors to
     *
     * @return List of Sensors
     *
     * @throws InvalidParametersException thrown if given parameters were
     *                                    incorrect or in conflict
     * @throws ResourceNotFoundException  thrown if the resource requested could
     *                                    not be found
     */
    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/{sourceid}/list/{from}/{to}", produces = "application/json")
    @ResponseBody
    public List<Sensor> listSensorsFromTo(
            @PathVariable long sourceid,
            @PathVariable int from,
            @PathVariable int to
    ) throws InvalidParametersException, ResourceNotFoundException {
        InformationSource source
                = (InformationSource) helper.returnOrException(sourceService.get(sourceid));
        helper.exceptionIfWrongLimits(to, from);
        return sensorService.getBy(from, to, source);
    }
}
