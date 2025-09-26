package com.nakivo.pages.njm.replication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ProxmoxReplicationJobPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final int WAIT_TIMEOUT = 15;

    // Locators
    private final By plusButton = By.xpath("//button[contains(@class, 'add-button') or @aria-label='Add']//span[text()='+']");
    private final By replicationOptionForProxmox = By.xpath("//div[contains(text(), 'Replication for Proxmox VE')] | //span[contains(text(), 'Replication for Proxmox VE')]");
    private final By wizardTitle = By.xpath("//h1[contains(text(), 'New Replication Job Wizard')] | //div[contains(@class, 'wizard-title') and contains(text(), 'Replication Job')]");
    private final By vmCheckbox = By.xpath("(//input[@type='checkbox'][contains(@class, 'vm-selector')] | //div[contains(@class, 'vm-item')]//input[@type='checkbox'])[1]");
    private final By nextButton = By.xpath("//button[contains(text(), 'Next') or contains(@class, 'next-button')]");
    private final By changeTrackingDropdown = By.xpath("//select[@id='changeTracking'] | //div[contains(@class, 'dropdown') and contains(., 'Change tracking')] | //label[contains(text(), 'Change tracking')]/following-sibling::*[1]//select | //div[@aria-label='Change tracking']//div[contains(@class, 'dropdown')]");
    private final By changeTrackingOptions = By.xpath("//option[contains(@class, 'tracking-option')] | //div[contains(@class, 'dropdown-item')] | //li[contains(@class, 'dropdown-option')]");
    private final By proxmoxCbtOption = By.xpath("//option[contains(text(), 'Use Proxmox VE CBT')] | //div[contains(text(), 'Use Proxmox VE CBT')] | //li[contains(text(), 'Use Proxmox VE CBT')]");
    private final By proprietaryMethodOption = By.xpath("//option[contains(text(), 'Use proprietary method')] | //div[contains(text(), 'Use proprietary method')] | //li[contains(text(), 'Use proprietary method')]");
    private final By noChangeTrackingOption = By.xpath("//option[contains(text(), 'No change tracking')] | //div[contains(text(), 'No change tracking')] | //li[contains(text(), 'No change tracking')]");
    private final By selectedDropdownValue = By.xpath("//select[@id='changeTracking']/option[@selected] | //div[contains(@class, 'dropdown')]//span[contains(@class, 'selected')] | //div[@aria-label='Change tracking']//div[contains(@class, 'selected-value')]");

    public ProxmoxReplicationJobPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT));
    }

    public ProxmoxReplicationJobPage clickPlusButton() {
        wait.until(ExpectedConditions.elementToBeClickable(plusButton)).click();
        return this;
    }

    public ProxmoxReplicationJobPage selectReplicationForProxmoxVE() {
        wait.until(ExpectedConditions.elementToBeClickable(replicationOptionForProxmox)).click();
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

    public ProxmoxReplicationJobPage selectFirstVM() {
        wait.until(ExpectedConditions.elementToBeClickable(vmCheckbox)).click();
        return this;
    }

    public ProxmoxReplicationJobPage clickNext() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
        return this;
    }

    public ProxmoxReplicationJobPage navigateToLastStep() {
        // Navigate through all wizard steps
        int maxSteps = 10; // Safety limit
        for (int i = 0; i < maxSteps; i++) {
            try {
                WebElement next = driver.findElement(nextButton);
                if (next.isEnabled() && next.isDisplayed()) {
                    next.click();
                    Thread.sleep(500); // Small delay between steps
                } else {
                    break;
                }
            } catch (Exception e) {
                // Reached last step or next button not available
                break;
            }
        }
        return this;
    }

    public ProxmoxReplicationJobPage clickChangeTrackingDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(changeTrackingDropdown)).click();
        return this;
    }

    public boolean isProxmoxCbtOptionDisplayed() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(proxmoxCbtOption));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isProprietaryMethodOptionDisplayed() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(proprietaryMethodOption));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNoChangeTrackingOptionDisplayed() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(noChangeTrackingOption));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getSelectedChangeTrackingOption() {
        try {
            WebElement selected = wait.until(ExpectedConditions.visibilityOfElementLocated(selectedDropdownValue));
            return selected.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isProxmoxCbtSelectedByDefault() {
        String selectedText = getSelectedChangeTrackingOption();
        return selectedText.contains("Use Proxmox VE CBT");
    }

    public List<WebElement> getAllChangeTrackingOptions() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(changeTrackingOptions));
    }
}