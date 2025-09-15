package com.nakivo.tests.login_bk;

import com.nakivo.pages.login_bk.LoginPage_bk;
import com.nakivo.listeners.LogListener;
import com.nakivo.listeners.ScreenshotListener;
import com.nakivo.listeners.VideoListenerWithUI;
import com.nakivo.pages.dashboard_bk.DashboardPage_bk;
import com.nakivo.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Description.
 *
 * Author: David Le
 * Date: 9/12/2025
 * Time: 4:16 PM
 */
//@Test(groups = {"login_bk"})
//@Listeners({ VideoListenerWithUI.class, LogListener.class, ScreenshotListener.class })
public class LoginTest_bk extends BaseTest {
    private final String LOGIN_URL = "https://10.8.80.19:4443/c/login";

//    @Test(description = "Test Case 1: Successful login")
    public void testSuccessfulLogin() {
        System.out.println("Starting test: Successful login");
        LoginPage_bk loginPage = new LoginPage_bk(driver);
        DashboardPage_bk dashboardPage = new DashboardPage_bk(driver);

        // Step 1: Open the login page
        loginPage.navigateToLoginPage(LOGIN_URL);

        // Step 2: Enter username
        loginPage.enterUsername("user");

        // Step 3: Enter password
        loginPage.enterPassword("user");

        // Step 4: Click the Log In button
        loginPage.clickLoginButton();

        // Step 5: Verify user is redirected to dashboard
        Assert.assertTrue(dashboardPage.isUserOnDashboard(),
            "User should be redirected to dashboard after successful login");

        System.out.println("Completed test: Successful login");
    }

//    @Test(description = "Test Case 2: Unsuccessful login with invalid credentials")
    public void testUnsuccessfulLoginInvalidPassword() {
        System.out.println("Starting test: Unsuccessful login with invalid credentials");

        LoginPage_bk loginPage = new LoginPage_bk(driver);

        // Step 1: Open the login page
        loginPage.navigateToLoginPage(LOGIN_URL);

        // Step 2: Enter wrong username
        loginPage.enterUsername("wronguser");

        // Step 3: Enter wrong password
        loginPage.enterPassword("wrongpassword");

        // Step 4: Click the Log In button
        loginPage.clickLoginButton();

        // Step 5: Verify error message is displayed
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
            "Error message should be displayed for invalid credentials");

        String errorText = loginPage.getErrorMessageText();
        Assert.assertTrue(errorText.contains("Incorrect credentials"),
            "Error message should contain 'Invalid credentials'");

        System.out.println("Completed test: Unsuccessful login with invalid credentials");
    }

}
