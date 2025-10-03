package com.nakivo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class ScheduleStepPage {
    private static final int WAIT_TIMEOUT = 15;
    private WebDriver driver;
    private WebDriverWait wait;
    
    private By doNotScheduleCheckbox = By.xpath("//div[contains(@class,'manual-schedule') and .//label[normalize-space()='Do not schedule, run on demand']]//input[contains(@class,'x-form-checkbox') or @role='checkbox']");
    private By nextButton = By.xpath("//button[normalize-space()='Next']");

    public ScheduleStepPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT));
    }

    public ScheduleStepPage clickDoNotSchedule() {
        wait.until(ExpectedConditions.elementToBeClickable(doNotScheduleCheckbox));
        driver.findElement(doNotScheduleCheckbox).click();
        return this;
    }

    public void clickNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        driver.findElement(nextButton).click();
    }

    public void performScheduleStep() {
        clickDoNotSchedule();
        clickNextButton();
    }
}