/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi.exceptions;

import fi.iot.iiframework.application.Application;
import fi.iot.iiframework.errors.ErrorLogger;
import fi.iot.iiframework.errors.ErrorSeverity;
import fi.iot.iiframework.restapi.exceptions.InvalidParametersException;
import fi.iot.iiframework.restapi.exceptions.ResourceNotFoundException;
import fi.iot.iiframework.restapi.exceptions.InvalidObjectException;
import fi.iot.iiframework.errors.ErrorType;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionCatcher {

    /**
     * Catches ResourceNotFoundExceptions created by RestAPI and notifies the
     * user with RestAPIError object that contains an ErrorType and a message.
     *
     * @return ResponseEntity with RestAPIError object
     */
    @RequestMapping(value = "/error/resourcenotfound", produces = "application/json")
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ResponseEntity<RestAPIExceptionObject> resourceNotFoundException() {
        return new ResponseEntity<>(
                new RestAPIExceptionObject(
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
    public ResponseEntity<RestAPIExceptionObject> invalidParametersException() {
        return new ResponseEntity<>(
                new RestAPIExceptionObject(
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
    public ResponseEntity<RestAPIExceptionObject> invalidObjectException() {
        return new ResponseEntity<>(
                new RestAPIExceptionObject(
                        ErrorType.INVALID_OBJECT,
                        "Object was invalid or wrong type."
                ), HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * Catches InvalidParametersException created by RestAPI and notifies the
     * user with RestAPIError object that contains an ErrorType and a message.
     *
     * @return ResponseEntity with RestAPIError object
     */
    @RequestMapping(value = "/error/toomanyrequests", produces = "application/json")
    @ExceptionHandler(TooManyRequestsException.class)
    @ResponseBody
    public ResponseEntity<RestAPIExceptionObject> tooManyRequestsException() {
        return new ResponseEntity<>(
                new RestAPIExceptionObject(
                        ErrorType.NOT_ACCEPTED,
                        "You have performed too many requests in a row, please wait a second before trying again."
                ), HttpStatus.TOO_MANY_REQUESTS);
    }

    /**
     * Catches ShouldBeBooleanException created by RestAPI and notifies the user
     * with RestAPIError object that contains an ErrorType and a message.
     *
     * @return ResponseEntity with RestAPIError object
     */
    @RequestMapping(value = "/error/toomanyrequests", produces = "application/json")
    @ExceptionHandler(ShouldBeBooleanException.class)
    @ResponseBody
    public ResponseEntity<RestAPIExceptionObject> shouldBeBooleanException() {
        return new ResponseEntity<>(
                new RestAPIExceptionObject(
                        ErrorType.BAD_REQUEST,
                        "Provided string could not be converted into a boolean."
                ), HttpStatus.BAD_REQUEST);
    }

    /**
     * Catches ShouldBeBooleanException created by RestAPI and notifies the user
     * with RestAPIError object that contains an ErrorType and a message.
     *
     * @param ex
     * @throws AccessDeniedException to be handled elsewhere.
     * @return 
     */
    @RequestMapping(value = "/error/accessdenied", produces = "application/json")
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ResponseEntity<RestAPIExceptionObject> accessDeniedException(AccessDeniedException ex) {
        throw new RuntimeException(ex);
    }

    /**
     * Catches all Exceptions created by RestAPI and notifies the user with
     * RestAPIError object that contains an ErrorType and a message.
     *
     * @param req
     * @param exception
     * @return ResponseEntity with RestAPIError object
     */
    @RequestMapping(value = "/error/unidentifiederror", produces = "application/json")
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<RestAPIExceptionObject> unidentifiedException(
            HttpServletRequest req,
            Exception exception) {
        Application.logger.log(Level.SEVERE, exception.toString());
        ErrorLogger.log(
                ErrorType.UNKNOWN_ERROR,
                ErrorSeverity.FATAL,
                "An unknown error occured while processing request: " + req.getRequestURL(),
                exception.toString()
        );

        return new ResponseEntity<>(
                new RestAPIExceptionObject(
                        ErrorType.UNKNOWN_ERROR,
                        "An unknown error occured."
                ), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
