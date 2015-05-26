/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.acceptancetesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AcceptanceTesting {

    private WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new FirefoxDriver();
        webDriver.get("http://localhost:8080");
    }

    @Test
    public void webInterfaceOpensProperly() {
//        WebElement element = webDriver.findElement(By.id("wrapper"));
//        String tagname = element.getTagName();
//        assertEquals("div", tagname);
    }
    
    @After
    public void close() {
        webDriver.quit();
    }
}
