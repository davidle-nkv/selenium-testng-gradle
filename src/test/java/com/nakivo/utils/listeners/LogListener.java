package com.nakivo.utils.listeners;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import com.nakivo.utils.Utils;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class LogListener implements ITestListener {
    private PrintWriter writer;

    @Override
    public void onTestStart(ITestResult result) {
        try {
            // Ensure logs folder exists
            File logDir = new File("logs");
            if (!logDir.exists()) {
                logDir.mkdirs();
            }
            String logFile = "logs/" + Utils.generateFileName(result.getName()) + ".log";
            writer = new PrintWriter(new FileWriter(logFile, true));
            writer.println("=== Test Started: " + result.getName() + " ===");
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        writer.println("Test PASSED: " + result.getName());
        closeWriter();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        writer.println("Test FAILED: " + result.getName());
        if (result.getThrowable() != null) {
            result.getThrowable().printStackTrace(writer);
        }
        closeWriter();
    }

    private void closeWriter() {
        if (writer != null) {
            writer.flush();
            writer.close();
        }
    }
}

