package com.nakivo.tests.njm124958.replication;

import com.nakivo.listeners.LogListener;
import com.nakivo.listeners.ScreenshotListener;
import com.nakivo.listeners.VideoListenerWithUI;
import com.nakivo.pages.njm124958.replication.ProxmoxReplicationWizardPage;
import com.nakivo.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.util.List;

@Listeners({ VideoListenerWithUI.class, LogListener.class, ScreenshotListener.class })
public class ProxmoxReplicationWizardTest extends BaseTest {

    @Test(description = "Verify Change Tracking dropdown options in Proxmox VE Replication Job Wizard", groups = { "njm124958", "njm124958.changeTrackingDropdown" })
    public void testChangeTrackingDropdownOptions() {
        // Step 1: Login and navigate to overview
        driver.get(baseUrl + "/c/login");
        // Assuming login is handled by BaseTest or we need to implement login
        // For this test, we assume user is already logged in or will be redirected after get
        
        // Navigate to overview page
        driver.get(baseUrl + "/c/overview");
        
        // Step 2: Navigate to Job Dashboard and create new replication job
        driver.get(baseUrl + "/c/jobs");
        
        ProxmoxReplicationWizardPage wizardPage = new ProxmoxReplicationWizardPage(driver);
        
        // Click + button and select Replication for Proxmox VE
        wizardPage.clickAddJobButton()
                  .selectReplicationForProxmox();
        
        // Verify wizard is displayed
        Assert.assertTrue(wizardPage.isWizardDisplayed(), 
                "New Replication Job Wizard for Proxmox VE should be displayed");
        
        // Step 3: Configure wizard and navigate to last step
        wizardPage.selectFirstVM()
                  .enterJobName("Test Replication Job " + System.currentTimeMillis())
                  .navigateToLastStep();
        
        // Verify Change tracking dropdown is visible
        Assert.assertTrue(wizardPage.isChangeTrackingDropdownVisible(), 
                "Change tracking dropdown should be visible on the last step");
        
        // Get all dropdown options
        List<String> options = wizardPage.getChangeTrackingOptions();
        
        // Verify all required options are present
        Assert.assertTrue(wizardPage.isChangeTrackingOptionPresent("Use Proxmox VE CBT"), 
                "'Use Proxmox VE CBT' option should be present in dropdown");
        
        Assert.assertTrue(wizardPage.isChangeTrackingOptionPresent("Use proprietary method"), 
                "'Use proprietary method' option should be present in dropdown");
        
        Assert.assertTrue(wizardPage.isChangeTrackingOptionPresent("No change tracking (always full)"), 
                "'No change tracking (always full)' option should be present in dropdown");
        
        // Verify default selection
        String selectedOption = wizardPage.getSelectedChangeTrackingOption();
        Assert.assertTrue(selectedOption.contains("Use Proxmox VE CBT"), 
                "'Use Proxmox VE CBT' should be selected by default");
        
        // Verify correct number of options
        Assert.assertEquals(options.size(), 3, 
                "Change tracking dropdown should contain exactly 3 options");
    }

    @Test(description = "Verify default selection of Change Tracking dropdown", groups = { "njm124958", "njm124958.defaultChangeTracking" })
    public void testDefaultChangeTrackingSelection() {
        // Navigate to Job Dashboard
        driver.get(baseUrl + "/c/jobs");
        
        ProxmoxReplicationWizardPage wizardPage = new ProxmoxReplicationWizardPage(driver);
        
        // Create new replication job
        wizardPage.clickAddJobButton()
                  .selectReplicationForProxmox();
        
        // Navigate to last step
        wizardPage.selectFirstVM()
                  .enterJobName("Test Default Selection " + System.currentTimeMillis())
                  .navigateToLastStep();
        
        // Verify default selection is 'Use Proxmox VE CBT'
        String defaultSelection = wizardPage.getSelectedChangeTrackingOption();
        Assert.assertEquals(defaultSelection, "Use Proxmox VE CBT", 
                "Default selection should be 'Use Proxmox VE CBT'");
    }
}