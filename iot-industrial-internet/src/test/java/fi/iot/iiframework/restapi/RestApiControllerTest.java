/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi;

import fi.iot.iiframework.application.Application;
import fi.iot.iiframework.application.ApplicationSettings;
import fi.iot.iiframework.application.TestConfig;
import static org.junit.Assert.assertTrue;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringApplicationConfiguration(classes = {TestConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@Ignore
public class RestApiControllerTest {

    @Autowired
    private RestApiController api;
    
    @Autowired
    private ApplicationSettings settings;
    
    private boolean testLimits(int from, int to) throws InvalidParametersException{
        api.exceptionIfWrongLimits(from, to);
        
        return true;
    }
    
    @Test(expected=InvalidParametersException.class)
    public void testExceptionIfWrongLimitsWhenFromIsNegative() throws InvalidParametersException {
        assertTrue(testLimits(-10, 20));
    }
    
    @Test(expected=InvalidParametersException.class)
    public void testExceptionIfWrongLimitsWhenToIsNegative() throws InvalidParametersException {
        assertTrue(testLimits(10, -20));
    }
    
    @Test(expected=InvalidParametersException.class)
    public void testExceptionIfWrongLimitsWhenBothAreNegative() throws InvalidParametersException {
        assertTrue(testLimits(-10, -20));
    }
    
    @Test(expected=InvalidParametersException.class)
    public void testExceptionIfWrongLimitsWhen() throws InvalidParametersException {
        assertTrue(testLimits(-10, -20));
    }
    
    @Test(expected=InvalidParametersException.class)
    public void testExceptionIfWrongLimitsWhenBothAreEqual() throws InvalidParametersException {
        assertTrue(testLimits(10, 10));
    }
    
    @Test(expected=InvalidParametersException.class)
    public void testExceptionIfWrongLimitsWhenFromIsGreaterThanTo() throws InvalidParametersException {
        assertTrue(testLimits(50, 20));
    }
    
    @Test(expected=InvalidParametersException.class)
    public void testExceptionIfWrongLimitsWhenTooHighAmount() throws InvalidParametersException {
        assertTrue(testLimits(0, settings.getMaxObjectsRetrievedFromDatabase()+1));
    }
    
    @Test(expected=InvalidParametersException.class)
    public void testExceptionIfWrongLimitsWhenTooHighAmountAndAlreadyHigh() throws InvalidParametersException {
        assertTrue(testLimits(100000, settings.getMaxObjectsRetrievedFromDatabase()+100001));
    }
    
}
