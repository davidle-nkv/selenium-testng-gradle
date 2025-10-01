package com.nakivo.tests.njm124958.replication;

import com.nakivo.listeners.LogListener;
import com.nakivo.listeners.ScreenshotListener;
import com.nakivo.listeners.VideoListenerWithUI;
import com.nakivo.pages.njm124958.replication.ProxmoxReplicationJobWizardPage;
import com.nakivo.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.util.List;

@Listeners({ VideoListenerWithUI.class, LogListener.class, ScreenshotListener.class })
public class ProxmoxReplicationJobWizardTest extends BaseTest {
    
    @Test(description = "Verify Change Tracking dropdown options in Proxmox VE Replication Job Wizard", groups = { "njm124958", "njm124958.changeTrackingDropdown" })
    public void testChangeTrackingDropdownOptions() {
        // Step 1: Login and navigate to overview
        driver.get("https://10.10.17.75:4443/c/login");
        
        // Assuming login is handled by BaseTest or we need to perform login
        // If login is needed, create a separate LoginPage in the same issue package
        driver.findElement(org.openqa.selenium.By.id("username")).sendKeys("admin");
        driver.findElement(org.openqa.selenium.By.id("password")).sendKeys("1");
        driver.findElement(org.openqa.selenium.By.xpath("//button[@type='submit']")).click();
        
        // Wait for overview page
        Assert.assertTrue(driver.getCurrentUrl().contains("/c/overview"), 
            "User should be redirected to overview page after login");
        
        // Step 2: Navigate to Job Dashboard and create new replication job
        driver.get("https://10.10.17.75:4443/c/jobs");
        
        ProxmoxReplicationJobWizardPage wizardPage = new ProxmoxReplicationJobWizardPage(driver);
        
        wizardPage.clickAddJobButton()
                  .selectReplicationForProxmox();
        
        // Verify wizard is displayed
        Assert.assertTrue(wizardPage.isWizardDisplayed(), 
            "New Replication Job Wizard for Proxmox VE should be displayed");
        
        // Step 3: Fill wizard and navigate to last step
        wizardPage.selectFirstVM()
                  .enterJobName("Test_Replication_Job_" + System.currentTimeMillis())
                  .navigateToLastStep();
        
        // Verify Change tracking dropdown is visible
        Assert.assertTrue(wizardPage.isChangeTrackingDropdownVisible(), 
            "Change tracking dropdown should be visible in the last step");
        
        // Verify default selection
        Assert.assertTrue(wizardPage.isUseProxmoxCBTSelectedByDefault(), 
            "'Use Proxmox VE CBT' should be selected by default");
        
        // Verify all options are present
        List<String> options = wizardPage.getChangeTrackingOptions();
        
        Assert.assertTrue(options.size() == 3, 
            "Change tracking dropdown should have exactly 3 options");
        
        Assert.assertTrue(options.stream().anyMatch(opt -> opt.contains("Use Proxmox VE CBT")), 
            "'Use Proxmox VE CBT' option should be present");
        
        Assert.assertTrue(options.stream().anyMatch(opt -> opt.contains("Use proprietary method")), 
            "'Use proprietary method' option should be present");
        
        Assert.assertTrue(options.stream().anyMatch(opt -> opt.contains("No change tracking (always full)")), 
            "'No change tracking (always full)' option should be present");
    }
}