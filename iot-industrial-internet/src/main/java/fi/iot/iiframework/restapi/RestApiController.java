/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi;

import fi.iot.iiframework.dataobject.Device;
import fi.iot.iiframework.dataobject.Header;
import fi.iot.iiframework.dataobject.Readout;
import fi.iot.iiframework.dataobject.Sensor;
import fi.iot.iiframework.services.dataobject.DataSourceObjectService;
import java.util.List;
import java.util.Map;
import fi.iot.iiframework.dataobject.DataSourceObject;
import fi.iot.iiframework.errors.ErrorType;
import fi.iot.iiframework.errors.SysError;
import fi.iot.iiframework.errors.service.ErrorService;
import fi.iot.iiframework.services.dataobject.DeviceService;
import fi.iot.iiframework.services.dataobject.ReadoutService;
import fi.iot.iiframework.services.dataobject.SensorService;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return datasourceservice.getAll();
    }

    @RequestMapping(value = "/datasources/list/{amount}", produces = "application/json")
    @ResponseBody
    public List<DataSourceObject> listDatasourcesAmount(
            @PathVariable int amount,
            @RequestParam(required = false) Map<String, String> params
    ) {

        return null;
    }

    @RequestMapping(value = "/datasources/list/{from}/{to}", produces = "application/json")
    @ResponseBody
    public List<DataSourceObject> listDatasourcesFromTo(
            @PathVariable int from,
            @PathVariable int to,
            @RequestParam(required = false) Map<String, String> params
    ) {

        return null;
    }

    @RequestMapping(value = "/datasources/{datasourceid}/view", produces = "application/json")
    @ResponseBody
    public DataSourceObject getDatasource(
            @PathVariable String datasourceid,
            @RequestParam(required = false) Map<String, String> params
    ) {

        return datasourceservice.get(datasourceid);
    }

    @RequestMapping(value = "/datasources/{datasourceid}/header", produces = "application/json")
    @ResponseBody
    public Header getDatasourceHeader(
            @PathVariable String datasourceid,
            @RequestParam(required = false) Map<String, String> params) {

        return datasourceservice.get(datasourceid).getHeader();
    }

    @RequestMapping(value = "/devices/{datasourceid}/list", produces = "application/json")
    @ResponseBody
    public List<Device> listDevices(
            @PathVariable String datasourceid,
            @RequestParam(required = false) Map<String, String> params
    ) {

        return null;
    }

    @RequestMapping(value = "/devices/{datasourceid}/list/{amont}", produces = "application/json")
    @ResponseBody
    public List<Device> listDevicesAmount(
            @PathVariable String datasourceid,
            @PathVariable int amount,
            @RequestParam(required = false) Map<String, String> params
    ) {

        return null;
    }

    @RequestMapping(value = "/devices/{datasourceid}/list/{from}/{to}", produces = "application/json")
    @ResponseBody
    public List<Device> listDevicesFromTo(
            @PathVariable String datasourceid,
            @PathVariable int from,
            @PathVariable int to,
            @RequestParam(required = false) Map<String, String> params) {

        return null;
    }

    @RequestMapping(value = "/devices/{deviceid}/view", produces = "application/json")
    @ResponseBody
    public Device getDevice(
            @PathVariable String deviceid,
            @RequestParam(required = false) Map<String, String> params
    ) {

        return deviceservice.get(deviceid);
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
    ) {

        return null;
    }

    @RequestMapping(value = "/readouts/{sensorid}/list", produces = "application/json")
    @ResponseBody
    public List<Readout> listReadoutsList(
            @PathVariable String sensorid,
            @RequestParam(required = false) Map<String, String> params
    ) {

        return new ArrayList<>(sensorservice.get(sensorid).getReadouts());
    }

    @RequestMapping(value = "/readouts/{sensorid}/list/{amont}", produces = "application/json")
    @ResponseBody
    public List<Readout> listReadoutsAmount(
            @PathVariable String sensorid,
            @PathVariable int amount,
            @RequestParam(required = false) Map<String, String> params
    ) {

        return null;
    }

    @RequestMapping(value = "/readouts/{sensorid}/list/{from}/{to}", produces = "application/json")
    @ResponseBody
    public List<Sensor> listReadoutsFromTo(
            @PathVariable String sensorid,
            @PathVariable int from,
            @PathVariable int to,
            @RequestParam(required = false) Map<String, String> params
    ) {

        return null;
    }

    @RequestMapping(value = "/readouts/{readoutid}/view/{timestamp}", produces = "application/json")
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

        return errorservice.getAll();
    }
    
    @RequestMapping(value = "/errors/list/{amount}", produces = "application/json")
    @ResponseBody
    public List<SysError> listErrorsAmount(
            @PathVariable int amount,
            @RequestParam(required = false) Map<String, String> params
    ) {

        return null;
    }
    
    @RequestMapping(value = "/errors/list/{from}/{to}", produces = "application/json")
    @ResponseBody
    public List<SysError> listErrorsFromTo(
            @PathVariable int from,
            @PathVariable int to,
            @RequestParam(required = false) Map<String, String> params
    ) {

        return null;
    }

    @RequestMapping(produces = "application/json")
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ResponseEntity<RestAPIError> resourceNotFoundException() {

        return new ResponseEntity<>(
                new RestAPIError(
                        ErrorType.NOT_FOUND,
                        "The object you tried to retrieve could not be found."
                ), HttpStatus.NOT_FOUND);
    }

}
