package com.nakivo.tests.njm145895.replication;

import com.nakivo.listeners.LogListener;
import com.nakivo.listeners.ScreenshotListener;
import com.nakivo.listeners.VideoListenerWithUI;
import com.nakivo.pages.njm145895.replication.ProxmoxReplicationPage;
import com.nakivo.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ VideoListenerWithUI.class, LogListener.class, ScreenshotListener.class })
public class ProxmoxReplicationTest extends BaseTest {

    @Test(description = "Create Proxmox Replication Job", groups = { "njm145895", "njm145895.createProxmoxReplicationJob" })
    public void testCreateProxmoxReplicationJob() {
        ProxmoxReplicationPage page = new ProxmoxReplicationPage(driver);

        // Login
        driver.get("https://10.8.80.19:4443/c/login");
        page.enterUsername("user")
            .enterPassword("user")
            .clickLogin();

        Assert.assertTrue(page.isLoggedIn(), "User should be logged in successfully");

        // Go to Wizard
        page.clickDataProtection()
            .clickCreateJob()
            .clickReplicationForProxmox();

        Assert.assertTrue(page.isWizardOpened(), "Replication wizard should be opened");

        // Source Step
        page.selectSourceItem()
            .clickNext();

        // Destination Step
        page.clickContainerSelector()
            .selectContainer()
            .clickTargetStorageSelector()
            .selectTargetStorage()
            .clickPoolSelector()
            .selectPool()
            .clickNext();

        // Networks Step
        page.clickNext();

        // Re-IP Step
        page.clickNext();

        // Schedule Step
        page.clickDoNotSchedule()
            .clickNext();

        // Retention Step
        page.clickNext();

        // Options Step
        page.clickFinish();

        Assert.assertTrue(page.isJobCreated(), "Replication job should be created successfully");
    }
}