package com.nakivo.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.xpath("//button[contains(text(), 'Log In')]");
    private By errorMessage = By.xpath("//div[contains(@class,'notification-message-content') and text()='Incorrect credentials.']");
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public LoginPage enterUsername(String username) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(usernameField));
        element.clear();
        element.sendKeys(username);
        return this;
    }
    
    public LoginPage enterPassword(String password) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(passwordField));
        element.clear();
        element.sendKeys(password);
        return this;
    }
    
    public void clickLoginButton() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        element.click();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return error.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}