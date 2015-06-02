/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi;

import fi.iot.iiframework.application.ApplicationSettings;
import fi.iot.iiframework.dataobject.*;
import fi.iot.iiframework.errors.ErrorLogger;
import fi.iot.iiframework.errors.ErrorSeverity;
import fi.iot.iiframework.errors.ErrorType;
import fi.iot.iiframework.errors.SysError;
import fi.iot.iiframework.errors.service.ErrorService;
import fi.iot.iiframework.services.dataobject.DataSourceObjectService;
import fi.iot.iiframework.services.dataobject.DeviceService;
import fi.iot.iiframework.services.dataobject.ReadoutService;
import fi.iot.iiframework.services.dataobject.SensorService;
import fi.iot.iiframework.source.InformationSourceConfiguration;
import fi.iot.iiframework.source.service.InformationSourceConfigurationService;
import fi.iot.iiframework.source.InformationSourceManager;
import java.util.*;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

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
    private ApplicationSettings settings;

    @Autowired
    private InformationSourceConfigurationService informationsourceservice;

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Autowired
    private CriterionFactory criterionfactory;

    @Autowired
    private InformationSourceManager informationsourcemanager;

    @RequestMapping(value = "/", produces = "application/json")
    @ResponseBody
    public Object[] index() {
        Set<String> links = new HashSet<>();

        requestMappingHandlerMapping.getHandlerMethods().entrySet().stream().forEach((entrySet) -> {
            links.addAll(entrySet.getKey().getPatternsCondition().getPatterns());
        });

        return links.stream().filter((p) -> p.contains("1.0")).toArray();
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
        DataSourceObject source = (DataSourceObject) returnOrException(datasourceservice.get(datasourceid));
        return deviceservice.getBy(0, settings.getDefaultAmountOfDevicesRetrievedFromDatabase(), source);
    }

    @RequestMapping(value = "/devices/{datasourceid}/list/{amount}", produces = "application/json")
    @ResponseBody
    public List<Device> listDevicesAmount(
            @PathVariable String datasourceid,
            @PathVariable int amount,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException, ResourceNotFoundException {
        DataSourceObject source = (DataSourceObject) returnOrException(datasourceservice.get(datasourceid));
        exceptionIfWrongLimits(0, amount);
        return deviceservice.getBy(0, amount, source);
    }

    @RequestMapping(value = "/devices/{datasourceid}/list/{from}/{to}", produces = "application/json")
    @ResponseBody
    public List<Device> listDevicesFromTo(
            @PathVariable String datasourceid,
            @PathVariable int from,
            @PathVariable int to,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException, ResourceNotFoundException {
        DataSourceObject source = (DataSourceObject) returnOrException(datasourceservice.get(datasourceid));
        exceptionIfWrongLimits(from, to);
        return deviceservice.getBy(to, from, source);
    }

    @RequestMapping(value = "/devices/{deviceid}/view", produces = "application/json")
    @ResponseBody
    public Device getDevice(
            @PathVariable String deviceid,
            @RequestParam(required = false) Map<String, String> params
    ) throws ResourceNotFoundException {
        return (Device) returnOrException(deviceservice.get(Long.parseLong(deviceid)));
    }

    @RequestMapping(value = "/sensors/{sensorid}/view", produces = "application/json")
    @ResponseBody
    public Sensor getSensor(
            @PathVariable String sensorid,
            @RequestParam(required = false) Map<String, String> params
    ) throws ResourceNotFoundException {
        return (Sensor) returnOrException(sensorservice.get(Long.parseLong(sensorid)));
    }

    @RequestMapping(value = "/sensors/{deviceid}/list", produces = "application/json")
    @ResponseBody
    public List<Sensor> listSensors(
            @PathVariable String deviceid,
            @RequestParam(required = false) Map<String, String> params
    ) throws ResourceNotFoundException {
        Device device = (Device) returnOrException(deviceservice.get(Long.parseLong(deviceid)));
        return sensorservice.getBy(0, settings.getDefaultAmountOfSensorsRetrievedFromDatabase(), device);
    }

    @RequestMapping(value = "/sensors/{deviceid}/list/{amount}", produces = "application/json")
    @ResponseBody
    public List<Sensor> listSensorsAmount(
            @PathVariable String deviceid,
            @PathVariable int amount,
            @RequestParam(required = false) Map<String, String> params
    ) throws ResourceNotFoundException, InvalidParametersException {
        Device device = (Device) returnOrException(deviceservice.get(Long.parseLong(deviceid)));
        exceptionIfWrongLimits(0, amount);
        return sensorservice.getBy(0, amount, device);
    }

    @RequestMapping(value = "/sensors/{deviceid}/list/{from}/{to}", produces = "application/json")
    @ResponseBody
    public List<Sensor> listSensorsFromTo(
            @PathVariable String deviceid,
            @PathVariable int from,
            @PathVariable int to,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException, ResourceNotFoundException {
        Device device = (Device) returnOrException(deviceservice.get(Long.parseLong(deviceid)));
        exceptionIfWrongLimits(to, from);
        return sensorservice.getBy(from, to, device);
    }

    @RequestMapping(value = "/readouts/{sensorid}/list", produces = "application/json")
    @ResponseBody
    public List<Readout> listReadoutsList(
            @PathVariable String sensorid,
            @RequestParam(required = false) Map<String, String> params
    ) throws ResourceNotFoundException {
        Sensor sensor = (Sensor) returnOrException(sensorservice.get(Long.parseLong(sensorid)));
        return readoutservice.getBy(0, settings.getDefaultAmountOfReadoutsRetrievedFromDatabase(), sensor);
    }

    @RequestMapping(value = "/readouts/{sensorid}/list/{amount}", produces = "application/json")
    @ResponseBody
    public List<Readout> listReadoutsAmount(
            @PathVariable String sensorid,
            @PathVariable int amount,
            @RequestParam(required = false) Map<String, String> params
    ) throws ResourceNotFoundException, InvalidParametersException {
        Sensor sensor = (Sensor) returnOrException(sensorservice.get(Long.parseLong(sensorid)));
        exceptionIfWrongLimits(0, amount);

        List<Criterion> readoutCriterion = criterionfactory.getReadoutCriterion(params);

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
        Sensor sensor = (Sensor) returnOrException(sensorservice.get(Long.parseLong(sensorid)));
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
//            @PathVariable String sensorid,
//            @PathVariable String timestamp,
            @RequestParam(required = false) Map<String, String> params
    ) {
        System.out.println("Getting errors!");
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

    @RequestMapping(value = "/configurations/informationsources/{configid}/view", produces = "application/json")
    @ResponseBody
    public InformationSourceConfiguration getInformationSource(
            @PathVariable String configid,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException, ResourceNotFoundException {
        return (InformationSourceConfiguration) returnOrException(informationsourceservice.get(configid));
    }

    @RequestMapping(
            value = "/configurations/informationsources/add",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json"
    )
    @ResponseBody
    public ResponseEntity<InformationSourceConfiguration> addInformationSource(
            @RequestBody InformationSourceConfiguration configuration,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException, ResourceNotFoundException, InvalidObjectException {
        checkIfObjectIsValid(configuration);
        informationsourceservice.save(configuration);
        informationsourcemanager.createSource(configuration);
        return new ResponseEntity<>(configuration, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/configurations/informationsources/{configid}/delete",
            method = RequestMethod.DELETE,
            produces = "application/json"
    )
    @ResponseBody
    public ResponseEntity<InformationSourceConfiguration> deleteInformationSource(
            @PathVariable String configid,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException, ResourceNotFoundException {
        InformationSourceConfiguration configuration
                = (InformationSourceConfiguration) returnOrException(informationsourceservice.get(configid));
        informationsourceservice.delete(configuration);
        return new ResponseEntity<>(configuration, HttpStatus.OK);
    }

    @RequestMapping(value = "/configurations/informationsources/list", produces = "application/json")
    @ResponseBody
    public List<InformationSourceConfiguration> listInformationSourcesList(
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException {
        return informationsourceservice.get(0, settings.getDefaultInformationSourcesRetrievedFromDatabase());
    }

    @RequestMapping(value = "/configurations/informationsources/list/{amount}", produces = "application/json")
    @ResponseBody
    public List<InformationSourceConfiguration> listInformationSourcesListAmount(
            @PathVariable int amount,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException {
        exceptionIfWrongLimits(0, amount);
        return informationsourceservice.get(0, amount);
    }

    @RequestMapping(value = "/configurations/informationsources/list/{to}/{from}", produces = "application/json")
    @ResponseBody
    public List<InformationSourceConfiguration> listInformationSourcesListFromTo(
            @PathVariable int from,
            @PathVariable int to,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException {
        exceptionIfWrongLimits(to, from);
        return informationsourceservice.get(to, from);
    }

    /**
     * Catches ResourceNotFoundExceptions created by RestAPI and notifies the
     * user with RestAPIError object that contains an ErrorType and a message.
     *
     * @return ResponseEntity with RestAPIError object
     */
    @RequestMapping(value = "/error/resourcenotfound", produces = "application/json")
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ResponseEntity<RestAPIError> resourceNotFoundException() {
        return new ResponseEntity<>(
                new RestAPIError(
                        ErrorType.NOT_FOUND,
                        "The object you tried to retrieve could not be found."
                ), HttpStatus.NOT_FOUND);
    }

    /**
     * Catches InvalidParametersException created by RestAPI and notifies the
     * user with RestAPIError object that contains an ErrorType and a message.
     *
     * @return ResponseEntity with RestAPIError object
     */
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

    /**
     * Catches InvalidParametersException created by RestAPI and notifies the
     * user with RestAPIError object that contains an ErrorType and a message.
     *
     * @return ResponseEntity with RestAPIError object
     */
    @RequestMapping(value = "/error/invalidobject", produces = "application/json")
    @ExceptionHandler(InvalidObjectException.class)
    @ResponseBody
    public ResponseEntity<RestAPIError> invalidObjectException() {
        return new ResponseEntity<>(
                new RestAPIError(
                        ErrorType.INVALID_OBJECT,
                        "Object was invalid or wrong type."
                ), HttpStatus.NOT_ACCEPTABLE);
    }

    /*
     * {from} cannot be negative
     * {to} cannot be negative
     * {to} cannot be equal to {from}
     * {to} cannot be smaller than {from}
     * {from} minus {to} cannot be bigger than default max objects retrieved
     */
    public void exceptionIfWrongLimits(int from, int to) throws InvalidParametersException {
        if (from < 0 || to <= 0 || to == from || from > to
                || (from - to) > settings.getMaxObjectsRetrievedFromDatabase()) {
            ErrorLogger.newError(ErrorType.BAD_REQUEST, ErrorSeverity.LOW, "Invalid parameters given for limits (" + from + ", " + to + ") in RestAPI.");
            throw new InvalidParametersException();
        }
    }

    /**
     * Returns the object it was given, if the object is null
     * ResourceNotFoundException will be thrown.
     *
     * @param object Object to check for null
     *
     * @return Object it was given
     *
     * @throws ResourceNotFoundException if the object is null
     */
    public Object returnOrException(Object object) throws ResourceNotFoundException {
        if (object == null) {
            ErrorLogger.newError(ErrorType.NOT_FOUND, ErrorSeverity.LOW, "Resource request could not be found in RestAPI.");
            throw new ResourceNotFoundException();
        }

        return object;
    }

    /**
     * Checks if Validatable object is valid and throws exception if not.
     *
     * @param validatable Validatable object
     * @throws InvalidObjectException if not valid
     */
    public void checkIfObjectIsValid(Validatable validatable) throws InvalidObjectException {
        if (!validatable.isValid()) {
            ErrorLogger.newError(ErrorType.IO_ERROR, ErrorSeverity.LOW, "Object recieved was invalid or wrong type in RestAPI.");
            throw new InvalidObjectException();
        }
    }

}
