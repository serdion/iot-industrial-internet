/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.errors;

import java.util.Date;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class ErrorTest {

    private static Error[] testErrors;
    private static Date now;

    @BeforeClass
    public static void setUpClass() {
        now = new Date();
        testErrors = new Error[3];

    }

    @Before
    public void setUp() {
        testErrors[0] = new Error(ErrorType.BAD_CONFIGURATION, now, "Hello");
        testErrors[1] = new Error(ErrorType.TIMEOUT_ERROR, new Date(), "muh");
        testErrors[2] = new Error(ErrorType.CONFLICT_ERROR, new Date(), "muh");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void correctErrorNameAndIdSaved() {
        assertTrue(testErrors[1].getType().getName().equals(ErrorType.TIMEOUT_ERROR.getName()));
        assertTrue(testErrors[0].getType().getId().equals(ErrorType.BAD_CONFIGURATION.getId()));
    }

    @Test
    public void correctDescriptionSaved() {
        assertTrue(testErrors[2].getDescription().equals("muh"));
    }

    @Test
    public void correctTimeSaved() {
        assertTrue(testErrors[0].getDate() == now);
    }

    @Test
    public void changedTypeSaved() {
        testErrors[0].setType(ErrorType.NOT_FOUND);
        assertTrue(testErrors[0].getType() == ErrorType.NOT_FOUND);
    }

    @Test
    public void changedDateSaved() {
        assertTrue(testErrors[0].getDate() == now);
        Date newdate = new Date();
        testErrors[0].setDate(newdate);
        assertTrue(testErrors[0].getDate().equals(newdate));
    }

    @Test
    public void changedDescpritionSaved() {
        testErrors[2].setDescription("test");
        assertTrue(testErrors[2].getDescription().equals("test"));
    }

}
