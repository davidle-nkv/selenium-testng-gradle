package com.nakivo.pages.login_bk;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

/**
 * Description.
 *
 * Author: David Le
 * Date: 9/12/2025
 * Time: 4:01 PM
 */
public class LoginPage_bk {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By usernameField = By.cssSelector("input[placeholder='Username']");
    private By passwordField = By.cssSelector("input[placeholder='Password']");
    private By loginButton = By.xpath("//button[.//span[text()='Log In']]"); //By.xpath("//div[contains(text(),'Log In')]");
    private By errorMessage = By.xpath("//div[contains(@class,'notification-message-content') and text()='Incorrect credentials.']");

    public LoginPage_bk(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToLoginPage(String url) {
        driver.get(url);
        wait.until(ExpectedConditions.presenceOfElementLocated(usernameField));
    }

    public void enterUsername(String username) {
        try {
            WebElement usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
            usernameElement.clear();
            usernameElement.sendKeys(username);
        } catch (TimeoutException e) {
            Assert.fail("Username field was not found on the login page", e);
        }
    }

    public void enterPassword(String password) {
        try {
            WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
            passwordElement.clear();
            passwordElement.sendKeys(password);
        } catch (TimeoutException e) {
            Assert.fail("Password field was not found on the login page", e);
        }
    }

    public void clickLoginButton() {
        try {
            WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
            loginBtn.click();
        } catch (TimeoutException e) {
            Assert.fail("Login button was not found or not clickable on the login page", e);
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return error.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessageText() {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return error.getText();
        } catch (Exception e) {
            return "";
        }
    }

}
