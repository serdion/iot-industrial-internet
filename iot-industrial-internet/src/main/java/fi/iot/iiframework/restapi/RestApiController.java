/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi;

import fi.iot.iiframework.application.ApplicationSettings;
import fi.iot.iiframework.dataobject.*;
import fi.iot.iiframework.errors.ErrorType;
import fi.iot.iiframework.errors.SysError;
import fi.iot.iiframework.errors.service.ErrorService;
import fi.iot.iiframework.services.dataobject.DataSourceObjectService;
import fi.iot.iiframework.services.dataobject.DeviceService;
import fi.iot.iiframework.services.dataobject.ReadoutService;
import fi.iot.iiframework.services.dataobject.SensorService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("1.0")
public class RestApiController {

    @Autowired
    private DataSourceObjectService datasourceservice;

    @Autowired
    private DeviceService deviceservice;

    @Autowired
    private ReadoutService readoutservice;

    @Autowired
    private SensorService sensorservice;

    @Autowired
    private ErrorService errorservice;

    @Autowired
    ApplicationSettings settings;

    @RequestMapping(value = "/test", produces = "application/json")
    @ResponseBody
    public List<DataSourceObject> test(
            @RequestParam(required = false) Map<String, String> params
    ) throws ResourceNotFoundException {

        throw new ResourceNotFoundException();

    }

    @RequestMapping(value = "/datasources/list", produces = "application/json")
    @ResponseBody
    public List<DataSourceObject> listDatasources(
            @RequestParam(required = false) Map<String, String> params
    ) {
        return datasourceservice.get(0, settings.getDefaultAmountOfDataSourcesRetrievedFromDatabase());
    }

