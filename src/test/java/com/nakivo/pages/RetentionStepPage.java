package com.nakivo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class RetentionStepPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final int TIMEOUT = 15;
    
    private By nextButton = By.xpath("//button[normalize-space()='Next']");

    public RetentionStepPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    public void clickNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        driver.findElement(nextButton).click();
    }

    public void performRetentionStep() {
        clickNextButton();
    }
}