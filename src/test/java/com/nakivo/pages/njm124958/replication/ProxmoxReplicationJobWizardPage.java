package com.nakivo.pages.njm124958.replication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ProxmoxReplicationJobWizardPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final int WAIT_TIMEOUT = 15;
    
    // Locators
    private final By addJobButton = By.xpath("//button[contains(@class, 'add-job-button') or @title='Add Job' or .//span[text()='+']]");
    private final By replicationProxmoxOption = By.xpath("//a[contains(text(), 'Replication for Proxmox VE')] | //span[contains(text(), 'Replication for Proxmox VE')]");
    private final By wizardTitle = By.xpath("//h1[contains(text(), 'New Replication Job Wizard for Proxmox VE')] | //div[contains(@class, 'wizard-title')][contains(text(), 'New Replication Job Wizard for Proxmox VE')]");
    private final By vmCheckbox = By.xpath("(//input[@type='checkbox'][contains(@class, 'vm-checkbox')] | //div[contains(@class, 'vm-list')]//input[@type='checkbox'])[1]");
    private final By nextButton = By.xpath("//button[contains(text(), 'Next')] | //button[contains(@class, 'next-button')]");
    private final By changeTrackingDropdown = By.xpath("//select[contains(@id, 'change-tracking')] | //div[contains(@class, 'change-tracking')]//select | //label[contains(text(), 'Change tracking')]//following-sibling::select");
    private final By changeTrackingDropdownOptions = By.xpath("//select[contains(@id, 'change-tracking')]//option | //div[contains(@class, 'change-tracking')]//option");
    private final By jobNameInput = By.xpath("//input[@name='jobName' or @id='jobName' or contains(@placeholder, 'Job name')]");
    
    public ProxmoxReplicationJobWizardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT));
    }
    
    public ProxmoxReplicationJobWizardPage clickAddJobButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addJobButton)).click();
        return this;
    }
    
    public ProxmoxReplicationJobWizardPage selectReplicationForProxmox() {
        wait.until(ExpectedConditions.elementToBeClickable(replicationProxmoxOption)).click();
        return this;
    }
    
    public boolean isWizardDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(wizardTitle)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public ProxmoxReplicationJobWizardPage selectFirstVM() {
        wait.until(ExpectedConditions.elementToBeClickable(vmCheckbox)).click();
        return this;
    }
    
    public ProxmoxReplicationJobWizardPage enterJobName(String name) {
        WebElement jobNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(jobNameInput));
        jobNameField.clear();
        jobNameField.sendKeys(name);
        return this;
    }
    
    public ProxmoxReplicationJobWizardPage clickNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
        return this;
    }
    
    public ProxmoxReplicationJobWizardPage navigateToLastStep() {
        // Click Next multiple times to reach the last step
        for (int i = 0; i < 5; i++) {
            try {
                if (isChangeTrackingDropdownVisible()) {
                    break;
                }
                clickNextButton();
                Thread.sleep(500); // Small wait between steps
            } catch (Exception e) {
                // Continue clicking next
            }
        }
        return this;
    }
    
    public boolean isChangeTrackingDropdownVisible() {
        try {
            return driver.findElement(changeTrackingDropdown).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getSelectedChangeTrackingOption() {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(changeTrackingDropdown)));
        return select.getFirstSelectedOption().getText();
    }
    
    public List<String> getChangeTrackingOptions() {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(changeTrackingDropdown)));
        return select.getOptions().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
    
    public boolean isUseProxmoxCBTSelectedByDefault() {
        String selectedOption = getSelectedChangeTrackingOption();
        return selectedOption.contains("Use Proxmox VE CBT");
    }
}