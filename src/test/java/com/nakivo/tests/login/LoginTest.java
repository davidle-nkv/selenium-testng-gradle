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
        
        driver.get("https://10.8.80.19:4443/c/login");
        
        loginPage.enterUsername("user")
                 .enterPassword("user")
                 .clickLoginButton();
        
        boolean isRedirected = loginPage.waitForUrlContains("/configuration");
        Assert.assertTrue(isRedirected, "User should be redirected to configuration page after successful login");
    }
    
    @Test(description = "Test Case 2: Unsuccessful login with invalid password")
    public void testUnsuccessfulLoginInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);
        
        driver.get("https://10.8.80.19:4443/c/login");
        
        loginPage.enterUsername("wronguser")
                 .enterPassword("wrongpassword")
                 .clickLoginButton();
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for invalid credentials");
        Assert.assertEquals(loginPage.getErrorMessageText(), "Incorrect credentials.", "Error message text should match expected");
    }
}