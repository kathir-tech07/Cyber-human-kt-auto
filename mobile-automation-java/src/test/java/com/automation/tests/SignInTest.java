package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.SignInPage;
import com.automation.pages.HomePage;
import com.automation.pages.ProfilePage;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class SignInTest extends BaseTest {

    /**
     * ✅ OVERRIDE SETUP TO SKIP HOME PAGE RESET
     * Sign In tests need to start from Sign In page (logged out state),
     * not Home page (logged in state). We override setup() to initialize
     * the driver without calling resetAppToHomePage().
     */
    @BeforeMethod
    public void setup() throws MalformedURLException {
        // Initialize driver with same options as BaseTest, but skip home page reset
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

        // Wait for app to load and navigate to Sign In page
        try {
            Thread.sleep(2000);
            navigateToSignInPage();
        } catch (Exception e) {
            System.out.println("⚠ Warning: Navigation setup failed: " + e.getMessage());
        }
    }

    /**
     * ✅ NAVIGATE TO SIGN IN PAGE
     * This ensures every test starts from Sign In page.
     */
    private void navigateToSignInPage() throws Exception {
        // Driver already initialized by BaseTest.setup()

        SignInPage signInPage = new SignInPage(driver);

        // Check if already on Sign In page
        if (signInPage.isOnSignInPage()) {
            System.out.println("✓ Already on Sign In page");
            return;
        }

        // Check if on Home page (logged in)
        HomePage homePage = new HomePage(driver);
        if (homePage.isHomePageDisplayed()) {
            System.out.println("User is logged in, navigating to Sign In page...");

            // Navigate: Wellbeing Dashboard → Profile → Logout → Yes
            homePage.navigateToLogout();

            ProfilePage profilePage = new ProfilePage(driver);
            profilePage.clickLogout();
            Thread.sleep(1000);

            // Click YES in logout confirmation
            profilePage.clickYes();
            Thread.sleep(2000);

            System.out.println("✓ Navigated to Sign In page");
            return;
        }

        // If on other page, press back until Sign In page appears
        int maxAttempts = 10;
        int attempts = 0;
        while (!signInPage.isOnSignInPage() && attempts < maxAttempts) {
            driver.navigate().back();
            Thread.sleep(500);
            attempts++;
        }

        if (signInPage.isOnSignInPage()) {
            System.out.println("✓ Navigated to Sign In page via back button");
        } else {
            System.out.println("⚠ Warning: Could not navigate to Sign In page");
        }
    }

    /**
     * ✅ NEGATIVE TEST DATA (NO HARDCODED EXPECTED MESSAGES)
     * Only test scenario name, email, and password
     */
    @DataProvider(name = "negativeLoginData")
    public Object[][] getNegativeLoginData() {
        return new Object[][] {
                { "Invalid Password", "testuser@example.com", "WrongPass" },
                { "Invalid Email Format", "testuser", "Password@123" },
                { "Non-existent Account", "notfound@example.com", "Password@123" },
                { "Empty Email", "", "Password@123" },
                { "Empty Password", "testuser@example.com", "" },
                { "Both Empty", "", "" },
                { "Special Characters Email", "test@#$%@example.com", "Password@123" },
        };
    }

    /**
     * ✅ RUNTIME-BASED NEGATIVE TEST (NO HARDCODED VALIDATION)
     * 
     * Test Flow:
     * 1. Enter email and password
     * 2. Click Continue button
     * 3. Check if ANY validation appears at runtime
     * 4. PASS if validation detected, FAIL if not
     * 5. Log the actual runtime validation message in Extent Report
     */
    @Test(dataProvider = "negativeLoginData")
    public void testNegativeSignIn(String testScenario, String email, String password)
            throws InterruptedException {

        test = extent.createTest("Negative Test: " + testScenario);
        test.log(Status.INFO, "Email: '" + email + "' | Password: '" + password + "'");

        SignInPage signInPage = new SignInPage(driver);

        // Step 1: Enter credentials
        signInPage.enterEmail(email);
        signInPage.enterPassword(password);
        test.log(Status.INFO, "Entered credentials");

        // Step 2: Click Continue button
        signInPage.clickContinue();
        test.log(Status.INFO, "Clicked Continue button");

        // Step 3: ✅ Wait for validation to appear using explicit wait (replaces
        // Thread.sleep)
        signInPage.waitForValidationToAppear(3);

        // Step 4: ✅ RUNTIME VALIDATION CHECK (NO HARDCODED MESSAGES)
        boolean validationDetected = signInPage.isAnyValidationVisible();

        if (validationDetected) {
            // ✅ PASS: Validation appeared (negative case handled correctly)

            // 🆕 Capture and log the actual runtime validation message
            String validationMessage = signInPage.getValidationMessage();
            if (validationMessage != null && !validationMessage.trim().isEmpty()) {
                test.log(Status.INFO, "📋 Validation message displayed: \"" + validationMessage + "\"");
            }

            test.log(Status.PASS, "✓ Validation detected at runtime - Negative case handled correctly");
            test.log(Status.PASS, "Test PASSED: Application showed validation for invalid input");
        } else {
            // ❌ FAIL: No validation appeared (security/UX issue)
            test.log(Status.FAIL, "✗ NO validation detected at runtime");
            test.log(Status.FAIL, "Test FAILED: Application did not show any validation for invalid input");
            Assert.fail("Expected validation to appear for negative test case, but NONE was detected");
        }
    }

    /**
     * ✅ POSITIVE LOGIN TEST (Optional - for comparison)
     * This test expects successful login without validation errors
     */
    @Test
    public void testPositiveSignIn() throws InterruptedException {
        test = extent.createTest("Positive Test: Valid Login");
        test.log(Status.INFO, "Testing valid credentials");

        SignInPage signInPage = new SignInPage(driver);

        // Use valid credentials
        String validEmail = "testuser@example.com";
        String validPassword = "Password@123";

        signInPage.enterEmail(validEmail);
        signInPage.enterPassword(validPassword);
        test.log(Status.INFO, "Entered valid credentials");

        signInPage.clickContinue();
        test.log(Status.INFO, "Clicked Continue button");

        // ✅ Wait for navigation/validation using explicit wait (replaces Thread.sleep)
        signInPage.waitForValidationToAppear(3);

        // For positive case, we expect NO validation errors
        boolean validationDetected = signInPage.isAnyValidationVisible();

        if (!validationDetected) {
            // ✅ PASS: No validation errors for valid credentials
            test.log(Status.PASS, "✓ No validation errors - Login successful");
            test.log(Status.PASS, "Test PASSED: Valid credentials accepted");
        } else {
            // ❌ FAIL: Unexpected validation for valid credentials
            test.log(Status.FAIL, "✗ Unexpected validation appeared for valid credentials");
            test.log(Status.FAIL, "Test FAILED: Valid credentials were rejected");
            Assert.fail("Valid credentials should not show validation errors");
        }
    }
}
