package com.nakivo.tests.login;

import com.nakivo.pages.login.LoginPage;
import com.nakivo.tests.base.BaseTest;
import com.nakivo.listeners.VideoListenerWithUI;
import com.nakivo.listeners.LogListener;
import com.nakivo.listeners.ScreenshotListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ VideoListenerWithUI.class, LogListener.class, ScreenshotListener.class })
public class LoginTest extends BaseTest {
    
    @Test(description = "Test successful login with valid credentials", groups = { "login", "login.success" })
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.navigateToLoginPage("https://10.8.80.19:4443/c/login")
                .enterUsername("user")
                .enterPassword("user")
                .clickLoginButton();
        
        Assert.assertTrue(loginPage.isRedirectedToConfiguration(), 
                "User should be redirected to configuration page after successful login");
    }
    
    @Test(description = "Test unsuccessful login with invalid credentials", groups = { "login", "login.failure" })
    public void testUnsuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.navigateToLoginPage("https://10.8.80.19:4443/c/login")
                .enterUsername("wronguser")
                .enterPassword("wrongpassword")
                .clickLoginButton();
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed("Incorrect credentials."), 
                "Error message 'Incorrect credentials.' should be displayed for invalid login");
    }
}