package com.nakivo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DestinationStepPage {
    private WebDriver driver;
    private By containerDropdown = By.xpath("//div[contains(@class,'item-selector')]//div[contains(@class,'glT1') and contains(normalize-space(.),'Select target container')]");
    private By containerItem = By.xpath("//div[contains(@class,'x-grid-cell-inner') and normalize-space(text())='proxmox02']");
    private By targetStorageDropdown = By.xpath("//div[contains(@class,'item-selector')]//div[contains(@class,'glT1') and contains(normalize-space(.),'Select target storage')]");
    private By targetStorageItem = By.xpath("//div[@class='stItemViewDiv1' and normalize-space(text())='DirectoryFS-Proxmox02']");
    private By poolDropdown = By.xpath("//div[contains(@class,'item-selector')]//div[contains(@class,'glT1') and contains(normalize-space(.),'Select target pool (optional)')]");
    private By poolItem = By.xpath("//div[@class='inLineName ellipsis' and normalize-space(text())='khapool01']");
    private By nextButton = By.xpath("//button[normalize-space()='Next']");

    public DestinationStepPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickContainer() {
        driver.findElement(containerDropdown).click();
    }

    public void selectContainerItem() {
        driver.findElement(containerItem).click();
    }

    public void clickTargetStorage() {
        driver.findElement(targetStorageDropdown).click();
    }

    public void selectTargetStorageItem() {
        driver.findElement(targetStorageItem).click();
    }

    public void clickPool() {
        driver.findElement(poolDropdown).click();
    }

    public void selectPoolItem() {
        driver.findElement(poolItem).click();
    }

    public void clickNext() {
        driver.findElement(nextButton).click();
    }

    public void performDestinationStep() {
        clickContainer();
        selectContainerItem();
        clickTargetStorage();
        selectTargetStorageItem();
        clickPool();
        selectPoolItem();
        clickNext();
    }
}