package com.nakivo.pages.overview;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class OverviewPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By overviewPageIdentifier = By.xpath("//div[contains(@class, 'overview-page')]|//h1[contains(text(), 'Overview')]");
    private By jobDashboardLink = By.xpath("//a[contains(text(), 'Job Dashboard')]|//span[contains(text(), 'Job Dashboard')]");
    
    public OverviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public boolean isOverviewPageDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(overviewPageIdentifier));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public void navigateToJobDashboard() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(jobDashboardLink));
        element.click();
    }
}