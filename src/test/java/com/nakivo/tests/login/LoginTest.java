package com.nakivo.tests.login;

import com.nakivo.listeners.LogListener;
import com.nakivo.listeners.ScreenshotListener;
import com.nakivo.listeners.VideoListenerWithUI;
import com.nakivo.pages.login.LoginPage;
import com.nakivo.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ VideoListenerWithUI.class, LogListener.class, ScreenshotListener.class })
public class LoginTest extends BaseTest {
    
    @Test(description = "Test Case 1: Successful login")
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        
        // Step 1: Open the login page
        driver.get("https://10.8.80.19:4443/c/login");
        
        // Step 2: Enter the username "user"
        loginPage.enterUsername("user");
        
        // Step 3: Enter the password "user"
        loginPage.enterPassword("user");
        
        // Step 4: Click the "Log In" button
        loginPage.clickLoginButton();
        
        // Step 5: Verify user is redirected to configuration page
        boolean isRedirected = loginPage.waitForUrlContains("/configuration");
        Assert.assertTrue(isRedirected, "User should be redirected to configuration page after successful login");
    }
    
    @Test(description = "Test Case 2: Unsuccessful login with invalid credentials")
    public void testUnsuccessfulLoginInvalidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        
        // Step 1: Open the login page
        driver.get("https://10.8.80.19:4443/c/login");
        
        // Step 2: Enter the username "wronguser"
        loginPage.enterUsername("wronguser");
        
        // Step 3: Enter the password "wrongpassword"
        loginPage.enterPassword("wrongpassword");
        
        // Step 4: Click the "Log In" button
        loginPage.clickLoginButton();
        
        // Step 5: Verify error message is displayed
        boolean isErrorDisplayed = loginPage.isErrorMessageDisplayed();
        Assert.assertTrue(isErrorDisplayed, "Error message should be displayed for invalid credentials");
        
        String errorText = loginPage.getErrorMessageText();
        Assert.assertEquals(errorText, "Incorrect credentials.", "Error message text should match expected value");
    }
}