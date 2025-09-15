package com.nakivo.pages.dashboard_bk;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

/**
 * Description.
 *
 * Author: David Le
 * Date: 9/12/2025
 * Time: 4:19 PM
 */

public class DashboardPage_bk {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By dashboardHeader = By.xpath("//h1[contains(text(),'Dashboard')] | //div[@id='dashboard'] | //div[contains(@class,'dashboard')]");

    public DashboardPage_bk(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isDashboardDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(dashboardHeader));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isUserOnDashboard() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlContains("/configuration"));
    }
}
