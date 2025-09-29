package com.nakivo.pages.njm84138.sharepoint;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SharepointBackupPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final int WAIT_TIMEOUT = 15;

    // Locators
    private final By jobDashboardSection = By.xpath("//div[contains(@class, 'job-dashboard')]");
    private final By error302Element = By.xpath("//div[contains(@class, 'error') and contains(text(), 'error302')]");
    private final By viewDetailsButton = By.xpath("//button[contains(text(), 'View details')] | //a[contains(text(), 'View details')]");
    private final By jobStatusCompleted = By.xpath("//span[contains(@class, 'status') and contains(text(), 'Completed')]");
    private final By reportTable = By.xpath("//table[contains(@class, 'report-table')]");
    private final By reportHeaders = By.xpath("//table//th");
    private final By downloadReportButton = By.xpath("//button[contains(text(), 'Download')] | //a[contains(@download, 'report')]");

    public SharepointBackupPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT));
    }

    public SharepointBackupPage waitForJobCompletion() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(jobStatusCompleted));
        return this;
    }

    public boolean isError302Displayed() {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(error302Element));
            return error.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public SharepointBackupPage clickViewDetails() {
        WebElement viewDetails = wait.until(ExpectedConditions.elementToBeClickable(viewDetailsButton));
        viewDetails.click();
        return this;
    }

    public boolean isReportDisplayed() {
        try {
            WebElement report = wait.until(ExpectedConditions.visibilityOfElementLocated(reportTable));
            return report.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyReportHeaders() {
        try {
            List<WebElement> headers = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(reportHeaders));
            String[] expectedHeaders = {
                "Source object",
                "Internal ID",
                "Start date & time",
                "Location",
                "System error code",
                "Description",
                "Action needed"
            };
            
            if (headers.size() != expectedHeaders.length) {
                return false;
            }
            
            for (int i = 0; i < expectedHeaders.length; i++) {
                if (!headers.get(i).getText().contains(expectedHeaders[i])) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public SharepointBackupPage clickDownloadReport() {
        WebElement downloadButton = wait.until(ExpectedConditions.elementToBeClickable(downloadReportButton));
        downloadButton.click();
        return this;
    }

    public boolean isDownloadButtonAvailable() {
        try {
            WebElement downloadButton = wait.until(ExpectedConditions.presenceOfElementLocated(downloadReportButton));
            return downloadButton.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isJobDashboardVisible() {
        try {
            WebElement dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(jobDashboardSection));
            return dashboard.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}