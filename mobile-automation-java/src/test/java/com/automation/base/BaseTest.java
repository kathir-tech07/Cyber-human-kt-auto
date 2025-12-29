package com.automation.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

    public static AppiumDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;

    /* ================= REPORT SETUP ================= */

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("extent-report.html");
        spark.config().setReportName("Appium Login Automation Report");
        spark.config().setDocumentTitle("Test Results");

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("OS", "Android");
        extent.setSystemInfo("Tester", "Antigravity");
    }

    /* ================= DRIVER SETUP ================= */

    @BeforeMethod
    public void setup(java.lang.reflect.Method method) throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("1dc3d76f");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.houseofepigenetics.abchopra");
        options.setAppActivity(".MainActivity");
        options.setNoReset(true); // Keep login state

        driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"), options);

        driver.manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(6));

        // ✅ CONDITIONAL RESET: Skip resetAppToHomePage for tests with custom navigation
        // EditProfileTest: manages Sign In -> Home -> Edit Profile flow
        // SignInTest: needs to start from Sign In page (logged out state)
        // SignUpTest: needs to start from Sign Up page
        // ForgotPasswordTest: needs to start from Sign In page (for Forgot Password
        // button)
        String testClassName = method.getDeclaringClass().getSimpleName();
        if (!"EditProfileTest".equals(testClassName) &&
                !"SignInTest".equals(testClassName) &&
                !"SignUpTest".equals(testClassName) &&
                !"ForgotPasswordTest".equals(testClassName)) {
            // ✅ RESET APP STATE: Navigate to Home page before each test
            // This ensures test independence without requiring re-login
            resetAppToHomePage();
        } else {
            System.out.println("⏭ Skipping resetAppToHomePage for " + testClassName + " - uses custom navigation");
        }
    }

    /**
     * Reset app state by navigating to Home page
     * Uses Android system back to clear navigation stack
     * This ensures each test starts from a known state
     */
    private void resetAppToHomePage() {
        try {
            Thread.sleep(2000); // Wait for app to fully load

            int maxAttempts = 10;
            int attempts = 0;

            // Keep pressing back until we reach home page or max attempts
            while (attempts < maxAttempts) {
                try {
                    // Check if we're on home page by looking for DAILY PRIORITY heading
                    org.openqa.selenium.WebElement homeHeading = driver.findElement(
                            org.openqa.selenium.By.xpath("//android.view.View[@content-desc='DAILY PRIORITY']"));
                    if (homeHeading.isDisplayed()) {
                        System.out.println("✓ App state reset: Successfully navigated to Home page");
                        return; // Successfully reached home page
                    }
                } catch (Exception e) {
                    // Not on home page yet, continue
                }

                // Press Android system back
                driver.navigate().back();
                Thread.sleep(500);
                attempts++;
            }

            // If we couldn't reach home page, log warning but continue
            // (test might still work if it handles navigation properly)
            System.out.println("⚠ Warning: Could not navigate to Home page after " + maxAttempts + " attempts");

        } catch (Exception e) {
            // If reset fails, log warning but don't fail the test
            System.out.println("⚠ Warning: App state reset failed: " + e.getMessage());
        }
    }

    /* ================= CLEAN TEARDOWN ================= */

    @AfterMethod
    public void tearDown(ITestResult result) {

        // ❌ Screenshot + FAIL logging ONLY on real failure
        if (test != null && result.getStatus() == ITestResult.FAILURE) {

            test.log(Status.FAIL, "Test Failed");
            test.log(Status.FAIL, result.getThrowable());

            try {
                String screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BASE64);
                test.addScreenCaptureFromBase64String(screenshot);
            } catch (Exception ignored) {
            }
        }

        // App + Driver cleanup
        if (driver != null) {
            try {
                ((AndroidDriver) driver)
                        .terminateApp("com.houseofepigenetics.abchopra");
            } catch (Exception ignored) {
            }
            driver.quit();
        }

        // 🛑 User requested 2-second delay after every test
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* ================= REPORT FLUSH ================= */

    @AfterSuite
    public void tearDownReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
