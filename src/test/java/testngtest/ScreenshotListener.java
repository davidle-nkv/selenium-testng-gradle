package testngtest;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class ScreenshotListener implements ITestListener {
    
    private void captureScreenshot(WebDriver driver, String fileName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            File targetFile = new File("screenshots/" + fileName + ".png");
            targetFile.getParentFile().mkdirs(); // ensure folder exists
            FileHandler.copy(srcFile, targetFile);
            System.out.println("Screenshot saved: " + targetFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = ((Test1) result.getInstance()).driver;
        captureScreenshot(driver, result.getName() + "_FAILED");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        WebDriver driver = ((Test1) result.getInstance()).driver;
        captureScreenshot(driver, result.getName() + "_PASSED");
    }

    // @Override
    //public void onTestFailure(ITestResult result) {
    //   Object testClass = result.getInstance();
    //    WebDriver driver = ((Test1) testClass).driver;

    //    File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    //    try {
    //        FileHandler.copy(srcFile, new File("screenshots/" + result.getName() + ".png"));
    //        System.out.println("Screenshot saved for failed test: " + result.getName());
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //    }
    //}

    @Override public void onTestStart(ITestResult result) {}
   
    @Override public void onTestSkipped(ITestResult result) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
}
