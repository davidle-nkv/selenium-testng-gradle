package com.nakivo.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Description.
 *
 * Author: David Le
 * Date: 9/12/2025
 * Time: 12:32 PM
 */
public class Utils {
    private static String jiraKey = System.getenv("JIRA_ISSUE_KEY");   // from TeamCity param
//    private static String jiraKey = "NJM-145895";
    private static String buildNumber = System.getenv("BUILD_NUMBER"); // from TeamCity

    public static String generateFileName(String fileName) {
        return fileName + "_" + jiraKey + "_" + buildNumber;
    }

}
