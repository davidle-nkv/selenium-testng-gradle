package com.nakivo.tests.login;

import com.nakivo.listeners.VideoListenerWithUI;
import com.nakivo.listeners.LogListener;
import com.nakivo.listeners.ScreenshotListener;
import com.nakivo.pages.login.LoginPage;
import com.nakivo.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ VideoListenerWithUI.class, LogListener.class, ScreenshotListener.class })
public class LoginTest extends BaseTest {
    
    @Test(description = "Test Case 1: Successful login")
    public void testSuccessfulLogin() {
        // Open the login page
        driver.get("https://10.8.80.19:4443/c/login");
        
        // Initialize Page Object
        LoginPage loginPage = new LoginPage(driver);
        
        // Enter username
        loginPage.enterUsername("user");
        
        // Enter password
        loginPage.enterPassword("user");
        
        // Click the Log In button
        loginPage.clickLoginButton();
        
        // Verify user is redirected to configuration page
        boolean isRedirected = loginPage.waitForUrlContains("/configuration");
        Assert.assertTrue(isRedirected, "User should be redirected to configuration page after successful login");
    }
    
    @Test(description = "Test Case 2: Unsuccessful login (invalid password)")
    public void testUnsuccessfulLoginInvalidPassword() {
        // Open the login page
        driver.get("https://10.8.80.19:4443/c/login");
        
        // Initialize Page Object
        LoginPage loginPage = new LoginPage(driver);
        
        // Enter username
        loginPage.enterUsername("wronguser");
        
        // Enter password
        loginPage.enterPassword("wrongpassword");
        
        // Click the Log In button
        loginPage.clickLoginButton();
        
        // Verify that error message is displayed
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for invalid credentials");
        
        // Verify the error message text
        String actualErrorMessage = loginPage.getErrorMessageText();
        Assert.assertEquals(actualErrorMessage, "Incorrect credentials.", "Error message should be 'Incorrect credentials.'");
    }
}