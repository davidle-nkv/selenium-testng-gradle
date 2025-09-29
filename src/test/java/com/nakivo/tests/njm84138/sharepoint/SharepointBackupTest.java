package com.nakivo.tests.njm84138.sharepoint;

import com.nakivo.listeners.LogListener;
import com.nakivo.listeners.ScreenshotListener;
import com.nakivo.listeners.VideoListenerWithUI;
import com.nakivo.pages.njm84138.sharepoint.SharepointBackupPage;
import com.nakivo.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ VideoListenerWithUI.class, LogListener.class, ScreenshotListener.class })
public class SharepointBackupTest extends BaseTest {

    @Test(description = "Verify error302 is displayed for Sharepoint backup with malware files", groups = { "njm84138", "njm84138.error302Display" })
    public void testError302DisplayedForMalwareBackup() {
        SharepointBackupPage sharepointPage = new SharepointBackupPage(driver);
        
        // Navigate to job dashboard
        driver.get(baseUrl + "/jobs/dashboard");
        
        // Wait for job completion
        sharepointPage.waitForJobCompletion();
        
        // Verify Job Dashboard is visible
        Assert.assertTrue(sharepointPage.isJobDashboardVisible(), 
            "Job Dashboard should be visible after navigation");
        
        // Verify error302 is displayed on Job Dashboard
        Assert.assertTrue(sharepointPage.isError302Displayed(), 
            "Error302 should be displayed on Job Dashboard when backup job with malware files is completed");
    }

    @Test(description = "Verify View details functionality for error302", groups = { "njm84138", "njm84138.viewDetails" })
    public void testViewDetailsForError302() {
        SharepointBackupPage sharepointPage = new SharepointBackupPage(driver);
        
        // Navigate to job dashboard
        driver.get(baseUrl + "/jobs/dashboard");
        
        // Wait for job completion
        sharepointPage.waitForJobCompletion();
        
        // Verify error302 is displayed
        Assert.assertTrue(sharepointPage.isError302Displayed(), 
            "Error302 should be displayed before clicking View details");
        
        // Click on View details
        sharepointPage.clickViewDetails();
        
        // Verify report is displayed
        Assert.assertTrue(sharepointPage.isReportDisplayed(), 
            "Report should be displayed after clicking View details");
    }

    @Test(description = "Verify report format contains correct headers", groups = { "njm84138", "njm84138.reportFormat" })
    public void testReportFormatHeaders() {
        SharepointBackupPage sharepointPage = new SharepointBackupPage(driver);
        
        // Navigate to job dashboard
        driver.get(baseUrl + "/jobs/dashboard");
        
        // Wait for job completion and click View details
        sharepointPage.waitForJobCompletion()
                      .clickViewDetails();
        
        // Verify report headers match the expected format
        Assert.assertTrue(sharepointPage.verifyReportHeaders(), 
            "Report should contain headers: Source object, Internal ID, Start date & time, Location, System error code, Description, Action needed");
    }

    @Test(description = "Verify report can be downloaded", groups = { "njm84138", "njm84138.downloadReport" })
    public void testDownloadReport() {
        SharepointBackupPage sharepointPage = new SharepointBackupPage(driver);
        
        // Navigate to job dashboard
        driver.get(baseUrl + "/jobs/dashboard");
        
        // Wait for job completion and click View details
        sharepointPage.waitForJobCompletion()
                      .clickViewDetails();
        
        // Verify download button is available
        Assert.assertTrue(sharepointPage.isDownloadButtonAvailable(), 
            "Download report button should be available");
        
        // Click download report
        sharepointPage.clickDownloadReport();
        
        // Note: Actual file download verification would require additional utilities
        // This test verifies the download action can be triggered
    }
}