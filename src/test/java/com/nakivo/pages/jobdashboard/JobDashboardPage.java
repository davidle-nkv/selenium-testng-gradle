package com.nakivo.pages.jobdashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class JobDashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By addButton = By.xpath("//button[contains(@class, 'add-button')]|//button[@title='Add']|//button[text()='+']");
    private By replicationProxmoxOption = By.xpath("//a[contains(text(), 'Replication for Proxmox VE')]|//span[contains(text(), 'Replication for Proxmox VE')]");
    
    public JobDashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public JobDashboardPage clickAddButton() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(addButton));
        element.click();
        return this;
    }
    
    public void selectReplicationForProxmoxVE() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(replicationProxmoxOption));
        element.click();
    }
}