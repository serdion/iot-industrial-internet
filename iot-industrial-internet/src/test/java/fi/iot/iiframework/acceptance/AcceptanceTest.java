/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.acceptance;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

@Ignore
// comment this @Ignore line if you want to run the acceptance tests, but make sure that the
// server is running first by pressing Run Project!
public class AcceptanceTest {

    private WebDriver webDriver;

    @Before
    public void open() {
        webDriver = new HtmlUnitDriver();
        webDriver.get("http://localhost:8080");
    }

    @Test
    public void webInterfaceOpensProperly() {
        String title = webDriver.getTitle();
        assertEquals("Helo world!", title);
        
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
