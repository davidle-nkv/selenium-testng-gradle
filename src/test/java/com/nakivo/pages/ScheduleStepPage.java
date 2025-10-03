package com.nakivo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ScheduleStepPage {
    private WebDriver driver;
    private By doNotScheduleCheckbox = By.xpath("//div[contains(@class,'manual-schedule') and .//label[normalize-space()='Do not schedule, run on demand']]//input[contains(@class,'x-form-checkbox') or @role='checkbox']");
    private By nextButton = By.xpath("//button[normalize-space()='Next']");

    public ScheduleStepPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickDoNotSchedule() {
        driver.findElement(doNotScheduleCheckbox).click();
    }

    public void clickNext() {
        driver.findElement(nextButton).click();
    }

    public void performScheduleStep() {
        clickDoNotSchedule();
        clickNext();
    }
}