/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.acceptance;

import fi.iot.iiframework.application.Application;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes=Application.class)
//@WebAppConfiguration

@Ignore
// comment this @Ignore line if you want to run the acceptance tests, but make sure that the
// server is running first by pressing Run Project!
public class AcceptanceTest {

    private WebDriver webDriver;

    @Before
    public void open() {
//        SpringApplication.run(Application.class);
        webDriver = new HtmlUnitDriver();
    }

    @Test
    public void webInterfaceOpensProperly() {
        webDriver.get("http://localhost:8080");
        String title = webDriver.getTitle();
        assertEquals("Hello world!", title);
        
        WebElement element = webDriver.findElement(By.id("wrapper"));
        String tagname = element.getTagName();
        assertEquals("div", tagname);
    }
    
    @Test
    public void webInterfaceShowsSomeXMLData() {
        webDriver.get("http://localhost:8080/sources/example/view");
        String title = webDriver.getTitle();
        assertEquals("List of all Sensors", title);
        
        WebElement element = webDriver.findElement(By.linkText("Readouts"));
        assertNotEquals(null, element);  
    }

    @After
    public void close() {
        webDriver.quit();
    }
}
