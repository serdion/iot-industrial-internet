/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi;

import fi.iot.iiframework.domain.Validatable;
import fi.iot.iiframework.restapi.exceptions.ResourceNotFoundException;
import fi.iot.iiframework.restapi.exceptions.InvalidParametersException;
import fi.iot.iiframework.restapi.exceptions.InvalidObjectException;
import fi.iot.iiframework.errors.ErrorLogger;
import fi.iot.iiframework.errors.ErrorSeverity;
import fi.iot.iiframework.errors.ErrorType;
import fi.iot.iiframework.restapi.exceptions.ShouldBeBooleanException;
import org.springframework.stereotype.Component;

@Component
public class RestAPIHelper {

    /*
     * {from} cannot be negative
     * {to} cannot be negative
     * {to} cannot be equal to {from}
     * {to} cannot be smaller than {from}
     * {from} minus {to} cannot be bigger than default max objects retrieved
     */
    public void exceptionIfWrongLimits(int from, int to) throws InvalidParametersException {
        if (from < 0 || to <= 0 || to == from || from > to
                || (from - to) > 10000) {
            ErrorLogger.log(ErrorType.BAD_REQUEST, ErrorSeverity.LOW, "Invalid parameters given for limits (" + from + ", " + to + ") in RestAPI.");
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
            ErrorLogger.log(ErrorType.NOT_FOUND, ErrorSeverity.LOW, "Resource request could not be found in RestAPI.");
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
            ErrorLogger.log(ErrorType.IO_ERROR, ErrorSeverity.LOW, "Object recieved was invalid or wrong type in RestAPI.");
            throw new InvalidObjectException();
        }
    }

    /**
     * Checks if given string resembles a boolean or not and throws exception if
     * not.
     *
     * @param str String to compare
     * @return boolean representation of the String
     * @throws ShouldBeBooleanException if String doesn't resemble a boolean
     */
    public boolean checkIfStringIsBoolean(String str) throws ShouldBeBooleanException {
        if (str.equals("true")) {
            return true;
        }
        if (str.equals("false")) {
            return false;
        }

        throw new ShouldBeBooleanException();
    }
}
