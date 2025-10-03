package com.nakivo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginPage {
    private static final int WAIT_TIMEOUT = 15;
    private WebDriver driver;
    private WebDriverWait wait;
    
    private By usernameField = By.cssSelector("input[placeholder='Username']");
    private By passwordField = By.cssSelector("input[placeholder='Password']");
    private By loginButton = By.xpath("//button[.//span[text()='Log In']]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT));
    }

    public LoginPage openLoginPage(String url) {
        driver.get(url);
        return this;
    }

    public LoginPage enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        driver.findElement(usernameField).sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }

    public void performLogin(String url, String username, String password) {
        openLoginPage(url);
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}