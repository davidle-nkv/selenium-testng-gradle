package com.nakivo.pages.njm124958.replication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ProxmoxReplicationWizardPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final int WAIT_TIMEOUT = 15;

    // Locators
    private final By addJobButton = By.xpath("//button[contains(@class, 'add-job') or @title='Create new job' or .//span[text()='+']]");
    private final By replicationProxmoxOption = By.xpath("//a[contains(text(), 'Replication for Proxmox VE')] | //span[contains(text(), 'Replication for Proxmox VE')]");
    private final By wizardTitle = By.xpath("//h1[contains(text(), 'New Replication Job Wizard for Proxmox VE')] | //div[contains(@class, 'wizard-title')][contains(text(), 'New Replication Job Wizard for Proxmox VE')]");
    private final By vmCheckbox = By.xpath("(//input[@type='checkbox'][contains(@class, 'vm-select')] | //div[contains(@class, 'vm-list')]//input[@type='checkbox'])[1]");
    private final By nextButton = By.xpath("//button[contains(text(), 'Next')] | //button[@type='button'][contains(@class, 'next-btn')]");
    private final By changeTrackingDropdown = By.xpath("//select[contains(@id, 'change-tracking')] | //div[contains(@class, 'change-tracking')]//select | //label[contains(text(), 'Change tracking')]/..//select");
    private final By changeTrackingDropdownOptions = By.xpath("//select[contains(@id, 'change-tracking')]//option | //div[contains(@class, 'change-tracking')]//option");
    private final By jobNameInput = By.xpath("//input[@name='jobName' or @id='jobName' or contains(@placeholder, 'Job name')]");

    public ProxmoxReplicationWizardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT));
    }

    public ProxmoxReplicationWizardPage clickAddJobButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addJobButton)).click();
        return this;
    }

    public ProxmoxReplicationWizardPage selectReplicationForProxmox() {
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

    public ProxmoxReplicationWizardPage selectFirstVM() {
        wait.until(ExpectedConditions.elementToBeClickable(vmCheckbox)).click();
        return this;
    }

    public ProxmoxReplicationWizardPage enterJobName(String jobName) {
        WebElement jobNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(jobNameInput));
        jobNameElement.clear();
        jobNameElement.sendKeys(jobName);
        return this;
    }

    public ProxmoxReplicationWizardPage clickNext() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
        return this;
    }

    public ProxmoxReplicationWizardPage navigateToLastStep() {
        // Navigate through wizard steps
        int maxSteps = 10;
        for (int i = 0; i < maxSteps; i++) {
            try {
                WebElement nextBtn = driver.findElement(nextButton);
                if (nextBtn.isEnabled() && nextBtn.isDisplayed()) {
                    nextBtn.click();
                    Thread.sleep(500); // Small wait between steps
                }
                // Check if we've reached the last step (where change tracking dropdown exists)
                if (isChangeTrackingDropdownVisible()) {
                    break;
                }
            } catch (Exception e) {
                // Continue if next button not found or not clickable
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

    public List<String> getChangeTrackingOptions() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(changeTrackingDropdown));
        Select select = new Select(driver.findElement(changeTrackingDropdown));
        return select.getOptions().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public String getSelectedChangeTrackingOption() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(changeTrackingDropdown));
        Select select = new Select(driver.findElement(changeTrackingDropdown));
        return select.getFirstSelectedOption().getText();
    }

    public boolean isChangeTrackingOptionPresent(String optionText) {
        return getChangeTrackingOptions().stream()
                .anyMatch(option -> option.contains(optionText));
    }
}