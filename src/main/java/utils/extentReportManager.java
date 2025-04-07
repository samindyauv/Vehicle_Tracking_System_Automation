package utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class extentReportManager {
    private static ExtentReports extent;
    private static final Map<String, ExtentTest> parentTests = new HashMap<>();
    private static ExtentTest currentTest;
    private static final String REPORT_PATH = "test-output/ExtentReport.html";
    private static final String SCREENSHOT_PATH = "test-output/screenshots/";
    private static LocalDateTime executionStartTime;
    private static LocalDateTime executionEndTime;
    private static String browserName;

    // Initialize Extent Report
    public static void initReport() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_PATH);
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().getCss();
            sparkReporter.config().setReportName("GCEC-GIT GlobaliTrack >> Fleet Management System");
            sparkReporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // Capture Execution Start Time
            executionStartTime = LocalDateTime.now();

            // System Info
            extent.setSystemInfo("Execution Start Time", executionStartTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            extent.setSystemInfo("Tester", "Samindya Vass");
            extent.setSystemInfo("Environment", "Staging");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
//            extent.setSystemInfo("Browser", System.getProperty("browser"));
        }
    }

    // Start a new test under a category
    public static void startTest(String category, String testName) {
        if (extent == null) {
            throw new IllegalStateException("ExtentReports is not initialized. Call initReport() first.");
        }

        // Create parent test if not exists
        ExtentTest parentTest = parentTests.computeIfAbsent(category, k -> extent.createTest(category));

        // Create child test inside the category
        currentTest = parentTest.createNode(testName);
    }

    // Log Info
    public static void testSteps(String message) {
        if (currentTest != null) {
            currentTest.info(message);
        }
    }

    // Log Pass
    public static void logPass(String message) {
        if (currentTest != null) {
            currentTest.pass(message);
        }
    }

    // Log Fail
    public static void logFail(String message) {
        if (currentTest != null) {
            currentTest.fail(message);
        }
    }

    // Capture Screenshot for Failed Tests
    public static void captureScreenshot(WebDriver driver, ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String screenshotPath = SCREENSHOT_PATH + result.getName() + ".png";
                FileUtils.copyFile(screenshot, new File(screenshotPath));

                currentTest.fail("🐞 <b><font color='grey'>Bug Screenshot</font></b>", MediaEntityBuilder.createScreenCaptureFromPath("./screenshots/" + result.getName() + ".png").build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Finalize and Open Report
    public static void flushReport() {
        if (extent != null) {
            executionEndTime = LocalDateTime.now();
            Duration executionTime = Duration.between(executionStartTime, executionEndTime);
            String formattedExecutionTime = String.format("%02d min, %02d sec",
                    executionTime.toMinutes(),
                    executionTime.toSecondsPart());

            extent.setSystemInfo("Execution End Time", executionEndTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            extent.setSystemInfo("Total Execution Time", formattedExecutionTime);
            extent.flush();
            System.out.println("✅ Extent Report generated successfully at: " + REPORT_PATH);
        }
    }

    // Open Extent Report Automatically After Tests
    public static void openReport() {
        try {
            File reportFile = new File(REPORT_PATH);
            if (reportFile.exists()) {
                Desktop.getDesktop().browse(reportFile.toURI());
                System.out.println("✅ Extent Report opened successfully!");
            } else {
                System.out.println("❌ Extent Report file not found. Ensure tests are running correctly.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}