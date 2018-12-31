/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gradle.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author mauriciobedoya
 */
public class GradleWebServletFunctionalTest {
    private WebDriver driver;
    
    @BeforeClass
    public static void setUpClass(){
        WebDriverManager.chromedriver().setup();
    }
    
    @Before
    public void setUpTest(){
        driver = new ChromeDriver();
    }
    
    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
     @Test
    public void test() {
        driver.get("http://localhost:8080/GradleWebCalculator");
        driver.findElement(By.id("value1-add-calculator")).sendKeys("5");
        driver.findElement(By.id("value2-add-calculator")).sendKeys("5");
        driver.findElement(By.id("add-calculator-button")).click();
        
        Assert.assertEquals("Addition Result: 10.0",driver.findElement(By.tagName("h2")).getText());
        
    }
}
