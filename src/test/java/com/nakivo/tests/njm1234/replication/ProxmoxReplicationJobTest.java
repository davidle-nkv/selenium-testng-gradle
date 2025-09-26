package com.nakivo.tests.njm1234.replication;

import com.nakivo.listeners.LogListener;
import com.nakivo.listeners.ScreenshotListener;
import com.nakivo.listeners.VideoListenerWithUI;
import com.nakivo.pages.njm1234.replication.ProxmoxReplicationJobPage;
import com.nakivo.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.util.List;

@Listeners({ VideoListenerWithUI.class, LogListener.class, ScreenshotListener.class })
public class ProxmoxReplicationJobTest extends BaseTest {
    
    @Test(description = "Verify Proxmox VE Replication Job Change Tracking Options", groups = { "njm1234", "njm1234.verifyChangeTrackingOptions" })
    public void verifyProxmoxReplicationChangeTrackingOptions() {
        // Step 1: Login to NBR director
        driver.get("https://10.10.17.75:4443/c/login");
        
        // Assuming login is handled by BaseTest or we need to perform it here
        // For now, we'll assume we're already logged in after navigating to overview
        driver.get("https://10.10.17.75:4443/c/overview");
        
        ProxmoxReplicationJobPage replicationPage = new ProxmoxReplicationJobPage(driver);
        
        // Step 2: Navigate to Job Dashboard and start creating new replication job
        replicationPage.navigateToJobDashboard()
                      .clickAddJobButton()
                      .selectReplicationForProxmoxVE();
        
        // Verify wizard is displayed
        Assert.assertTrue(replicationPage.isWizardDisplayed(), 
            "New Replication Job Wizard for Proxmox VE should be displayed");
        
        // Step 3: Select VM and navigate to last step
        replicationPage.selectFirstVM()
                      .enterJobName("Test Replication Job")
                      .navigateToLastStep();
        
        // Verify Change tracking dropdown is visible
        Assert.assertTrue(replicationPage.isChangeTrackingDropdownVisible(), 
            "Change tracking dropdown should be visible in the last step");
        
        // Verify default selected option is "Use Proxmox VE CBT"
        Assert.assertTrue(replicationPage.isProxmoxCBTSelectedByDefault(), 
            "'Use Proxmox VE CBT' should be selected by default");
        
        // Open dropdown and verify all options
        replicationPage.openChangeTrackingDropdown();
        List<String> options = replicationPage.getChangeTrackingOptions();
        
        // Verify all three options are present
        Assert.assertTrue(options.stream().anyMatch(opt -> opt.contains("Use Proxmox VE CBT")), 
            "'Use Proxmox VE CBT' option should be present in dropdown");
        
        Assert.assertTrue(options.stream().anyMatch(opt -> opt.contains("Use proprietary method")), 
            "'Use proprietary method' option should be present in dropdown");
        
        Assert.assertTrue(options.stream().anyMatch(opt -> opt.contains("No change tracking") && opt.contains("always full")), 
            "'No change tracking (always full)' option should be present in dropdown");
        
        // Verify exactly 3 options are present
        Assert.assertEquals(options.size(), 3, 
            "Change tracking dropdown should contain exactly 3 options");
    }
}