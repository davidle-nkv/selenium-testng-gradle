package com.nakivo.pages.overview;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OverviewPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By overviewPageTitle = By.xpath("//h1[contains(text(),'Overview')] | //div[contains(@class,'overview')]";
    private final By jobDashboardMenu = By.xpath("//a[contains(text(),'Job Dashboard')] | //span[contains(text(),'Job Dashboard')]";

    public OverviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isOverviewPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(overviewPageTitle));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void navigateToJobDashboard() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(jobDashboardMenu));
        element.click();
    }
}