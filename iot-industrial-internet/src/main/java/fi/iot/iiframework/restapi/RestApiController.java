/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi;

import fi.iot.iiframework.restapi.exceptions.ResourceNotFoundException;
import fi.iot.iiframework.restapi.exceptions.InvalidParametersException;
import fi.iot.iiframework.restapi.exceptions.InvalidObjectException;
import fi.iot.iiframework.application.ApplicationSettings;
import fi.iot.iiframework.errors.SysError;
import fi.iot.iiframework.errors.service.ErrorService;
import fi.iot.iiframework.services.domain.InformationSourceObjectService;
import fi.iot.iiframework.services.domain.DeviceService;
import fi.iot.iiframework.services.domain.ReadoutService;
import fi.iot.iiframework.services.domain.SensorService;
import fi.iot.iiframework.source.InformationSourceConfiguration;
import fi.iot.iiframework.source.service.InformationSourceConfigurationService;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@RestController
@RequestMapping("1.0")
public class RestApiController {

    @Autowired
    private InformationSourceObjectService datasourceservice;

    @Autowired
    private DeviceService deviceservice;

    @Autowired
    private ReadoutService readoutservice;

    @Autowired
    private SensorService sensorservice;

    @Autowired
    private ErrorService errorservice;

    @Autowired
    private InformationSourceConfigurationService informationsourceservice;

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Autowired
    private CriterionFactory criterionfactory;
    
    @Autowired
    private RestAPIHelper helper;
    
    @Autowired
    private ApplicationSettings settings;

    @RequestMapping(value = "/", produces = "application/json")
    @ResponseBody
    public Object[] index() {
        Set<String> links = new HashSet<>();

        requestMappingHandlerMapping.getHandlerMethods().entrySet().stream().forEach((entrySet) -> {
            links.addAll(entrySet.getKey().getPatternsCondition().getPatterns());
        });

        return links.stream().filter((p) -> p.contains("1.0")).toArray();
    }

    // TODO
    
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
        helper.exceptionIfWrongLimits(0, amount);
        return errorservice.get(0, amount);
    }

    @RequestMapping(value = "/errors/list/{from}/{to}", produces = "application/json")
    @ResponseBody
    public List<SysError> listErrorsFromTo(
            @PathVariable int from,
            @PathVariable int to,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException {
        helper.exceptionIfWrongLimits(from, to);
        return errorservice.get(from, to);
    }

    @RequestMapping(value = "/configurations/informationsources/{configid}/view", produces = "application/json")
    @ResponseBody
    public InformationSourceConfiguration getInformationSource(
            @PathVariable String configid,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException, ResourceNotFoundException {
        return (InformationSourceConfiguration) helper.returnOrException(informationsourceservice.get(configid));
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
        helper.checkIfObjectIsValid(configuration);
        informationsourceservice.save(configuration);
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
                = (InformationSourceConfiguration) helper.returnOrException(informationsourceservice.get(configid));
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
        helper.exceptionIfWrongLimits(0, amount);
        return informationsourceservice.get(0, amount);
    }

    @RequestMapping(value = "/configurations/informationsources/list/{to}/{from}", produces = "application/json")
    @ResponseBody
    public List<InformationSourceConfiguration> listInformationSourcesListFromTo(
            @PathVariable int from,
            @PathVariable int to,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException {
        helper.exceptionIfWrongLimits(to, from);
        return informationsourceservice.get(to, from);
    }

    

}
