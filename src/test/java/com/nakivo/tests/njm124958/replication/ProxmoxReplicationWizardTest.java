package com.nakivo.tests.njm124958.replication;

import com.nakivo.listeners.LogListener;
import com.nakivo.listeners.ScreenshotListener;
import com.nakivo.listeners.VideoListenerWithUI;
import com.nakivo.pages.njm124958.replication.ProxmoxReplicationWizardPage;
import com.nakivo.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ VideoListenerWithUI.class, LogListener.class, ScreenshotListener.class })
public class ProxmoxReplicationWizardTest extends BaseTest {

    @Test(description = "Verify Proxmox VE replication job wizard displays correct change tracking options", groups = { "njm124958", "njm124958.changeTrackingOptions" })
    public void testProxmoxReplicationChangeTrackingOptions() {
        // Step 1: Login to NBR director
        driver.get("https://10.10.17.75:4443/c/login");
        
        // Assuming login is handled by BaseTest or we need to add login steps here
        // For this test, we assume user is already logged in or login is handled in BaseTest
        // If not, login page object would need to be created separately
        
        // Navigate to overview page after login
        driver.get("https://10.10.17.75:4443/c/overview");
        
        ProxmoxReplicationWizardPage wizardPage = new ProxmoxReplicationWizardPage(driver);
        
        // Step 2: Navigate to Job Dashboard and start new replication job
        wizardPage.clickJobDashboard()
                  .clickAddJobButton()
                  .selectReplicationForProxmox();
        
        // Verify wizard is displayed
        Assert.assertTrue(wizardPage.isWizardDisplayed(), 
                "New Replication Job Wizard for Proxmox VE should be displayed");
        
        // Step 3: Select VM and navigate to last step
        wizardPage.selectFirstVM()
                  .navigateToLastStep();
        
        // Click on Change tracking dropdown
        wizardPage.clickChangeTrackingDropdown();
        
        // Verify all three options are displayed
        Assert.assertTrue(wizardPage.isProxmoxCBTOptionDisplayed(), 
                "'Use Proxmox VE CBT' option should be displayed in Change tracking dropdown");
        
        Assert.assertTrue(wizardPage.isProprietaryMethodOptionDisplayed(), 
                "'Use proprietary method' option should be displayed in Change tracking dropdown");
        
        Assert.assertTrue(wizardPage.isNoChangeTrackingOptionDisplayed(), 
                "'No change tracking (always full)' option should be displayed in Change tracking dropdown");
        
        // Verify 'Use Proxmox VE CBT' is selected by default
        Assert.assertTrue(wizardPage.isProxmoxCBTSelectedByDefault(), 
                "'Use Proxmox VE CBT' should be selected by default in Change tracking dropdown");
        
        // Additional verification: ensure exactly 3 options are present
        int optionsCount = wizardPage.getChangeTrackingOptionsCount();
        Assert.assertEquals(optionsCount, 3, 
                "Change tracking dropdown should contain exactly 3 options");
    }
}