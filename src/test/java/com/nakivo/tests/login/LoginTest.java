package com.nakivo.tests.login;

import com.nakivo.pages.login.LoginPage;
import com.nakivo.tests.base.BaseTest;
import com.nakivo.listeners.VideoListenerWithUI;
import com.nakivo.listeners.LogListener;
import com.nakivo.listeners.ScreenshotListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ VideoListenerWithUI.class, LogListener.class, ScreenshotListener.class })
public class LoginTest extends BaseTest {
    
    private LoginPage loginPage;
    private static final String LOGIN_URL = "https://10.8.80.19:4443/c/login";
    
    @BeforeMethod
    public void setUp() {
        loginPage = new LoginPage(driver);
    }
    
    @Test(groups = {"login"})
    public void testSuccessfulLogin() {
        loginPage.navigateToLoginPage(LOGIN_URL)
                 .enterUsername("user")
                 .enterPassword("user")
                 .clickLoginButton();
        
        Assert.assertTrue(loginPage.isRedirectedToConfiguration(), 
                         "User should be redirected to configuration page after successful login");
        
        Assert.assertTrue(loginPage.getCurrentUrl().contains("/configuration"),
                         "URL should contain '/configuration' after successful login");
    }
    
    @Test(groups = {"login"})
    public void testUnsuccessfulLoginInvalidCredentials() {
        loginPage.navigateToLoginPage(LOGIN_URL)
                 .enterUsername("wronguser")
                 .enterPassword("wrongpassword")
                 .clickLoginButton();
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed("Incorrect credentials."),
                         "Error message 'Incorrect credentials.' should be displayed for invalid login");
        
        Assert.assertTrue(loginPage.getCurrentUrl().contains("/login"),
                         "User should remain on login page after unsuccessful login attempt");
    }
}