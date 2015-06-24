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
import fi.iot.iiframework.restapi.exceptions.ResourceNotFoundException;
import fi.iot.iiframework.restapi.exceptions.ShouldBeBooleanException;
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

    /**
     * Set a single Error to a certain status. Accepts only "true" and "false"
     * as a status String.
     *
     * @param errorid Id of the SysError
     * @param status Status to be set as a string ("true" or "false")
     * @param params Given parameters in URL
     * @return SysError that was edited
     * @throws ResourceNotFoundException
     * @throws ShouldBeBooleanException thrown if boundaries are incorrect
     */
    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(
            value = "/{errorid}/set/{status}",
            produces = "application/json",
            method = RequestMethod.POST
    )
    @ResponseBody
    public SysError setErrorViewed(
            @PathVariable long errorid,
            @PathVariable String status,
            @RequestParam(required = false) Map<String, String> params
    ) throws ResourceNotFoundException, ShouldBeBooleanException {
        SysError error = (SysError) helper.returnOrException(errorservice.get(errorid));
        error.setViewed(helper.checkIfStringIsBoolean(status));
        errorservice.save(error);

        return error;
    }

    /**
     * Returns a single SysError that matches the given id.
     *
     * @param errorid ID of the SysError
     * @param params Given parameters in URL
     * @return SysError that matches the given ID.
     */
    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/{errorid}/view", produces = "application/json")
    @ResponseBody
    public SysError getError(
            @PathVariable long errorid,
            @RequestParam(required = false) Map<String, String> params
    ) {
        return errorservice.get(errorid);
    }

    /**
     * List default amount of SysErrors (25) that are ordered from newest to
     * oldest.
     *
     * @param params Parameters given in the URL.
     * @return a list of SysErrors
     */
    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/list", produces = "application/json")
    @ResponseBody
    public List<SysError> listErrors(
            @RequestParam(required = false) Map<String, String> params
    ) {
        return errorservice.getBy(0, 25, createCriterion(params));
    }

    /**
     * List given amount of SysErrors that are ordered from newest to oldest.
     *
     * @param amount Number of SysErrors to retrieve.
     * @param params Parameters given in the URL.
     * @return a list of SysErrors
     * @throws InvalidParametersException thrown if given parameters are incorrect
     */
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

    /**
     * List all SysErrors from given index to another given index that are
     * ordered from newest to oldest.
     *
     * @param from Lower boundary
     * @param to Higher boundary
     * @param params Parameters given in the URL
     * @return a list of SysErrors
     * @throws InvalidParametersException thrown if given parameters are incorrect
     */
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
