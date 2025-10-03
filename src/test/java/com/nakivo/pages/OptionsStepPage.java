package com.nakivo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OptionsStepPage {
    private WebDriver driver;
    private By finishButton = By.xpath("//button[normalize-space()='Finish']");

    public OptionsStepPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFinish() {
        driver.findElement(finishButton).click();
    }

    public void performOptionsStep() {
        clickFinish();
    }
}