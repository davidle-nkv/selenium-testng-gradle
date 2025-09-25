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
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.navigateTo("https://10.8.80.19:4443/c/login")
                .enterUsername("user")
                .enterPassword("user")
                .clickLoginButton();
        
        boolean isRedirected = loginPage.waitForUrlContains("/configuration");
        Assert.assertTrue(isRedirected, "User should be redirected to configuration page after successful login");
    }
    
    @Test(description = "Test Case 2: Unsuccessful login (invalid password)")
    public void testUnsuccessfulLoginInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.navigateTo("https://10.8.80.19:4443/c/login")
                .enterUsername("wronguser")
                .enterPassword("wrongpassword")
                .clickLoginButton();
        
        boolean isErrorDisplayed = loginPage.isErrorMessageDisplayed();
        Assert.assertTrue(isErrorDisplayed, "Error message should be displayed for invalid credentials");
        
        String errorText = loginPage.getErrorMessageText();
        Assert.assertEquals(errorText, "Incorrect credentials.", "Error message text should be 'Incorrect credentials.'");
    }
}