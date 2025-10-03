package com.nakivo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoToWizardPage {
    private WebDriver driver;
    private By dataProtectionButton = By.xpath("//div[@role='presentation' and @title='Data Protection' and contains(@class,'smplIconMain')]");
    private By createJobButton = By.xpath("//div[contains(@class,'create-btn') and contains(@class,'clickable')]");
    private By replicationForProxmoxLink = By.xpath("//a[@role='presentation' and contains(@class,'popupLink') and normalize-space(text())='Replication for Proxmox VE']");

    public GoToWizardPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickDataProtection() {
        driver.findElement(dataProtectionButton).click();
    }

    public void clickCreateJob() {
        driver.findElement(createJobButton).click();
    }

    public void clickReplicationForProxmox() {
        driver.findElement(replicationForProxmoxLink).click();
    }

    public void performGoToWizard() {
        clickDataProtection();
        clickCreateJob();
        clickReplicationForProxmox();
    }
}