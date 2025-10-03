package com.nakivo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class DestinationStepPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final int TIMEOUT = 15;
    
    private By containerSelector = By.xpath("//div[contains(@class,'item-selector')]//div[contains(@class,'glT1') and contains(normalize-space(.),'Select target container')]");
    private By containerItem = By.xpath("//div[contains(@class,'x-grid-cell-inner') and normalize-space(text())='proxmox02']");
    private By targetStorageSelector = By.xpath("//div[contains(@class,'item-selector')]//div[contains(@class,'glT1') and contains(normalize-space(.),'Select target storage')]");
    private By targetStorageItem = By.xpath("//div[@class='stItemViewDiv1' and normalize-space(text())='DirectoryFS-Proxmox02']");
    private By poolSelector = By.xpath("//div[contains(@class,'item-selector')]//div[contains(@class,'glT1') and contains(normalize-space(.),'Select target pool (optional)')]");
    private By poolItem = By.xpath("//div[@class='inLineName ellipsis' and normalize-space(text())='khapool01']");
    private By nextButton = By.xpath("//button[normalize-space()='Next']");
    
    public DestinationStepPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }
    
    public void clickContainer() {
        wait.until(ExpectedConditions.elementToBeClickable(containerSelector));
        driver.findElement(containerSelector).click();
    }
    
    public void selectContainerItem() {
        wait.until(ExpectedConditions.elementToBeClickable(containerItem));
        driver.findElement(containerItem).click();
    }
    
    public void clickTargetStorage() {
        wait.until(ExpectedConditions.elementToBeClickable(targetStorageSelector));
        driver.findElement(targetStorageSelector).click();
    }
    
    public void selectTargetStorageItem() {
        wait.until(ExpectedConditions.elementToBeClickable(targetStorageItem));
        driver.findElement(targetStorageItem).click();
    }
    
    public void clickPool() {
        wait.until(ExpectedConditions.elementToBeClickable(poolSelector));
        driver.findElement(poolSelector).click();
    }
    
    public void selectPoolItem() {
        wait.until(ExpectedConditions.elementToBeClickable(poolItem));
        driver.findElement(poolItem).click();
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