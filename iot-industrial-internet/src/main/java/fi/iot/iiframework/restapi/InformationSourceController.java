/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi;

import fi.iot.iiframework.application.ApplicationSettings;
import fi.iot.iiframework.dataobject.DataSourceObject;
import fi.iot.iiframework.dataobject.Header;
import fi.iot.iiframework.restapi.exceptions.InvalidParametersException;
import fi.iot.iiframework.restapi.exceptions.ResourceNotFoundException;
import fi.iot.iiframework.services.dataobject.DataSourceObjectService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("1.0/informationsources")
public class InformationSourceController {

    @Autowired
    private DataSourceObjectService datasourceservice;

    @Autowired
    private ApplicationSettings settings;

    @Autowired
    private RestAPIHelper helper;

    @RequestMapping(value = "/list", produces = "application/json")
    @ResponseBody
    public List<DataSourceObject> listDatasources(
            @RequestParam(required = false) Map<String, String> params
    ) {
        return datasourceservice.get(0, settings.getDefaultAmountOfDataSourcesRetrievedFromDatabase());
    }

    @RequestMapping(value = "/list/{amount}", produces = "application/json")
    @ResponseBody
    public List<DataSourceObject> listDatasourcesAmount(
            @PathVariable int amount,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException {
        helper.exceptionIfWrongLimits(0, amount);
        return datasourceservice.get(0, amount);
    }

    @RequestMapping(value = "/list/{from}/{to}", produces = "application/json")
    @ResponseBody
    public List<DataSourceObject> listDatasourcesFromTo(
            @PathVariable int from,
            @PathVariable int to,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException {
        helper.exceptionIfWrongLimits(to, from);
        return datasourceservice.get(to, from);
    }

    @RequestMapping(value = "/{datasourceid}/view", produces = "application/json")
    @ResponseBody
    public DataSourceObject getDatasource(
            @PathVariable String datasourceid,
            @RequestParam(required = false) Map<String, String> params
    ) throws ResourceNotFoundException {
        return (DataSourceObject) helper.returnOrException(datasourceservice.get(datasourceid));
    }

    @RequestMapping(value = "/{datasourceid}/header", produces = "application/json")
    @ResponseBody
    public Header getDatasourceHeader(
            @PathVariable String datasourceid,
            @RequestParam(required = false) Map<String, String> params) throws ResourceNotFoundException {
        return (Header) helper.returnOrException(datasourceservice.get(datasourceid).getHeader());
    }

}
