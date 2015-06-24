/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.acceptance;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

@Ignore
public class LoginAcceptance {

    private WebDriver driver;

    private String baseUrl = "http://localhost:8080/";

    @Before
    public void setUp() {
        // SpringApplication.run(Application.class);
        this.driver = new HtmlUnitDriver();
    }

    @Test
    public void loginSuccessfullWithCorrectDetails() {
        driver.get(baseUrl + "/login");
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("moderator");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("moderator");
        driver.findElement(By.xpath("//button")).click();

        assertTrue(driver.getPageSource().contains("IIFramework for Java"));
    }
}
