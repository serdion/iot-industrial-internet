/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi;

import fi.iot.iiframework.domain.InformationSource;
import fi.iot.iiframework.errors.ErrorLogger;
import fi.iot.iiframework.errors.ErrorSeverity;
import fi.iot.iiframework.errors.ErrorType;
import fi.iot.iiframework.restapi.exceptions.InvalidObjectException;
import fi.iot.iiframework.restapi.exceptions.InvalidParametersException;
import fi.iot.iiframework.restapi.exceptions.ResourceNotFoundException;
import fi.iot.iiframework.restapi.exceptions.TooManyRequestsException;
import fi.iot.iiframework.services.domain.InformationSourceService;
import fi.iot.iiframework.source.InformationSourceManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("1.0/sources")
public class InformationSourceController {

    /**
     * Keeps track of when a source was last read.
     */
    private Map<Long, Long> lastRequests = new HashMap<>();

    @Autowired
    private RestAPIHelper helper;

    @Autowired
    private InformationSourceManager informationSourceManager;

    @Autowired
    private InformationSourceService informationSourceService;

    /**
     * Returns a single InformationSource based on the given ID.
     *
     * @param sourceid ID of the InformationSource
     *
     * @return InformationSource that was found
     *
     * @throws InvalidParametersException thrown if parameters given in the
     *                                    request were incorrect of are in
     *                                    conflict
     * @throws ResourceNotFoundException  thrown if the resource requested could
     *                                    not be found
     */
    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/{sourceid}/view", produces = "application/json")
    @ResponseBody
    public InformationSource getInformationSource(
            @PathVariable long sourceid
    ) throws InvalidParametersException, ResourceNotFoundException {
        return (InformationSource) helper.returnOrException(informationSourceService.get(sourceid));
    }

    /**
     * Add a single InformationSource to the system.
     *
     * @param source InformationSource to be added
     *
     * @return InformationSource that was created
     *
     * @throws InvalidParametersException thrown if parameters given in the
     *                                    request were incorrect of are in
     *                                    conflict
     * @throws ResourceNotFoundException  thrown if the resource requested could
     *                                    not be found
     * @throws InvalidObjectException     thrown if the object provided was
     *                                    invalid, which is determined by
     *                                    isValid() function
     */
    @Secured("ROLE_MODERATOR")
    @RequestMapping(
            value = "/add",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json"
    )
    @ResponseBody
    public ResponseEntity<InformationSource> addInformationSource(
            @RequestBody InformationSource source
    ) throws InvalidParametersException, ResourceNotFoundException, InvalidObjectException {
        helper.checkIfObjectIsValid(source);
        informationSourceManager.createSource(source);
        return new ResponseEntity<>(source, HttpStatus.CREATED);
    }

    /**
     * Edit a single InformationSource.
     *
     * @param source InformationSource to be edited
     *
     * @return InformationSource that was edited
     * @throws ResourceNotFoundException  thrown if the resource requested could
     *                                    not be found
     * @throws InvalidObjectException     thrown if the object provided was
     *                                    invalid, which is determined by
     *                                    isValid() function
     */
    @Secured("ROLE_MODERATOR")
    @RequestMapping(
            value = "/edit",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json"
    )
    @ResponseBody
    public ResponseEntity<InformationSource> editInformationSource(
            @RequestBody InformationSource source
    ) throws ResourceNotFoundException, InvalidObjectException {
        helper.checkIfObjectIsValid(source);
        informationSourceManager.updateSource(source);
        return new ResponseEntity<>(source, HttpStatus.CREATED);
    }

    /**
     * Delete a single InformationSource with given ID.
     *
     * @param sourceid ID of the InformationSource
     *
     * @return InformationSource that was deleted
     *
     * @throws InvalidParametersException thrown if parameters given in the
     *                                    request were incorrect of are in
     *                                    conflict
     * @throws ResourceNotFoundException  thrown if the resource requested could
     *                                    not be found
     */
    @Secured("ROLE_MODERATOR")
    @RequestMapping(
            value = "/{sourceid}/delete",
            method = RequestMethod.DELETE,
            produces = "application/json"
    )
    @ResponseBody
    public ResponseEntity<InformationSource> deleteInformationSource(
            @PathVariable long sourceid
    ) throws InvalidParametersException, ResourceNotFoundException {

        InformationSource source
                = (InformationSource) helper.returnOrException(informationSourceService.get(sourceid));
        informationSourceService.clear();
        informationSourceManager.removeSource(sourceid);
        return new ResponseEntity<>(source, HttpStatus.OK);
    }

