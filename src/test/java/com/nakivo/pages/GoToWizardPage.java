package com.nakivo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class GoToWizardPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final int TIMEOUT = 15;
    
    private By dataProtectionButton = By.xpath("//div[@role='presentation' and @title='Data Protection' and contains(@class,'smplIconMain')]");
    private By createJobButton = By.xpath("//div[contains(@class,'create-btn') and contains(@class,'clickable')]");
    private By replicationForProxmoxLink = By.xpath("//a[@role='presentation' and contains(@class,'popupLink') and normalize-space(text())='Replication for Proxmox VE']");

    public GoToWizardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    public GoToWizardPage clickDataProtection() {
        wait.until(ExpectedConditions.elementToBeClickable(dataProtectionButton));
        driver.findElement(dataProtectionButton).click();
        return this;
    }

    public GoToWizardPage clickCreateJob() {
        wait.until(ExpectedConditions.elementToBeClickable(createJobButton));
        driver.findElement(createJobButton).click();
        return this;
    }

    public void clickReplicationForProxmox() {
        wait.until(ExpectedConditions.elementToBeClickable(replicationForProxmoxLink));
        driver.findElement(replicationForProxmoxLink).click();
    }

    public void performGoToWizard() {
        clickDataProtection();
        clickCreateJob();
        clickReplicationForProxmox();
    }
}