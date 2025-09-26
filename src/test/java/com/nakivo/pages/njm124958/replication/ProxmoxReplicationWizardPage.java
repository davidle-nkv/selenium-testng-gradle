package com.nakivo.pages.njm124958.replication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProxmoxReplicationWizardPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final int WAIT_TIMEOUT = 15;

    // Locators
    private By jobDashboardLink = By.xpath("//a[contains(@href, '/c/jobs') or contains(text(), 'Jobs')]|//span[contains(text(), 'Jobs')]/parent::a");
    private By addJobButton = By.xpath("//button[@aria-label='Add job' or contains(@class, 'add-job')]|//button[.//span[text()='+']]|//button[contains(@class, 'btn-primary') and contains(text(), '+')]|//button[@title='Create new job']");
    private By replicationProxmoxOption = By.xpath("//a[contains(text(), 'Replication for Proxmox VE')]|//li[contains(text(), 'Replication for Proxmox VE')]|//span[contains(text(), 'Replication for Proxmox VE')]/parent::*");
    private By wizardTitle = By.xpath("//h1[contains(text(), 'New Replication Job Wizard for Proxmox VE')]|//div[contains(@class, 'wizard-title') and contains(text(), 'New Replication Job Wizard for Proxmox VE')]");
    private By vmCheckbox = By.xpath("(//input[@type='checkbox' and contains(@class, 'vm-selection')]|//div[contains(@class, 'vm-list')]//input[@type='checkbox'])[1]");
    private By nextButton = By.xpath("//button[contains(text(), 'Next')]|//button[contains(@class, 'btn-next')]|//button[@aria-label='Next']");
    private By changeTrackingDropdown = By.xpath("//select[@id='change-tracking' or contains(@name, 'changeTracking')]|//div[contains(@class, 'dropdown') and .//label[contains(text(), 'Change tracking')]]//button|//div[contains(text(), 'Change tracking')]/following-sibling::div//button");
    private By changeTrackingOptions = By.xpath("//select[@id='change-tracking']//option|//ul[contains(@class, 'dropdown-menu')]//li|//div[contains(@class, 'dropdown-item')]");
    private By useProxmoxCBTOption = By.xpath("//option[contains(text(), 'Use Proxmox VE CBT')]|//li[contains(text(), 'Use Proxmox VE CBT')]|//div[contains(text(), 'Use Proxmox VE CBT')]");
    private By useProprietaryOption = By.xpath("//option[contains(text(), 'Use proprietary method')]|//li[contains(text(), 'Use proprietary method')]|//div[contains(text(), 'Use proprietary method')]");
    private By noChangeTrackingOption = By.xpath("//option[contains(text(), 'No change tracking (always full)')]|//li[contains(text(), 'No change tracking (always full)')]|//div[contains(text(), 'No change tracking (always full)')]");
    private By selectedChangeTrackingOption = By.xpath("//select[@id='change-tracking']/option[@selected]|//div[contains(@class, 'dropdown')]//button/span[1]|//div[contains(text(), 'Change tracking')]/following-sibling::div//span[contains(@class, 'selected')]");

    public ProxmoxReplicationWizardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT));
    }

    public ProxmoxReplicationWizardPage clickJobDashboard() {
        wait.until(ExpectedConditions.elementToBeClickable(jobDashboardLink)).click();
        return this;
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
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(vmCheckbox));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        return this;
    }

    public ProxmoxReplicationWizardPage clickNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
        return this;
    }

    public ProxmoxReplicationWizardPage navigateToLastStep() {
        int maxSteps = 10;
        for (int i = 0; i < maxSteps; i++) {
            try {
                WebElement dropdown = driver.findElement(changeTrackingDropdown);
                if (dropdown.isDisplayed()) {
                    break;
                }
            } catch (Exception e) {
                // Continue to next step
            }
            
            try {
                WebElement next = driver.findElement(nextButton);
                if (next.isEnabled()) {
                    next.click();
                    Thread.sleep(500);
                }
            } catch (Exception e) {
                break;
            }
        }
        return this;
    }

    public ProxmoxReplicationWizardPage clickChangeTrackingDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(changeTrackingDropdown)).click();
        return this;
    }

    public boolean isProxmoxCBTOptionDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(useProxmoxCBTOption)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isProprietaryMethodOptionDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(useProprietaryOption)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNoChangeTrackingOptionDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(noChangeTrackingOption)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getSelectedChangeTrackingOption() {
        try {
            WebElement selected = wait.until(ExpectedConditions.visibilityOfElementLocated(selectedChangeTrackingOption));
            return selected.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isProxmoxCBTSelectedByDefault() {
        String selectedText = getSelectedChangeTrackingOption();
        return selectedText.contains("Use Proxmox VE CBT");
    }

    public int getChangeTrackingOptionsCount() {
        try {
            List<WebElement> options = driver.findElements(changeTrackingOptions);
            return options.size();
        } catch (Exception e) {
            return 0;
        }
    }
}