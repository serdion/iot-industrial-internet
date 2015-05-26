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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class AcceptanceTest {

    private WebDriver webDriver;

    @Before
    public void open() {
        webDriver = new HtmlUnitDriver();
        webDriver.get("http://localhost:8080");
    }

//    @Test
//    public void webInterfaceOpensProperly() {
//        // all of the tests in this class now work IF you first choose Run Project and have the server running properly
//        
//        String title = webDriver.getTitle();
//        assertEquals("Hello world!", title);
//        
//        WebElement element = webDriver.findElement(By.id("wrapper"));
//        String tagname = element.getTagName();
//        assertEquals("div", tagname);
//    }
//    
//    @Test
//    public void webInterfaceShowsSomeXMLData() {
//        webDriver.get("http://localhost:8080/sources/example/view");
//        String title = webDriver.getTitle();
//        assertEquals("List of all Sensors", title);
//        
//        WebElement element = webDriver.findElement(By.linkText("Readouts"));
//        assertNotEquals(null, element);  
//    }

    @After
    public void close() {
        webDriver.quit();
    }
}
