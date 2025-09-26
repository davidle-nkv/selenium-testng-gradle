package com.nakivo.pages.njm1234.replication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ProxmoxReplicationJobPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final int WAIT_TIMEOUT = 15;

    // Locators
    private final By overviewPageTitle = By.xpath("//h1[contains(text(),'Overview')] | //div[@class='overview-header']");
    private final By jobDashboardLink = By.xpath("//a[contains(@href,'/jobs')] | //span[text()='Jobs'] | //a[text()='Job Dashboard']");
    private final By createJobButton = By.xpath("//button[@aria-label='Create job'] | //button[contains(@class,'add-job')] | //button[@title='Create New Job'] | //button[text()='+'] | //button[contains(@class,'btn-create')]");
    private final By replicationProxmoxOption = By.xpath("//span[contains(text(),'Replication for Proxmox VE')] | //a[contains(text(),'Replication for Proxmox VE')] | //div[contains(text(),'Replication for Proxmox VE')]");
    private final By wizardTitle = By.xpath("//h2[contains(text(),'New Replication Job')] | //div[contains(text(),'New Replication Job Wizard')]");
    private final By vmCheckboxes = By.xpath("//input[@type='checkbox'][contains(@name,'vm')] | //div[@class='vm-item']//input[@type='checkbox']");
    private final By nextButton = By.xpath("//button[text()='Next'] | //button[contains(@class,'btn-next')] | //button[@type='button'][contains(text(),'Next')]");
    private final By changeTrackingDropdown = By.xpath("//select[contains(@name,'changeTracking')] | //div[contains(@class,'change-tracking')]//select | //label[contains(text(),'Change tracking')]//following-sibling::select");
    private final By changeTrackingDropdownButton = By.xpath("//button[contains(@aria-label,'Change tracking')] | //div[contains(@class,'dropdown-toggle')][contains(text(),'Change tracking')] | //div[@class='change-tracking-dropdown']//button");
    private final By changeTrackingOptions = By.xpath("//div[@class='dropdown-menu show']//a | //ul[@class='dropdown-menu']//li | //option");
    private final By proxmoxCbtOption = By.xpath("//option[contains(text(),'Use Proxmox VE CBT')] | //a[contains(text(),'Use Proxmox VE CBT')] | //li[contains(text(),'Use Proxmox VE CBT')]");
    private final By proprietaryMethodOption = By.xpath("//option[contains(text(),'Use proprietary method')] | //a[contains(text(),'Use proprietary method')] | //li[contains(text(),'Use proprietary method')]");
    private final By noChangeTrackingOption = By.xpath("//option[contains(text(),'No change tracking')] | //a[contains(text(),'No change tracking')] | //li[contains(text(),'always full')]");
    private final By selectedChangeTrackingValue = By.xpath("//select[contains(@name,'changeTracking')]/option[@selected] | //div[contains(@class,'change-tracking')]//span[@class='selected-value'] | //button[contains(@aria-label,'Change tracking')]/span");

    public ProxmoxReplicationJobPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT));
    }

    public ProxmoxReplicationJobPage waitForOverviewPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(overviewPageTitle));
        return this;
    }

    public ProxmoxReplicationJobPage navigateToJobDashboard() {
        WebElement jobDashboard = wait.until(ExpectedConditions.elementToBeClickable(jobDashboardLink));
        jobDashboard.click();
        return this;
    }

    public ProxmoxReplicationJobPage clickCreateJobButton() {
        WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(createJobButton));
        createButton.click();
        return this;
    }

    public ProxmoxReplicationJobPage selectReplicationForProxmox() {
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

    public ProxmoxReplicationJobPage selectFirstVM() {
        List<WebElement> vms = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(vmCheckboxes));
        if (!vms.isEmpty() && !vms.get(0).isSelected()) {
            vms.get(0).click();
        }
        return this;
    }

    public ProxmoxReplicationJobPage clickNext() {
        WebElement next = wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        next.click();
        return this;
    }

    public ProxmoxReplicationJobPage navigateToLastStep() {
        // Click Next multiple times to reach the last step
        // The actual number of steps may vary, so we'll click Next until we find the Change Tracking dropdown
        int maxSteps = 10;
        for (int i = 0; i < maxSteps; i++) {
            try {
                // Check if Change Tracking dropdown is present
                if (isChangeTrackingDropdownPresent()) {
                    break;
                }
                // If not, click Next if available
                WebElement next = driver.findElement(nextButton);
                if (next.isEnabled()) {
                    next.click();
                    wait.until(ExpectedConditions.stalenessOf(next));
                }
            } catch (Exception e) {
                // Continue to next iteration
            }
        }
        return this;
    }

    public boolean isChangeTrackingDropdownPresent() {
        try {
            WebElement dropdown = driver.findElement(changeTrackingDropdown);
            return dropdown.isDisplayed();
        } catch (Exception e) {
            try {
                WebElement dropdownButton = driver.findElement(changeTrackingDropdownButton);
                return dropdownButton.isDisplayed();
            } catch (Exception ex) {
                return false;
            }
        }
    }

    public ProxmoxReplicationJobPage openChangeTrackingDropdown() {
        try {
            // Try to click on select