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

public class ProxmoxReplicationPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final int WAIT_TIMEOUT = 15;

    // Locators
    private final By overviewLink = By.xpath("//a[contains(@href, '/c/overview')]");
    private final By jobDashboardLink = By.xpath("//a[contains(text(), 'Job Dashboard')] | //a[contains(@href, '/jobs')]");
    private final By addJobButton = By.xpath("//button[contains(@class, 'add-job')] | //button[contains(., '+')] | //button[@aria-label='Add Job']");
    private final By replicationProxmoxOption = By.xpath("//li[contains(text(), 'Replication for Proxmox VE')] | //a[contains(text(), 'Replication for Proxmox VE')]");
    private final By wizardTitle = By.xpath("//h1[contains(text(), 'New Replication Job Wizard for Proxmox VE')] | //div[contains(@class, 'wizard-title')][contains(text(), 'New Replication Job Wizard')]");
    private final By vmCheckbox = By.xpath("(//input[@type='checkbox'][contains(@class, 'vm-checkbox')] | //input[@type='checkbox'][contains(@name, 'vm')])[1]");
    private final By nextButton = By.xpath("//button[contains(text(), 'Next')] | //button[contains(@class, 'next-btn')]");
    private final By changeTrackingDropdown = By.xpath("//select[contains(@name, 'changeTracking')] | //div[contains(@class, 'change-tracking')]//select | //label[contains(text(), 'Change tracking')]//following-sibling::select");
    private final By changeTrackingDropdownDiv = By.xpath("//div[contains(@class, 'dropdown') and .//label[contains(text(), 'Change tracking')]] | //div[@data-testid='change-tracking-dropdown']");
    private final By dropdownOptions = By.xpath("//option | //li[contains(@class, 'dropdown-option')]");

    public ProxmoxReplicationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT));
    }

    public boolean isOverviewPageDisplayed() {
        try {
            wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("/c/overview"),
                ExpectedConditions.presenceOfElementLocated(overviewLink)
            ));
            return driver.getCurrentUrl().contains("/c/overview");
        } catch (Exception e) {
            return false;
        }
    }

    public ProxmoxReplicationPage navigateToJobDashboard() {
        WebElement jobDashboard = wait.until(ExpectedConditions.elementToBeClickable(jobDashboardLink));
        jobDashboard.click();
        return this;
    }

    public ProxmoxReplicationPage clickAddJobButton() {
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(addJobButton));
        addButton.click();
        return this;
    }

    public ProxmoxReplicationPage selectReplicationForProxmox() {
        WebElement replicationOption = wait.until(ExpectedConditions.elementToBeClickable(replicationProxmoxOption));
        replicationOption.click();
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

    public ProxmoxReplicationPage selectFirstVM() {
        WebElement vm = wait.until(ExpectedConditions.elementToBeClickable(vmCheckbox));
        if (!vm.isSelected()) {
            vm.click();
        }
        return this;
    }

    public ProxmoxReplicationPage navigateToLastStep() {
        // Navigate through wizard steps to reach the last step with change tracking
        int maxSteps = 10; // Safety limit
        for (int i = 0; i < maxSteps; i++) {
            try {
                // Check if change tracking dropdown is present
                if (isChangeTrackingDropdownPresent()) {
                    break;
                }
                // Click Next if available
                WebElement next = driver.findElement(nextButton);
                if (next.isDisplayed() && next.isEnabled()) {
                    next.click();
                    wait.until(ExpectedConditions.stalenessOf(next));
                }
            } catch (Exception e) {
                // Continue if Next button is not found
            }
        }
        return this;
    }

    private boolean isChangeTrackingDropdownPresent() {
        try {
            WebElement dropdown = driver.findElement(changeTrackingDropdown);
            return dropdown.isDisplayed();
        } catch (Exception e) {
            try {
                WebElement dropdownDiv = driver.findElement(changeTrackingDropdownDiv);
                return dropdownDiv.isDisplayed();
            } catch (Exception ex) {
                return false;
            }
        }
    }

    public List<String> getChangeTrackingOptions() {
        try {
            // Try to find select element first
            WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(changeTrackingDropdown));
            Select select = new Select(dropdown);
            return select.getOptions().stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // If not a select element, try to click and get options
            try {
                WebElement dropdownDiv = driver.findElement(changeTrackingDropdownDiv);
                dropdownDiv.click();
                List<WebElement> options = driver.findElements(dropdownOptions);
                return options.stream()
                        .map(WebElement::getText)
                        .collect(Collectors.toList());
            } catch (Exception ex) {
                return List.of();
            }
        }
    }

    public String getSelectedChangeTrackingOption() {
        try {
            WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(changeTrackingDropdown));
            Select select = new Select(dropdown);
            return select.getFirstSelectedOption().getText();
        } catch (Exception e) {
            // For custom dropdowns
            try {
                WebElement selectedOption = driver.findElement(By.xpath("//div[contains(@class, 'change-tracking')]//span[contains(@class, 'selected')] | //div[@data-testid='change-tracking-dropdown']