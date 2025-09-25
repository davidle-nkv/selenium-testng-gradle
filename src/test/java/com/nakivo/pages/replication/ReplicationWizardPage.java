package com.nakivo.pages.replication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ReplicationWizardPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By wizardTitle = By.xpath("//h1[contains(text(),'New Replication Job Wizard for Proxmox VE')] | //div[contains(text(),'New Replication Job Wizard')]";
    private final By vmCheckbox = By.xpath("(//input[@type='checkbox'])[1]";
    private final By nextButton = By.xpath("//button[contains(text(),'Next')] | //button[contains(@class,'next')]";
    private final By changeTrackingDropdown = By.xpath("//select[@name='changeTracking'] | //select[contains(@class,'change-tracking')]";
    private final By jobNameField = By.xpath("//input[@name='jobName'] | //input[contains(@placeholder,'Job name')]";
    private final By finishButton = By.xpath("//button[contains(text(),'Finish')] | //button[contains(@class,'finish')]";

    public ReplicationWizardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isWizardDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(wizardTitle));
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

    public ReplicationWizardPage enterJobName(String jobName) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(jobNameField));
        element.clear();
        element