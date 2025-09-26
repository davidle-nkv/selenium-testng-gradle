package com.nakivo.tests.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

/**
 * Description.
 *
 * Author: David Le
 * Date: 9/12/2025
 * Time: 4:03 PM
 */
public class BaseTest {
    protected WebDriver driver;

//    @BeforeClass
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");  // important for Xvfb
        options.addArguments("--window-size=1920,1080");
        options.setAcceptInsecureCerts(true);   // Allow self-signed/invalid SSL certs

        driver = new ChromeDriver(options);
//        driver.manage().window().maximize();

    }

//    @AfterClass
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

}
