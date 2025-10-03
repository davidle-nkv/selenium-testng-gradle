package com.nakivo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class SourceStepPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final int TIMEOUT = 15;
    
    private By itemCheckbox = By.xpath("//div[contains(@class,'x-grid-cell-inner')][contains(text(),'cong-empty-vm-06')]/input[@class='x-tree-checkbox']");
    private By nextButton = By.xpath("//button[normalize-space()='Next']");
    
    public SourceStepPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }
    
    public void selectItem() {
        wait.until(ExpectedConditions.elementToBeClickable(itemCheckbox));
        driver.findElement(itemCheckbox).click();
    }
    
    public void clickNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        driver.findElement(nextButton).click();
    }
    
    public void performSourceStep() {
        selectItem();
        clickNextButton();
    }
}