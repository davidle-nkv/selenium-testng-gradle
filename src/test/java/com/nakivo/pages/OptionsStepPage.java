package com.nakivo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class OptionsStepPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final int TIMEOUT = 15;
    
    private By finishButton = By.xpath("//button[normalize-space()='Finish']");
    
    public OptionsStepPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }
    
    public void clickFinishButton() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton));
        driver.findElement(finishButton).click();
    }
    
    public void performOptionsStep() {
        clickFinishButton();
    }
}