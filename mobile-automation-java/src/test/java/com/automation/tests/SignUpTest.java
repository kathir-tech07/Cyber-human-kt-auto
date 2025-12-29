package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.SignUpPage;
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

public class SignUpTest extends BaseTest {

    /**
     * ✅ OVERRIDE SETUP TO SKIP HOME PAGE RESET
     * Sign Up tests need to start from Sign In page (logged out state),
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

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        driver.manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(6));

        // Wait for app to load (no navigation needed - tests handle it)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("⚠ Warning: Setup sleep interrupted: " + e.getMessage());
        }
    }

    /**
     * ✅ NEGATIVE TEST DATA (NO HARDCODED EXPECTED MESSAGES)
     * Test scenarios for Sign Up page validation
     */
    @DataProvider(name = "negativeSignUpData")
    public Object[][] getNegativeSignUpData() {
        return new Object[][] {
                // Scenario, Name, Email, Password, Confirm Password
                { "Invalid Email Format", "John Doe", "invalidemail", "Password@123", "Password@123" },
                { "Empty Name Field", "", "test@example.com", "Password@123", "Password@123" },
                { "Empty Email Field", "John Doe", "", "Password@123", "Password@123" },
                { "Empty Password Field", "John Doe", "test@example.com", "", "Password@123" },
                { "Empty Confirm Password", "John Doe", "test@example.com", "Password@123", "" },
                { "Password Mismatch", "John Doe", "test@example.com", "Password@123", "DifferentPass@123" },
                { "Weak Password", "John Doe", "test@example.com", "123", "123" },
                { "All Fields Empty", "", "", "", "" },
                { "Special Characters in Name", "@#$%^&*", "test@example.com", "Password@123", "Password@123" },
                { "Email Without Domain", "John Doe", "test@", "Password@123", "Password@123" },
                { "Short Password", "John Doe", "test@example.com", "12", "12" },
                { "Only Spaces in Name", "   ", "test@example.com", "Password@123", "Password@123" },
        };
    }

    /**
     * ✅ RUNTIME-BASED NEGATIVE TEST FOR SIGN UP
     * 
     * Test Flow:
     * 0. Navigate from Sign In page to Sign Up page
     * 1. Enter name, email, password, and confirm password
     * 2. Select country from dropdown
     * 3. Click SIGN UP heading
     * 4. Click Continue button
     * 5. Check if ANY validation appears at runtime
     * 6. PASS if validation detected, FAIL if not
     * 7. Log the actual runtime validation message in Extent Report
     */
    @Test(dataProvider = "negativeSignUpData")
    public void testNegativeSignUp(String testScenario, String name, String email, String password,
            String confirmPassword) throws InterruptedException {

        test = extent.createTest("Negative Test: " + testScenario);
        test.log(Status.INFO, "Name: '" + name + "' | Email: '" + email + "'");
        test.log(Status.INFO, "Password: '" + password + "' | Confirm: '" + confirmPassword + "'");

        // Step 0: Navigate from Sign In to Sign Up page
        com.automation.pages.SignInPage signInPage = new com.automation.pages.SignInPage(driver);
        signInPage.clickSignUp();
        test.log(Status.INFO, "Navigated to Sign Up page from Sign In");
        Thread.sleep(2000); // Wait for page transition

        SignUpPage signUpPage = new SignUpPage(driver);

        // Step 1: Enter form data
        signUpPage.enterName(name);
        signUpPage.enterEmail(email);
        test.log(Status.INFO, "Entered name and email");

        // Step 2: Select country from dropdown
        signUpPage.selectFirstCountryOption();
        test.log(Status.INFO, "Selected country from dropdown");

        // Step 3: Enter passwords
        signUpPage.enterPassword(password);
        signUpPage.enterConfirmPassword(confirmPassword);
        test.log(Status.INFO, "Entered password and confirm password");

        // Step 4: Click SIGN UP heading (required before Continue)
        signUpPage.clickSignUpHeading();
        test.log(Status.INFO, "Clicked SIGN UP heading");

        // Step 5: Click Continue button
        signUpPage.clickContinue();
        test.log(Status.INFO, "Clicked Continue button");

        // Step 6: Wait for validation to appear (if any)
        Thread.sleep(2000); // Allow time for validation to render

        // Step 7: ✅ RUNTIME VALIDATION CHECK (NO HARDCODED MESSAGES)
        boolean validationDetected = signUpPage.isAnyValidationVisible();

        if (validationDetected) {
            // ✅ PASS: Validation appeared (negative case handled correctly)

            // 🆕 Capture and log the actual runtime validation message
            String validationMessage = signUpPage.getValidationMessage();
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
     * ✅ POSITIVE SIGN UP TEST (Optional - for comparison)
     * This test expects successful sign up without validation errors
     * 
     * Test Flow:
     * 0. Navigate from Sign In page to Sign Up page
     * 1. Enter valid name, email, password, and confirm password
     * 2. Select country from dropdown
     * 3. Click SIGN UP heading
     * 4. Click Continue button
     * 5. Verify no validation errors appear
     */
    @Test
    public void testPositiveSignUp() throws InterruptedException {
        test = extent.createTest("Positive Test: Valid Sign Up");
        test.log(Status.INFO, "Testing valid sign up credentials");

        // Step 0: Navigate from Sign In to Sign Up page
        com.automation.pages.SignInPage signInPage = new com.automation.pages.SignInPage(driver);
        signInPage.clickSignUp();
        test.log(Status.INFO, "Navigated to Sign Up page from Sign In");
        Thread.sleep(2000); // Wait for page transition

        SignUpPage signUpPage = new SignUpPage(driver);

        // Use valid credentials
        String validName = "John Doe";
        String validEmail = "john.doe" + System.currentTimeMillis() + "@example.com"; // Unique email
        String validPassword = "SecurePass@123";

        // Step 1: Enter form data
        signUpPage.enterName(validName);
        signUpPage.enterEmail(validEmail);
        test.log(Status.INFO, "Entered valid name and email");

        // Step 2: Select country from dropdown
        signUpPage.selectFirstCountryOption();
        test.log(Status.INFO, "Selected country from dropdown");

        // Step 3: Enter passwords
        signUpPage.enterPassword(validPassword);
        signUpPage.enterConfirmPassword(validPassword);
        test.log(Status.INFO, "Entered valid password and confirm password");

        // Step 4: Click SIGN UP heading (required before Continue)
        signUpPage.clickSignUpHeading();
        test.log(Status.INFO, "Clicked SIGN UP heading");

        // Step 5: Click Continue button
        signUpPage.clickContinue();
        test.log(Status.INFO, "Clicked Continue button");

        Thread.sleep(2000); // Wait for validation/navigation

        // For positive case, we expect NO validation errors
        boolean validationDetected = signUpPage.isAnyValidationVisible();

        if (!validationDetected) {
            // ✅ PASS: No validation errors for valid credentials
            test.log(Status.PASS, "✓ No validation errors - Sign up successful");
            test.log(Status.PASS, "Test PASSED: Valid credentials accepted");
        } else {
            // ❌ FAIL: Unexpected validation for valid credentials
            String validationMessage = signUpPage.getValidationMessage();
            if (validationMessage != null) {
                test.log(Status.FAIL, "Validation message: \"" + validationMessage + "\"");
            }
            test.log(Status.FAIL, "✗ Unexpected validation appeared for valid credentials");
            test.log(Status.FAIL, "Test FAILED: Valid credentials were rejected");
            Assert.fail("Valid credentials should not show validation errors");
        }
    }
}
