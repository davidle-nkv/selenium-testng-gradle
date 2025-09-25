package com.nakivo.pages.jobdashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class JobDashboardPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By addJobButton = By.xpath("//button[contains(@class,'add')] | //button[@title='Add'] | //button[contains(text(),'+')]";
    private final By replicationProxmoxOption = By.xpath("//a[contains(text(),'Replication for Proxmox VE')] | //span[contains(text(),'Replication for Proxmox VE')]";

    public JobDashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public JobDashboardPage clickAddJobButton() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(addJobButton));
        element.click();
        return this;
    }

    public void selectReplicationForProxmoxVE() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(replicationProxmoxOption));
        element.click();
    }
}