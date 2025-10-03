package com.nakivo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class DestinationStepPage {
    private static final int WAIT_TIMEOUT = 15;
    private WebDriver driver;
    private WebDriverWait wait;
    
    private By containerSelector = By.xpath("//div[contains(@class,'item-selector')]//div[contains(@class,'glT1') and contains(normalize-space(.),'Select target container')]");
    private By containerItem = By.xpath("//div[contains(@class,'x-grid-cell-inner') and normalize-space(text())='proxmox02']");
    private By targetStorageSelector = By.xpath("//div[contains(@class,'item-selector')]//div[contains(@class,'glT1') and contains(normalize-space(.),'Select target storage')]");
    private By targetStorageItem = By.xpath("//div[@class='stItemViewDiv1' and normalize-space(text())='DirectoryFS-Proxmox02']");
    private By poolSelector = By.xpath("//div[contains(@class,'item-selector')]//div[contains(@class,'glT1') and contains(normalize-space(.),'Select target pool (optional)')]");
    private By poolItem = By.xpath("//div[@class='inLineName ellipsis' and normalize-space(text())='khapool01']");
    private By nextButton = By.xpath("//button[normalize-space()='Next']");

    public DestinationStepPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT));
    }

    public DestinationStepPage clickContainer() {
        wait.until(ExpectedConditions.elementToBeClickable(containerSelector));
        driver.findElement(containerSelector).click();
        return this;
    }

    public DestinationStepPage selectContainerItem() {
        wait.until(ExpectedConditions.elementToBeClickable(containerItem));
        driver.findElement(containerItem).click();
        return this;
    }

    public DestinationStepPage clickTargetStorage() {
        wait.until(ExpectedConditions.elementToBeClickable(targetStorageSelector));
        driver.findElement(targetStorageSelector).click();
        return this;
    }

    public DestinationStepPage selectTargetStorageItem() {
        wait.until(ExpectedConditions.elementToBeClickable(targetStorageItem));
        driver.findElement(targetStorageItem).click();
        return this;
    }

    public DestinationStepPage clickPool() {
        wait.until(ExpectedConditions.elementToBeClickable(poolSelector));
        driver.findElement(poolSelector).click();
        return this;
    }

    public DestinationStepPage selectPoolItem() {
        wait.until(ExpectedConditions.elementToBeClickable(poolItem));
        driver.findElement(poolItem).click();
        return this;
    }

    public void clickNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        driver.findElement(nextButton).click();
    }

    public void performDestinationStep() {
        clickContainer();
        selectContainerItem();
        clickTargetStorage();
        selectTargetStorageItem();
        clickPool();
        selectPoolItem();
        clickNextButton();
    }
}