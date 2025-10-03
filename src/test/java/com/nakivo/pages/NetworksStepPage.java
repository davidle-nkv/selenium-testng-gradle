package com.nakivo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NetworksStepPage {
    private WebDriver driver;
    private By nextButton = By.xpath("//button[normalize-space()='Next']");

    public NetworksStepPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickNext() {
        driver.findElement(nextButton).click();
    }

    public void performNetworksStep() {
        clickNext();
    }
}