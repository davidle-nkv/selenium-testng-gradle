package com.nakivo.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    
    // Locators
    private final By usernameField = By.cssSelector("input[placeholder='Username']");
    private final By passwordField = By.cssSelector("input[placeholder='Password']");
    private final By loginButton = By.xpath("//button[.//span[text()='Log In']]");
    private final By errorMessage = By.xpath("//div[contains(@class,'notification-message-content') and text()='Incorrect credentials.']");
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public LoginPage navigateTo(String url) {
        driver.get(url);
        return this;
    }
    
    public LoginPage enterUsername(String username) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        element.clear();
        element.sendKeys(username);
        return this;
    }
    
    public LoginPage enterPassword(String password) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        element.clear();
        element.sendKeys(password);
        return this;
    }
    
    public LoginPage clickLoginButton() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        element.click();
        return this;
    }
    
    public boolean waitForUrlContains(String urlPart) {
        try {
            return wait.until(ExpectedConditions.urlContains(urlPart));
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isErrorMessageDisplayed() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getErrorMessageText() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return element.getText();
        } catch (Exception e) {
            return "";
        }
    }
}