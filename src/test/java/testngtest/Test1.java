package testngtest;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.Listeners;

@Listeners({ testngtest.VideoListener.class, testngtest.LogListener.class, testngtest.ScreenshotListener.class })
public class Test1 {
    public String baseUrl = "https://www.browserstack.com/";
    public WebDriver driver;    

    @BeforeTest
    public void launchBrowser() {        
        System.out.println("Launching Chrome browser");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(baseUrl);
    }

    @Test
    public void verifyHomepageTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains("BrowserStack"));
        String expectedTitle = "Browser Stack"; // "Most Reliable App & Cross Browser Testing Platform | BrowserStack";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @AfterTest
    public void terminateBrowser() {
        if (driver != null) {
            driver.quit();
        }        
    }
}
