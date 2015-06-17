/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi;

import fi.iot.iiframework.restapi.filters.CriterionFactory;
import fi.iot.iiframework.errors.SysError;
import fi.iot.iiframework.restapi.exceptions.InvalidParametersException;
import fi.iot.iiframework.services.errors.ErrorService;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("1.0/errors")
public class SysErrorController {

    @Autowired
    private ErrorService errorservice;

    @Autowired
    private RestAPIHelper helper;

    @Autowired
    private CriterionFactory criterionfactory;

    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/{errorid}/view", produces = "application/json")
    @ResponseBody
    public SysError getError(
            @PathVariable String errorid,
            @RequestParam(required = false) Map<String, String> params
    ) {
        return errorservice.get(errorid);
    }

    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/list", produces = "application/json")
    @ResponseBody
    public List<SysError> listErrors(
            @RequestParam(required = false) Map<String, String> params
    ) {
        return errorservice.getBy(0, 25, createCriterion(params));
    }

    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/list/{amount}", produces = "application/json")
    @ResponseBody
    public List<SysError> listErrorsAmount(
            @PathVariable int amount,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException {
        helper.exceptionIfWrongLimits(0, amount);
        return errorservice.getBy(0, amount, createCriterion(params));
    }

    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/list/{from}/{to}", produces = "application/json")
    @ResponseBody
    public List<SysError> listErrorsFromTo(
            @PathVariable int from,
            @PathVariable int to,
            @RequestParam(required = false) Map<String, String> params
    ) throws InvalidParametersException {
        helper.exceptionIfWrongLimits(from, to);
        return errorservice.getBy(from, to, createCriterion(params));
    }

    private List<Criterion> createCriterion(Map<String, String> params) {
        return criterionfactory.getSysErrorCriterion(params);
    }
}
