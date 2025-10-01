package com.nakivo.pages.njm1234.replication;

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

    // Login page locators
    private final By usernameField = By.cssSelector("input[placeholder='Username']");
    private final By passwordField = By.cssSelector("input[placeholder='Password']");
    private final By loginButton = By.xpath("//button[.//span[text()='Log In']]");

    // Navigation locators
    private final By dataProtectionMenu = By.xpath("//div[@role='presentation' and @title='Data Protection' and contains(@class,'smplIconMain')]");
    private final By createJobButton = By.xpath("//div[contains(@class,'create-btn') and contains(@class,'clickable')]");
    private final By replicationForProxmoxLink = By.xpath("//a[@role='presentation' and contains(@class,'popupLink') and normalize-space(text())='Replication for Proxmox VE']");

    // Job configuration locators
    private final By vmItemCheckbox = By.xpath("//div[contains(@class,'x-grid-cell-inner')][contains(text(),'cong-empty-vm-06')]/input[@class='x-tree-checkbox']");
    private final By containerItem = By.xpath("//div[@class='x-grid-cell-inner' and normalize-space(text())='proxmox02']");
    private final By storageItem = By.xpath("//div[@class='stItemViewDiv1' and normalize-space(text())='DirectoryFS-Proxmox02']");
    private final By poolItem = By.xpath("//div[@class='inLineName ellipsis' and normalize-space(text())='khapool01']");
    private final By doNotScheduleOption = By.xpath("//div[contains(@class,'manual-schedule') and .//label[normalize-space()='Do not schedule, run on demand']]//input[contains(@class,'x-form-checkbox') or @role='checkbox']");
    private final By nextButton = By.xpath("//button[normalize-space()='Next']");
    private final By finishButton = By.xpath("//button[normalize-space()='Finish']");

    public ProxmoxReplicationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT));
    }

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
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        element.click();
        return this;
    }

    public ProxmoxReplicationPage clickDataProtection() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(dataProtectionMenu));
        element.click();
        return this;
    }

    public ProxmoxReplicationPage clickCreateJob() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(createJobButton));
        element.click();
        return this;
    }

    public ProxmoxReplicationPage clickReplicationForProxmox() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(replicationForProxmoxLink));
        element.click();
        return this;
    }

    public ProxmoxReplicationPage selectVMItem() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(vmItemCheckbox));
        element.click();
        return this;
    }

    public ProxmoxReplicationPage selectContainer() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(containerItem));
        element.click();
        return this;
    }

    public ProxmoxReplicationPage selectStorage() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(storageItem));
        element.click();
        return this;
    }

    public ProxmoxReplicationPage selectPool() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(poolItem));
        element.click();
        return this;
    }

    public ProxmoxReplicationPage selectDoNotSchedule() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(doNotScheduleOption));
        element.click();
        return this;
    }

    public ProxmoxReplicationPage clickNext() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        element.click();
        return this;
    }

    public ProxmoxReplicationPage clickFinish() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(finishButton));
        element.click();
        return this;
    }

    public boolean isLoginButtonVisible() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDataProtectionMenuVisible() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(dataProtectionMenu));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFinishButtonVisible() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(finishButton));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}