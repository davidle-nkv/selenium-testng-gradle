package com.nakivo.pages.njm124958.replication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ProxmoxReplicationWizardPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final int WAIT_TIMEOUT = 15;

    // Locators
    private final By jobDashboardLink = By.xpath("//a[contains(@href, '/jobs') or contains(text(), 'Job Dashboard')]");
    private final By addJobButton = By.xpath("//button[contains(@class, 'add') or contains(text(), '+')]");
    private final By replicationProxmoxOption = By.xpath("//div[contains(text(), 'Replication for Proxmox VE') or contains(text(), 'Proxmox VE')]//parent::*");
    private final By wizardTitle = By.xpath("//h1[contains(text(), 'New Replication Job Wizard') or contains(text(), 'Proxmox VE')]");
    private final By vmSelector = By.xpath("//div[contains(@class, 'vm-selector')]//input[@type='checkbox'][1]");
    private final By nextButton = By.xpath("//button[contains(text(), 'Next') or contains(@class, 'next')]");
    private final By changeTrackingDropdown = By.xpath("//select[contains(@name, 'changeTracking') or contains(@id, 'changeTracking')]//parent::div");
    private final By changeTrackingSelect = By.xpath("//select[contains(@name, 'changeTracking') or contains(@id, 'changeTracking')]");
    private final By changeTrackingOptions = By.xpath("//select[contains(@name, 'changeTracking') or contains(@id, 'changeTracking')]//option");
    private final By proxmoxCbtOption = By.xpath("//option[contains(text(), 'Use Proxmox VE CBT')]");
    private final By proprietaryMethodOption = By.xpath("//option[contains(text(), 'Use proprietary method')]");
    private final By noChangeTrackingOption = By.xpath("//option[contains(text(), 'No change tracking (always full)')]");

    public ProxmoxReplicationWizardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT));
    }

    public ProxmoxReplicationWizardPage navigateToJobDashboard() {
        wait.until(ExpectedConditions.elementToBeClickable(jobDashboardLink)).click();
        return this;
    }

    public ProxmoxReplicationWizardPage clickAddJobButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addJobButton)).click();
        return this;
    }

    public ProxmoxReplicationWizardPage selectReplicationForProxmoxVE() {
        wait.until(ExpectedConditions.elementToBeClickable(replicationProxmoxOption)).click();
        return this;
    }

    public boolean isWizardDisplayed() {
        try {
            WebElement wizard = wait.until(ExpectedConditions.visibilityOfElementLocated(wizardTitle));
            return wizard.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public ProxmoxReplicationWizardPage selectFirstVM() {
        wait.until(ExpectedConditions.elementToBeClickable(vmSelector)).click();
        return this;
    }

    public ProxmoxReplicationWizardPage clickNext() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
        return this;
    }

    public ProxmoxReplicationWizardPage navigateToLastStep() {
        // Navigate through wizard steps - assuming we need to click Next multiple times
        for (int i = 0; i < 5; i++) {
            try {
                WebElement next = driver.findElement(nextButton);
                if (next.isEnabled() && next.isDisplayed()) {
                    next.click();
                    Thread.sleep(500); // Small wait between steps
                }
            } catch (Exception e) {
                // Next button might not be available, we reached the last step
                break;
            }
        }
        return this;
    }

    public ProxmoxReplicationWizardPage clickChangeTrackingDropdown() {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(changeTrackingSelect));
        dropdown.click();
        return this;
    }

    public boolean isProxmoxCbtOptionDisplayed() {
        try {
            WebElement option = wait.until(ExpectedConditions.presenceOfElementLocated(proxmoxCbtOption));
            return option.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isProxmoxCbtOptionSelected() {
        try {
            WebElement option = driver.findElement(proxmoxCbtOption);
            return option.isSelected();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isProprietaryMethodOptionDisplayed() {
        try {
            WebElement option = wait.until(ExpectedConditions.presenceOfElementLocated(proprietaryMethodOption));
            return option.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNoChangeTrackingOptionDisplayed() {
        try {
            WebElement option = wait.until(ExpectedConditions.presenceOfElementLocated(noChangeTrackingOption));
            return option.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public List<WebElement> getChangeTrackingOptions() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(changeTrackingOptions));
    }

    public boolean verifyChangeTrackingDropdownOptions() {
        clickChangeTrackingDropdown();
        boolean proxmoxCbtDisplayed = isProxmoxCbtOptionDisplayed();
        boolean proprietaryMethodDisplayed = isProprietaryMethodOptionDisplayed();
        boolean noChangeTrackingDisplayed = isNoChangeTrackingOptionDisplayed();
        return proxmoxCbtDisplayed && proprietaryMethodDisplayed && noChangeTrackingDisplayed;
    }
}