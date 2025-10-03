package com.nakivo.tests.njm147866;

import com.nakivo.pages.LoginPage;
import com.nakivo.pages.GoToWizardPage;
import com.nakivo.pages.SourceStepPage;
import com.nakivo.pages.DestinationStepPage;
import com.nakivo.pages.NetworksStepPage;
import com.nakivo.pages.ReIPStepPage;
import com.nakivo.pages.ScheduleStepPage;
import com.nakivo.pages.RetentionStepPage;
import com.nakivo.pages.OptionsStepPage;
import com.nakivo.tests.base.BaseTest;
import com.nakivo.listeners.VideoListenerWithUI;
import com.nakivo.listeners.LogListener;
import com.nakivo.listeners.ScreenshotListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ VideoListenerWithUI.class, LogListener.class, ScreenshotListener.class })
public class CreateProxmoxReplicationJobTest extends BaseTest {

    @Test(description = "Create Proxmox Replication Job", groups = { "njm147866", "njm147866.proxmox_replication" })
    public void createProxmoxReplicationJob() {
        LoginPage loginPage = new LoginPage(driver);
        GoToWizardPage goToWizardPage = new GoToWizardPage(driver);
        SourceStepPage sourceStepPage = new SourceStepPage(driver);
        DestinationStepPage destinationStepPage = new DestinationStepPage(driver);
        NetworksStepPage networksStepPage = new NetworksStepPage(driver);
        ReIPStepPage reIPStepPage = new ReIPStepPage(driver);
        ScheduleStepPage scheduleStepPage = new ScheduleStepPage(driver);
        RetentionStepPage retentionStepPage = new RetentionStepPage(driver);
        OptionsStepPage optionsStepPage = new OptionsStepPage(driver);

        // Login Page
        loginPage.openLoginPage("https://10.8.80.19:4443/c/login");
        loginPage.enterUsername("user");
        loginPage.enterPassword("user");
        loginPage.clickLoginButton();

        // Create Job Pop
        goToWizardPage.clickDataProtection();
        goToWizardPage.clickCreateJob();
        goToWizardPage.clickReplicationForProxmox();

        // Source Step
        sourceStepPage.selectItem();
        sourceStepPage.clickNextButton();

        // Destination Step
        destinationStepPage.clickContainer();
        destinationStepPage.selectContainerItem();
        destinationStepPage.clickTargetStorage();
        destinationStepPage.selectTargetStorageItem();
        destinationStepPage.clickPool();
        destinationStepPage.selectPoolItem();
        destinationStepPage.clickNextButton();

        // Networks Step
        networksStepPage.clickNextButton();

        // Re-IP Step
        reIPStepPage.clickNextButton();

        // Schedule Step
        scheduleStepPage.clickDoNotSchedule();
        scheduleStepPage.clickNextButton();

        // Retention Step
        retentionStepPage.clickNextButton();

        // Options Step
        optionsStepPage.clickFinishButton();
    }
}