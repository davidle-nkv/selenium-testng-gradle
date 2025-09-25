package com.nakivo.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By usernameField = By.cssSelector("input[placeholder='Username']");
    private By passwordField = By.cssSelector("input[placeholder='Password']");
    private By loginButton = By.xpath("//button[.//span[text()='Log In']]");
    private By errorMessage = By.xpath("//div[contains(@class,'notification-message-content') and text()='Incorrect credentials.']");
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public LoginPage enterUsername(String username) {
        WebElement usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        usernameElement.clear();
        usernameElement.sendKeys(username);
        return this;
    }
    
    public LoginPage enterPassword(String password) {
        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordElement.clear();
        passwordElement.sendKeys(password);
        return this;
    }
    
    public LoginPage clickLoginButton() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginBtn.click();
        return this;
    }
    
    public boolean isErrorMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getErrorMessageText() {
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return errorElement.getText();
    }
    
    public boolean waitForUrlContains(String urlFragment) {
        try {
            return wait.until(ExpectedConditions.urlContains(urlFragment));
        } catch (Exception e) {
            return false;
        }
    }
}