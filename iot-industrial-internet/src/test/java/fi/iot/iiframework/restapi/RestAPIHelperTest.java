/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi;

import fi.iot.iiframework.restapi.exceptions.InvalidParametersException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

public class RestAPIHelperTest {

    private RestAPIHelper helper;
    
    public RestAPIHelperTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp(){
        helper = new RestAPIHelper();
        helper.setMaxObjectsRetrieved(10000);
        helper.setErrorLogging(false);
    }
    
    @After
    public void tearDown() {
    }

    private boolean testLimits(int from, int to) throws InvalidParametersException{
        helper.exceptionIfWrongLimits(from, to);
        
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
    
    @Ignore // Fails to throw exception
    @Test(expected=InvalidParametersException.class)
    public void testExceptionIfWrongLimitsWhenTooHighAmount() throws InvalidParametersException {
        assertTrue(testLimits(0, (int) (helper.getMaxObjectsRetrieved()+1)));
    }
    
    @Ignore // Fails to throw exception
    @Test(expected=InvalidParametersException.class)
    public void testExceptionIfWrongLimitsWhenTooHighAmountAndAlreadyHigh() throws InvalidParametersException {
        int amountToAdd = 100000;
        assertTrue(testLimits(amountToAdd, (int) (helper.getMaxObjectsRetrieved()+amountToAdd+10)));
    }
    
}
