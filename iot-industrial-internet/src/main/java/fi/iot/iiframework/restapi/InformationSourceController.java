/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi;

import fi.iot.iiframework.application.ApplicationSettings;
import fi.iot.iiframework.domain.InformationSourceObject;
import fi.iot.iiframework.domain.Header;
import fi.iot.iiframework.restapi.exceptions.InvalidParametersException;
import fi.iot.iiframework.restapi.exceptions.ResourceNotFoundException;
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
@RequestMapping("1.0/informationsources")
public class InformationSourceController {

    @Autowired
    private InformationSourceObjectService informtionSourceService;

    @Autowired
    private ApplicationSettings settings;

    @Autowired
    private RestAPIHelper helper;

    @RequestMapping(value = "/list", produces = "application/json")
    @ResponseBody
    public List<InformationSourceObject> listDatasources(
            @RequestParam(required = false) Map<String, String> params
    ) {
        return informtionSourceService.get(0, settings.getDefaultAmountOfDataSourcesRetrievedFromDatabase());
    }

    @RequestMapping(value = "/list/{amount}", produces = "application/json")
    @ResponseBody
    public List<InformationSourceObject> listDatasourcesAmount(
            @PathVariable int amount,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException {
        helper.exceptionIfWrongLimits(0, amount);
        return informtionSourceService.get(0, amount);
    }

    @RequestMapping(value = "/list/{from}/{to}", produces = "application/json")
    @ResponseBody
    public List<InformationSourceObject> listDatasourcesFromTo(
            @PathVariable int from,
            @PathVariable int to,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException {
        helper.exceptionIfWrongLimits(to, from);
        return informtionSourceService.get(to, from);
    }

    @RequestMapping(value = "/{informationsourceid}/view", produces = "application/json")
    @ResponseBody
    public InformationSourceObject getDatasource(
            @PathVariable String informationsourceid,
            @RequestParam(required = false) Map<String, String> params
    ) throws ResourceNotFoundException {
        return (InformationSourceObject) helper.returnOrException(informtionSourceService.get(informationsourceid));
    }

    @RequestMapping(value = "/{informationsourceid}/header", produces = "application/json")
    @ResponseBody
    public Header getDatasourceHeader(
            @PathVariable String informationsourceid,
            @RequestParam(required = false) Map<String, String> params) throws ResourceNotFoundException {
        return (Header) helper.returnOrException(informtionSourceService.get(informationsourceid).getHeader());
    }

}