    @RequestMapping(value = "/datasources/list/{amount}", produces = "application/json")
    @ResponseBody
    public List<DataSourceObject> listDatasourcesAmount(
            @PathVariable int amount,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException {

        exceptionIfWrongLimits(0, amount);

        return datasourceservice.get(0, amount);
    }

    @RequestMapping(value = "/datasources/list/{from}/{to}", produces = "application/json")
    @ResponseBody
    public List<DataSourceObject> listDatasourcesFromTo(
            @PathVariable int from,
            @PathVariable int to,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException {

        exceptionIfWrongLimits(to, from);

        return datasourceservice.get(to, from);
    }

    @RequestMapping(value = "/datasources/{datasourceid}/view", produces = "application/json")
    @ResponseBody
    public DataSourceObject getDatasource(
            @PathVariable String datasourceid,
            @RequestParam(required = false) Map<String, String> params
    ) throws ResourceNotFoundException {

        return (DataSourceObject) returnOrException(datasourceservice.get(datasourceid));
    }

    @RequestMapping(value = "/datasources/{datasourceid}/header", produces = "application/json")
    @ResponseBody
    public Header getDatasourceHeader(
            @PathVariable String datasourceid,
            @RequestParam(required = false) Map<String, String> params) throws ResourceNotFoundException {

        return (Header) returnOrException(datasourceservice.get(datasourceid).getHeader());
    }

    @RequestMapping(value = "/devices/{datasourceid}/list", produces = "application/json")
    @ResponseBody
    public List<Device> listDevices(
            @PathVariable String datasourceid,
            @RequestParam(required = false) Map<String, String> params
    ) throws ResourceNotFoundException {

        Object returnOrException = returnOrException(datasourceservice.get(datasourceid));

        // TODO
        return deviceservice.get(0, settings.getDefaultAmountOfDevicesRetrievedFromDatabase());
    }

    @RequestMapping(value = "/devices/{datasourceid}/list/{amount}", produces = "application/json")
    @ResponseBody
    public List<Device> listDevicesAmount(
            @PathVariable String datasourceid,
            @PathVariable int amount,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException {

        exceptionIfWrongLimits(0, amount);

        // TODO
        return deviceservice.get(0, settings.getDefaultAmountOfDevicesRetrievedFromDatabase());
    }

    @RequestMapping(value = "/devices/{datasourceid}/list/{from}/{to}", produces = "application/json")
    @ResponseBody
    public List<Device> listDevicesFromTo(
            @PathVariable String datasourceid,
            @PathVariable int from,
            @PathVariable int to,
            @RequestParam(required = false) Map<String, String> params) throws InvalidParametersException {

        exceptionIfWrongLimits(to, from);

        // TODO
        return deviceservice.get(to, from);
    }

    @RequestMapping(value = "/devices/{deviceid}/view", produces = "application/json")
    @ResponseBody
    public Device getDevice(
            @PathVariable String deviceid,
            @RequestParam(required = false) Map<String, String> params
    ) throws ResourceNotFoundException {

        return (Device) returnOrException(deviceservice.get(deviceid));
    }

    @RequestMapping(value = "/sensors/{deviceid}/list/", produces = "application/json")
    @ResponseBody
    public List<Sensor> listSensors(
            @PathVariable String deviceid,
            @RequestParam(required = false) Map<String, String> params
    ) {

        return new ArrayList<>(deviceservice.get(deviceid).getSensors());
    }

    @RequestMapping(value = "/sensors/{deviceid}/list/{amont}", produces = "application/json")
    @ResponseBody
    public List<Sensor> listSensorsAmount(
            @PathVariable String deviceid,
            @PathVariable int amount,
            @RequestParam(required = false) Map<String, String> params
    ) {

        return null;
    }

    @RequestMapping(value = "/sensors/{deviceid}/list/{from}/{to}", produces = "application/json")
    @ResponseBody
    public List<Sensor> listSensorsFromTo(
            @PathVariable String deviceid,
            @PathVariable int from,
            @PathVariable int to,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException {

        exceptionIfWrongLimits(to, from);

        return sensorservice.get(to, from);
    }

    @RequestMapping(value = "/readouts/{sensorid}/list", produces = "application/json")
    @ResponseBody
    public List<Readout> listReadoutsList(
            @PathVariable String sensorid,
            @RequestParam(required = false) Map<String, String> params
    ) throws ResourceNotFoundException {
        Sensor sensor = (Sensor) returnOrException(sensorservice.get(sensorid));

        return readoutservice.getBy(0, settings.getDefaultAmountOfReadoutsRetrievedFromDatabase(), sensor);
    }

    @RequestMapping(value = "/readouts/{sensorid}/list/{amount}", produces = "application/json")
    @ResponseBody
    public List<Readout> listReadoutsAmount(
            @PathVariable String sensorid,
            @PathVariable int amount,
            @RequestParam(required = false) Map<String, String> params
    ) throws ResourceNotFoundException, InvalidParametersException {
        Sensor sensor = (Sensor) returnOrException(sensorservice.get(sensorid));

        exceptionIfWrongLimits(0, amount);

        return readoutservice.getBy(0, amount, sensor);
    }

    @RequestMapping(value = "/readouts/{sensorid}/list/{from}/{to}", produces = "application/json")
    @ResponseBody
    public List<Readout> listReadoutsFromTo(
            @PathVariable String sensorid,
            @PathVariable int from,
            @PathVariable int to,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException, ResourceNotFoundException {
        Sensor sensor = (Sensor) returnOrException(sensorservice.get(sensorid));

        exceptionIfWrongLimits(from, to);

        return readoutservice.getBy(from, to, sensor);
    }

    @RequestMapping(value = "/readouts/{readoutid}/view", produces = "application/json")
    @ResponseBody
    public Readout getReadout(
            @PathVariable long readoutid,
            @RequestParam(required = false) Map<String, String> params
    ) {

        return readoutservice.get(readoutid);
    }

    @RequestMapping(value = "/errors/{errorid}/view", produces = "application/json")
    @ResponseBody
    public SysError getError(
            @PathVariable String errorid,
            @RequestParam(required = false) Map<String, String> params
    ) {

        return errorservice.get(errorid);
    }

    @RequestMapping(value = "/errors/list", produces = "application/json")
    @ResponseBody
    public List<SysError> listErrors(
            @PathVariable String sensorid,
            @PathVariable String timestamp,
            @RequestParam(required = false) Map<String, String> params
    ) {

        return errorservice.get(0, settings.getDefautAmountOfErrorsRetrievedFromDatabase());
    }

    @RequestMapping(value = "/errors/list/{amount}", produces = "application/json")
    @ResponseBody
    public List<SysError> listErrorsAmount(
            @PathVariable int amount,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException {

        exceptionIfWrongLimits(0, amount);

        return errorservice.get(0, amount);
    }

    @RequestMapping(value = "/errors/list/{from}/{to}", produces = "application/json")
    @ResponseBody
    public List<SysError> listErrorsFromTo(
            @PathVariable int from,
            @PathVariable int to,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException {

        exceptionIfWrongLimits(from, to);

        return errorservice.get(from, to);
    }

    @RequestMapping(value = "/error/notfound", produces = "application/json")
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ResponseEntity<RestAPIError> resourceNotFoundException() {

        return new ResponseEntity<>(
                new RestAPIError(
                        ErrorType.NOT_FOUND,
                        "The object you tried to retrieve could not be found."
                ), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/error/invalidparameters", produces = "application/json")
    @ExceptionHandler(InvalidParametersException.class)
    @ResponseBody
    public ResponseEntity<RestAPIError> invalidParametersException() {

        return new ResponseEntity<>(
                new RestAPIError(
                        ErrorType.BAD_REQUEST,
                        "Invalid parameters found in your request."
                ), HttpStatus.BAD_REQUEST);
    }

    private void exceptionIfWrongLimits(int to, int from) throws InvalidParametersException {
        if(from<=0||to>settings.getMaxObjectsRetrievedFromDatabase()) {
            throw new InvalidParametersException();
        }
    }

    private Object returnOrException(Object object) throws ResourceNotFoundException {
        if(object==null) {
            throw new ResourceNotFoundException();
        }

        return object;
    }

}