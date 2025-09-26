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

    @Test(description = "Verify Change Tracking dropdown options in Proxmox VE Replication Job Wizard", groups = { "njm124958", "njm124958.changeTrackingDropdown" })
    public void testChangeTrackingDropdownOptions() {
        // Step 1: Login and navigate to overview page
        driver.get("https://10.10.17.75:4443/c/login");
        
        // Assuming login is handled by BaseTest or we need to perform login here
        // Since the test assumes we're already logged in and on overview page
        driver.get("https://10.10.17.75:4443/c/overview");
        
        ProxmoxReplicationWizardPage wizardPage = new ProxmoxReplicationWizardPage(driver);
        
        // Step 2: Navigate to Job Dashboard and create new replication job
        wizardPage.navigateToJobDashboard()
                  .clickAddJobButton()
                  .selectReplicationForProxmoxVE();
        
        // Verify wizard is displayed
        Assert.assertTrue(wizardPage.isWizardDisplayed(), 
                "New Replication Job Wizard for Proxmox VE should be displayed");
        
        // Step 3: Select VM and navigate to last step
        wizardPage.selectFirstVM()
                  .navigateToLastStep();
        
        // Verify Change tracking dropdown options
        Assert.assertTrue(wizardPage.verifyChangeTrackingDropdownOptions(),
                "Change tracking dropdown should contain all required options");
        
        // Verify 'Use Proxmox VE CBT' is displayed
        Assert.assertTrue(wizardPage.isProxmoxCbtOptionDisplayed(),
                "'Use Proxmox VE CBT' option should be displayed");
        
        // Verify 'Use Proxmox VE CBT' is selected by default
        Assert.assertTrue(wizardPage.isProxmoxCbtOptionSelected(),
                "'Use Proxmox VE CBT' option should be selected by default");
        
        // Verify 'Use proprietary method' is displayed
        Assert.assertTrue(wizardPage.isProprietaryMethodOptionDisplayed(),
                "'Use proprietary method' option should be displayed");
        
        // Verify 'No change tracking (always full)' is displayed
        Assert.assertTrue(wizardPage.isNoChangeTrackingOptionDisplayed(),
                "'No change tracking (always full)' option should be displayed");
    }
}