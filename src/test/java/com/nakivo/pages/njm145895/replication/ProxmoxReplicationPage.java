package com.nakivo.pages.njm145895.replication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProxmoxReplicationPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final int WAIT_TIMEOUT = 15;

    // Login Page Locators
    private final By usernameField = By.cssSelector("input[placeholder='Username']");
    private final By passwordField = By.cssSelector("input[placeholder='Password']");
    private final By loginButton = By.xpath("//button[.//span[text()='Log In']]");

    // Navigation Locators
    private final By dataProtectionMenu = By.xpath("//div[@role='presentation' and @title='Data Protection' and contains(@class,'smplIconMain')]");
    private final By createJobButton = By.xpath("//div[contains(@class,'create-btn') and contains(@class,'clickable')]");
    private final By replicationForProxmoxLink = By.xpath("//a[@role='presentation' and contains(@class,'popupLink') and normalize-space(text())='Replication for Proxmox VE']");

    // Source Step Locators
    private final By sourceItemCheckbox = By.xpath("//div[contains(@class,'x-grid-cell-inner')][contains(text(),'cong-empty-vm-06')]/input[@class='x-tree-checkbox']");
    private final By nextButton = By.xpath("//button[normalize-space()='Next']");

    // Destination Step Locators
    private final By containerSelector = By.xpath("//div[contains(@class,'item-selector')]//div[contains(@class,'glT1') and contains(normalize-space(.),'Select target container')]");
    private final By containerItem = By.xpath("//div[@class='x-grid-cell-inner' and normalize-space(text())='proxmox02']");
    private final By targetStorageSelector = By.xpath("//div[contains(@class,'item-selector')]//div[contains(@class,'glT1') and contains(normalize-space(.),'Select target storage')]");
    private final By targetStorageItem = By.xpath("//div[@class='stItemViewDiv1' and normalize-space(text())='DirectoryFS-Proxmox02']");
    private final By poolSelector = By.xpath("//div[contains(@class,'item-selector')]//div[contains(@class,'glT1') and contains(normalize-space(.),'Select target pool (optional)')]");
    private final By poolItem = By.xpath("//div[@class='inLineName ellipsis' and normalize-space(text())='khapool01']");

    // Schedule Step Locators
    private final By doNotScheduleCheckbox = By.xpath("//div[contains(@class,'manual-schedule') and .//label[normalize-space()='Do not schedule, run on demand']]//input[contains(@class,'x-form-checkbox') or @role='checkbox']");

    // Options Step Locators
    private final By finishButton = By.xpath("//button[normalize-space()='Finish']");

    public ProxmoxReplicationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT));
    }

    // Login Methods
    public ProxmoxReplicationPage enterUsername(String username) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        element.clear();
        element.sendKeys(username);
        return this;
    }

    public ProxmoxReplicationPage enterPassword(String password) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        element.clear();
        element.sendKeys(password);
        return this;
    }

    public ProxmoxReplicationPage clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        return this;
    }

    // Navigation Methods
    public ProxmoxReplicationPage clickDataProtection() {
        wait.until(ExpectedConditions.elementToBeClickable(dataProtectionMenu)).click();
        return this;
    }

    public ProxmoxReplicationPage clickCreateJob() {
        wait.until(ExpectedConditions.elementToBeClickable(createJobButton)).click();
        return this;
    }

    public ProxmoxReplicationPage clickReplicationForProxmox() {
        wait.until(ExpectedConditions.elementToBeClickable(replicationForProxmoxLink)).click();
        return this;
    }

    // Source Step Methods
    public ProxmoxReplicationPage selectSourceItem() {
        wait.until(ExpectedConditions.elementToBeClickable(sourceItemCheckbox)).click();
        return this;
    }

    public ProxmoxReplicationPage clickNext() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
        return this;
    }

    // Destination Step Methods
    public ProxmoxReplicationPage clickContainerSelector() {
        wait.until(ExpectedConditions.elementToBeClickable(containerSelector)).click();
        return this;
    }

    public ProxmoxReplicationPage selectContainer() {
        wait.until(ExpectedConditions.elementToBeClickable(containerItem)).click();
        return this;
    }

    public ProxmoxReplicationPage clickTargetStorageSelector() {
        wait.until(ExpectedConditions.elementToBeClickable(targetStorageSelector)).click();
        return this;
    }

    public ProxmoxReplicationPage selectTargetStorage() {
        wait.until(ExpectedConditions.elementToBeClickable(targetStorageItem)).click();
        return this;
    }

    public ProxmoxReplicationPage clickPoolSelector() {
        wait.until(ExpectedConditions.elementToBeClickable(poolSelector)).click();
        return this;
    }

    public ProxmoxReplicationPage selectPool() {
        wait.until(ExpectedConditions.elementToBeClickable(poolItem)).click();
        return this;
    }

    // Schedule Step Methods
    public ProxmoxReplicationPage clickDoNotSchedule() {
        wait.until(ExpectedConditions.elementToBeClickable(doNotScheduleCheckbox)).click();
        return this;
    }

    // Options Step Methods
    public ProxmoxReplicationPage clickFinish() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
        return this;
    }

    // Validation Methods
    public boolean isLoggedIn() {
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(loginButton));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isWizardOpened() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(nextButton)) != null;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isJobCreated() {
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(finishButton));
        } catch (Exception e) {
            return false;
        }
    }
}