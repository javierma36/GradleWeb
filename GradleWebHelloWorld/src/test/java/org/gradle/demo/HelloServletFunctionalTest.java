/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gradle.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
import org.junit.After;
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
public class HelloServletFunctionalTest {
    private WebDriver  driver;
    
    @BeforeClass
    public static void setupClass(){
        WebDriverManager.chromedriver().setup();
    }
    
    @Before
    public void setUp(){
        driver = new ChromeDriver();
    }
    
    @After
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
    
    @Test
    public void sayHello(){
        driver.get("http://localhost:8080/GradleWebHelloWorld");
        
        driver.findElement(By.id("say-hello-text-input")).sendKeys("Dolly");
        driver.findElement(By.id("say-hello-button")).click();
        
        Assert.assertEquals("Hello Page", driver.getTitle());
        Assert.assertEquals("Hello Dolly", driver.findElement(By.tagName("h2")).getText());
    }
    
}
