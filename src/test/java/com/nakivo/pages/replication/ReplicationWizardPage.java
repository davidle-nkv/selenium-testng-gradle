package com.nakivo.pages.replication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;
import java.util.List;

public class ReplicationWizardPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By wizardTitle = By.xpath("//h1[contains(text(), 'New Replication Job Wizard')]|//h2[contains(text(), 'Replication Job Wizard for Proxmox VE')]");
    private By vmCheckbox = By.xpath("(//input[@type='checkbox'])[1]|//tr[1]//input[@type='checkbox']");
    private By nextButton = By.xpath("//button[contains(text(), 'Next')]|//button[contains(@class, 'next-button')]");
    private By finishButton = By.xpath("//button[contains(text(), 'Finish')]|//button[contains(@class, 'finish-button')]");
    private By changeTrackingDropdown = By.xpath("//select[contains(@name, 'changeTracking')]|//div[contains(@class, 'change-tracking')]//select");
    private By dropdownOptions = By.xpath("//select[contains(@name, 'changeTracking')]//option|//div[contains(@class, 'change-tracking')]//option");
    
    public ReplicationWizardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public boolean isWizardDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(wizardTitle));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public ReplicationWizardPage selectFirstVM() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(vmCheckbox));
        if (!element.isSelected()) {
            element.click();
        }
        return this;
    }
    
    public Re