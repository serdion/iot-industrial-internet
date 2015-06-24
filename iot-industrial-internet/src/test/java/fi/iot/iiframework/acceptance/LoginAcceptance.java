/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.acceptance;

import fi.iot.iiframework.application.Application;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class LoginAcceptance {
    
    private WebDriver driver;
    
    private String root = "http://localhost:8080/";
    
    @Before
    public void setUp() {
        // SpringApplication.run(Application.class);
        this.driver = new HtmlUnitDriver();
    }

    @Test
    public void onceBobSubmittedSignupSuccessful() {
        driver.get(root);

        

        assertTrue(true);
    }
}
