package com.nakivo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SourceStepPage {
    private WebDriver driver;
    private By itemCheckbox = By.xpath("//div[contains(@class,'x-grid-cell-inner')][contains(text(),'cong-empty-vm-06')]/input[@class='x-tree-checkbox']");
    private By nextButton = By.xpath("//button[normalize-space()='Next']");

    public SourceStepPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectItem() {
        driver.findElement(itemCheckbox).click();
    }

    public void clickNext() {
        driver.findElement(nextButton).click();
    }

    public void performSourceStep() {
        selectItem();
        clickNext();
    }
}