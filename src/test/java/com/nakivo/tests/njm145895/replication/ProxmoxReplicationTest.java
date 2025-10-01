package com.nakivo.tests.njm145895.replication;

import com.nakivo.listeners.VideoListenerWithUI;
import com.nakivo.listeners.LogListener;
import com.nakivo.listeners.ScreenshotListener;
import com.nakivo.pages.njm145895.replication.ProxmoxReplicationPage;
import com.nakivo.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ VideoListenerWithUI.class, LogListener.class, ScreenshotListener.class })
public class ProxmoxReplicationTest extends BaseTest {

    @Test(description = "Create Proxmox Replication Job", groups = { "njm145895", "njm145895.createProxmoxReplicationJob" })
    public void testCreateProxmoxReplicationJob() {
        ProxmoxReplicationPage proxmoxReplicationPage = new ProxmoxReplicationPage(driver);
        
        driver.get("https://10.8.80.19:4443/c/login");
        
        proxmoxReplicationPage
            .enterUsername("user")
            .enterPassword("user")
            .clickLogin();
        
        Assert.assertTrue(proxmoxReplicationPage.isDataProtectionDisplayed(), "Failed to login - Data Protection button not displayed");
        
        proxmoxReplicationPage
            .clickDataProtection()
            .clickCreateJob()
            .clickReplicationForProxmox()
            .selectVmItem()
            .clickNext()
            .selectContainer()
            .selectStorage()
            .selectPool()
            .clickNext()
            .clickNext()
            .clickNext()
            .selectDoNotSchedule()
            .clickNext()
            .clickNext()
            .clickNext();
        
        Assert.assertTrue(proxmoxReplicationPage.isFinishButtonDisplayed(), "Finish button not displayed - Job configuration may have failed");
        
        proxmoxReplicationPage.clickFinish();
    }
}