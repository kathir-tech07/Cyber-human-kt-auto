package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.SignInPage;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SignInTest extends BaseTest {

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

        // Step 3: Wait for validation to appear (if any)
        Thread.sleep(2000); // Allow time for validation to render

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

        Thread.sleep(3000); // Wait for navigation/success

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
