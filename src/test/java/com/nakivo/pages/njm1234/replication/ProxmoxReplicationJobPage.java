package com.nakivo.pages.njm1234.replication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ProxmoxReplicationJobPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final int WAIT_TIMEOUT = 15;
    
    // Locators
    private final By jobDashboardLink = By.xpath("//a[contains(@href, '/jobs') or contains(text(), 'Job Dashboard')]");
    private final By addJobButton = By.xpath("//button[contains(@class, 'add') or @aria-label='Add' or .//span[text()='+']]");
    private final By replicationProxmoxOption = By.xpath("//a[contains(text(), 'Replication for Proxmox VE')] | //span[contains(text(), 'Replication for Proxmox VE')]");
    private final By wizardTitle = By.xpath("//h1[contains(text(), 'New Replication Job Wizard')] | //div[contains(@class, 'wizard-title')][contains(text(), 'Proxmox VE')]");
    private final By vmCheckbox = By.xpath("//input[@type='checkbox'][ancestor::div[contains(@class, 'vm-item')]][1]");
    private final By nextButton = By.xpath("//button[contains(text(), 'Next')] | //button[contains(@class, 'next')]");
    private final By changeTrackingDropdown = By.xpath("//select[contains(@id, 'changeTracking')] | //div[contains(@class, 'dropdown')][.//label[contains(text(), 'Change tracking')]]//button");
    private final By changeTrackingOptions = By.xpath("//select[contains(@id, 'changeTracking')]/option | //div[contains(@class, 'dropdown-menu')]//a | //ul[@role='listbox']//li");
    private final By selectedChangeTrackingOption = By.xpath("//select[contains(@id, 'changeTracking')]/option[@selected] | //div[contains(@class, 'dropdown')]//span[contains(@class, 'selected')] | //button[contains(@aria-expanded, 'false')]/span[1]");
    private final By jobNameInput = By.xpath("//input[@name='jobName' or @id='jobName']");
    
    public ProxmoxReplicationJobPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT));
    }
    
    public ProxmoxReplicationJobPage navigateToJobDashboard() {
        wait.until(ExpectedConditions.elementToBeClickable(jobDashboardLink)).click();
        return this;
    }
    
    public ProxmoxReplicationJobPage clickAddJobButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addJobButton)).click();
        return this;
    }
    
    public ProxmoxReplicationJobPage selectReplicationForProxmoxVE() {
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
    
    public ProxmoxReplicationJobPage selectFirstVM() {
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(vmCheckbox));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        return this;
    }
    
    public ProxmoxReplicationJobPage enterJobName(String name) {
        WebElement jobName = wait.until(ExpectedConditions.visibilityOfElementLocated(jobNameInput));
        jobName.clear();
        jobName.sendKeys(name);
        return this;
    }
    
    public ProxmoxReplicationJobPage clickNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
        return this;
    }
    
    public ProxmoxReplicationJobPage navigateToLastStep() {
        // Click Next multiple times to reach the last step with Change tracking
        for (int i = 0; i < 5; i++) {
            try {
                if (isChangeTrackingDropdownVisible()) {
                    break;
                }
                wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
                Thread.sleep(500); // Small wait between steps
            } catch (Exception e) {
                // Continue to next iteration
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
    
    public ProxmoxReplicationJobPage openChangeTrackingDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(changeTrackingDropdown)).click();
        return this;
    }
    
    public List<String> getChangeTrackingOptions() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(changeTrackingOptions));
        List<WebElement> options = driver.findElements(changeTrackingOptions);
        return options.stream()
            .map(WebElement::getText)
            .filter(text -> !text.isEmpty())
            .collect(Collectors.toList());
    }
    
    public String getSelectedChangeTrackingOption() {
        try {
            WebElement selected = wait.until(ExpectedConditions.visibilityOfElementLocated(selectedChangeTrackingOption));
            return selected.getText();
        } catch (Exception e) {
            // Try to get from dropdown button text
            return driver.findElement(changeTrackingDropdown).getText();
        }
    }
    
    public boolean isProxmoxCBTSelectedByDefault() {
        String selectedOption = getSelectedChangeTrackingOption();
        return selectedOption != null && selectedOption.contains("Use Proxmox VE CBT");
    }
}