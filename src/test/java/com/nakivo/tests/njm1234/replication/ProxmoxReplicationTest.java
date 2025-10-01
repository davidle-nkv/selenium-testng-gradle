package com.nakivo.tests.njm1234.replication;

import com.nakivo.listeners.LogListener;
import com.nakivo.listeners.ScreenshotListener;
import com.nakivo.listeners.VideoListenerWithUI;
import com.nakivo.pages.njm1234.replication.ProxmoxReplicationPage;
import com.nakivo.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ VideoListenerWithUI.class, LogListener.class, ScreenshotListener.class })
public class ProxmoxReplicationTest extends BaseTest {

    @Test(description = "Create Proxmox Replication Job", groups = { "njm1234", "njm1234.createProxmoxReplicationJob" })
    public void testCreateProxmoxReplicationJob() {
        ProxmoxReplicationPage proxmoxPage = new ProxmoxReplicationPage(driver);
        
        // Navigate to login page
        driver.get("https://10.8.80.19:4443/c/login");
        
        // Verify login page is loaded
        Assert.assertTrue(proxmoxPage.isLoginButtonVisible(), "Login button should be visible on login page");
        
        // Login with user credentials
        proxmoxPage.enterUsername("user")
                   .enterPassword("user")
                   .clickLogin();
        
        // Verify successful login and navigate to Data Protection
        Assert.assertTrue(proxmoxPage.isDataProtectionMenuVisible(), "Data Protection menu should be visible after login");
        proxmoxPage.clickDataProtection();
        
        // Create new replication job
        proxmoxPage.clickCreateJob()
                   .clickReplicationForProxmox();
        
        // Select VM item
        proxmoxPage.selectVMItem()
                   .clickNext();
        
        // Configure destination
        proxmoxPage.selectContainer()
                   .selectStorage()
                   .selectPool()
                   .clickNext();
        
        // Click through configuration steps
        proxmoxPage.clickNext()
                   .clickNext();
        
        // Configure schedule
        proxmoxPage.selectDoNotSchedule()
                   .clickNext();
        
        // Complete remaining steps
        proxmoxPage.clickNext()
                   .clickNext();
        
        // Verify Finish button is visible before clicking
        Assert.assertTrue(proxmoxPage.isFinishButtonVisible(), "Finish button should be visible on final step");
        
        // Complete the job creation
        proxmoxPage.clickFinish();
    }
}