    /**
     * Returns StatObject containing the amount of InformationSources in the
     * system.
     *
     * @return StatObject with number of sources in the system
     */
    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/count", produces = "application/json")
    @ResponseBody
    public StatObject getInformationSourceCount() {
        return new StatObject("numberOfSources", "The number of information sources added to the system.", informationSourceService.count());
    }

    /**
     * List default amount (max 10) InformationSources in the system.
     *
     * @return List of InformationSources with max length of 10
     *
     * @throws InvalidParametersException thrown if parameters given in the
     *                                    request were incorrect of are in
     *                                    conflict
     */
    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/list", produces = "application/json")
    @ResponseBody
    public List<InformationSource> listInformationSourcesList() throws InvalidParametersException {
        return informationSourceService.get(0, 10);
    }

    /**
     * List given amount of InformationSources in the system.
     *
     * @param amount Amount to be listed
     *
     * @return List of InformationSources
     *
     * @throws InvalidParametersException thrown if parameters given in the
     *                                    request were incorrect of are in
     *                                    conflict
     */
    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/list/{amount}", produces = "application/json")
    @ResponseBody
    public List<InformationSource> listInformationSourcesListAmount(
            @PathVariable int amount
    ) throws InvalidParametersException {
        helper.exceptionIfWrongLimits(0, amount);
        return informationSourceService.get(0, amount);
    }

    /**
     * Returns a list of InformationSources in the database from index [to] to
     * [from].
     *
     * @param from Starting index to list InformationSources from
     * @param to   Ending index to list InformationSources to
     *
     * @return List of InformationSources
     *
     * @throws InvalidParametersException thrown if parameters given in the
     *                                    request were incorrect of are in
     *                                    conflict
     */
    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping(value = "/list/{to}/{from}", produces = "application/json")
    @ResponseBody
    public List<InformationSource> listInformationSourcesListFromTo(
            @PathVariable int from,
            @PathVariable int to
    ) throws InvalidParametersException {
        helper.exceptionIfWrongLimits(to, from);
        return informationSourceService.get(to, from);
    }

    /**
     * Force a InformationSource to be read instantly, but not more frequently
     * than once every 10 seconds.
     *
     * @param sourceid ID of the InformationSource to be read
     *
     * @return SuccessObject if the read was successful
     *
     * @throws TooManyRequestsException thrown if a request has been made too
     *                                  early
     */
    @Secured({"ROLE_MODERATOR"})
    @RequestMapping(value = "/{sourceid}/read", produces = "application/json")
    @ResponseBody
    public ResponseEntity<SuccessObject> readInformationSource(
            @PathVariable long sourceid
    ) throws TooManyRequestsException {
        if(lastRequests.containsKey(sourceid)&&lastRequestTooClose(sourceid)) {
            ErrorLogger.log(
                    ErrorType.NOT_ACCEPTED,
                    ErrorSeverity.LOW,
                    "Too many forced requests for a single source.",
                    "Read request can only be issued every 10 seconds for a single source"
            );
            throw new TooManyRequestsException();
        }

        informationSourceManager.readSource(sourceid);
        lastRequests.put(sourceid, System.currentTimeMillis());
        return new ResponseEntity<>(new SuccessObject("Source was read successfully."), HttpStatus.OK);
    }

    /*
     * Only allows a single source to be read once every 10 seconds.
     */
    private boolean lastRequestTooClose(long sourceid) {
        return System.currentTimeMillis()-lastRequests.get(sourceid)<TimeUnit.SECONDS.toMillis(10);
    }

    /**
     * Manually set new InformationSourceService to be used.
     *
     * @param informationSourceService InformationSourceService to be set.
     *
     * @see InformationSourceService
     */
    public void setInformationSourceService(InformationSourceService informationSourceService) {
        this.informationSourceService = informationSourceService;
    }

    /**
     * Manually set new InformationSourceManager to be used.
     *
     * @param informationSourceManager InformationSourceManager to be set.
     *
     * @see InformationSourceManager
     */
    public void setInformationSourceManager(InformationSourceManager informationSourceManager) {
        this.informationSourceManager = informationSourceManager;
    }

    /**
     * Manually set new RestAPIHelper to be used.
     *
     * @param helper Helper to be set
     *
     * @see RestAPIHelper
     */
    public void setRestAPIHelper(RestAPIHelper helper) {
        this.helper = helper;
    }
}
