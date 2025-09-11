package testngtest;

import org.openqa.selenium.chrome.ChromeOptions;
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

@Listeners({ VideoListener.class, testngtest.LogListener.class, testngtest.ScreenshotListener.class })
public class Test1 {
    public String baseUrl = "https://www.browserstack.com/";
    public WebDriver driver;    

    @BeforeTest
    public void launchBrowser() {        
//        System.out.println("Launching Chrome browser");
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.get(baseUrl);

        System.out.println("Launching Chrome browser in headless mode");
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless=new");     // Run headless (new mode for Chrome >=109)
//        options.addArguments("--no-sandbox");       // Required in many CI environments
//        options.addArguments("--disable-dev-shm-usage"); // Avoid /dev/shm space issues
//        options.addArguments("--remote-allow-origins=*"); // Helps with some versions of ChromeDriver

        options.addArguments("--headless=new");  // modern headless mode
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");  // important for Xvfb
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
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
