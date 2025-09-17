package com.nakivo.listeners;

import com.nakivo.tests.base.BaseTest;
import com.nakivo.utils.Utils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = ((BaseTest) result.getInstance()).getDriver();
        captureScreenshot(driver, result.getName() + "_FAILED");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        WebDriver driver = ((BaseTest) result.getInstance()).getDriver();
        captureScreenshot(driver, result.getName() + "_PASSED");
    }

    @Override public void onTestStart(ITestResult result) {}
   
    @Override public void onTestSkipped(ITestResult result) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}

    /**
     * Captures screenshot and save to file.
     *
     * @param driver    WebDriver instance.
     * @param filename  Filename without extension.
     *
     */
    private void captureScreenshot(WebDriver driver, String filename) {
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File("build/reports/screenshots/" + Utils.generateFileName(filename) + ".png");
            destFile.getParentFile().mkdirs(); // make sure folder exists
            Files.copy(srcFile.toPath(), destFile.toPath());
            System.out.println("Saved screenshot: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
