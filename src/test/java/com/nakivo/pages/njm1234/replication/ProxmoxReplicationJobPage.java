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
    private final By addJobButton = By.xpath("//button[contains(@class, 'add-job-button') or contains(text(), '+')]//ancestor::button[1]");
    private final By replicationProxmoxOption = By.xpath("//span[contains(text(), 'Replication for Proxmox VE')] | //a[contains(text(), 'Replication for Proxmox VE')]");
    private final By wizardTitle = By.xpath("//h1[contains(text(), 'New Replication Job Wizard for Proxmox VE')] | //div[contains(@class, 'wizard-title')][contains(text(), 'New Replication Job Wizard')]");
    private final By vmCheckbox = By.xpath("(//input[@type='checkbox'][contains(@class, 'vm-checkbox')] | //div[contains(@class, 'vm-list')]//input[@type='checkbox'])[1]");
    private final By nextButton = By.xpath("//button[contains(text(), 'Next')] | //button[contains(@class, 'next-button')]");
    private final By changeTrackingDropdown = By.xpath("//select[contains(@id, 'changeTracking')] | //div[contains(@class, 'change-tracking')]//select | //label[contains(text(), 'Change tracking')]//following-sibling::select");
    private final By changeTrackingDropdownOptions = By.xpath("//select[contains(@id, 'changeTracking')]//option | //div[contains(@class, 'change-tracking')]//option");
    private final By finishButton = By.xpath("//button[contains(text(), 'Finish')] | //button[contains(@class, 'finish-button')]");
    private final By jobNameInput = By.xpath("//input[@id='jobName' or @name='jobName' or contains(@placeholder, 'Job name')]");

    public ProxmoxReplicationJobPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT));
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

    public ProxmoxReplicationJobPage clickNext() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
        return this;
    }

    public ProxmoxReplicationJobPage navigateToLastStep() {
        // Navigate through all steps until we reach the last one with Change tracking
        int maxSteps = 10; // Safety limit
        for (int i = 0; i < maxSteps; i++) {
            try {
                // Check if we're at the last step (Change tracking is visible)
                if (driver.findElements(changeTrackingDropdown).size() > 0) {
                    break;
                }
                // Check if Finish button is visible (alternative last step indicator)
                if (driver.findElements(finishButton).size() > 0) {
                    break;
                }
                clickNext();
                // Small wait between steps
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'loading') or contains(@class, 'spinner')]"))); 
            } catch (Exception e) {
                // Continue if Next is not available
                break;
            }
        }
        return this;
    }

    public boolean isChangeTrackingDropdownVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(changeTrackingDropdown)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public List<String> getChangeTrackingOptions() {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(changeTrackingDropdown)));
        return select.getOptions().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public String getSelectedChangeTrackingOption() {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(changeTrackingDropdown)));
        return select.getFirstSelectedOption().getText();
    }

    public boolean isProxmoxCBTSelectedByDefault() {
        String selectedOption = getSelectedChangeTrackingOption();
        return selectedOption.contains("Use Proxmox VE CBT");
    }

    public boolean verifyChangeTrackingOptions() {
        List<String> options = getChangeTrackingOptions();
        boolean hasProxmoxCBT = options.stream().anyMatch(opt -> opt.contains("Use Proxmox VE CBT"));
        boolean hasProprietaryMethod = options.stream().anyMatch(opt -> opt.contains("Use proprietary method"));
        boolean hasNoChangeTracking = options.stream().anyMatch(opt -> opt.contains("No change tracking") && opt.contains("always full"));
        
        return hasProxmoxCBT && hasProprietaryMethod && hasNoChangeTracking;
    }
}