package com.nakivo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReIPStepPage {
    private WebDriver driver;
    private By nextButton = By.xpath("//button[normalize-space()='Next']");

    public ReIPStepPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickNext() {
        driver.findElement(nextButton).click();
    }

    public void performReIPStep() {
        clickNext();
    }
}