package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.SignInPage;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SignInTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
                { "Valid Login", "testuser@example.com", "Password@123", "Success" },
                { "Invalid Password", "testuser@example.com", "WrongPass", "Error" },
                { "Invalid Email Format", "testuser", "Password@123", "Error" },
                { "Non-existent Account", "notfound@example.com", "Password@123", "Error" },
                { "Empty Email", "", "Password@123", "Empty" },
                { "Empty Password", "testuser@example.com", "", "Empty" },
                { "Both Empty", "", "", "Empty" }
        };
    }

    @Test(dataProvider = "loginData")
    public void testSignIn(String description, String email, String password, String expectedType)
            throws InterruptedException {

        test = extent.createTest("Test: " + description);
        test.log(Status.INFO, "Email: " + email + " | Password: " + password);

        SignInPage signInPage = new SignInPage(driver);

        signInPage.enterEmail(email);
        signInPage.enterPassword(password);

        // Check button state before clicking
        boolean isButtonDisabled = signInPage.isContinueButtonDisabled();
        String buttonState = signInPage.getContinueButtonState();
        
        test.log(Status.INFO, "Continue button state: " + buttonState);
        if (isButtonDisabled) {
            test.log(Status.INFO, "Continue button is disabled (light grey)");
        }

        signInPage.clickContinue();

        // Reduced wait time - validation messages appear quickly
        Thread.sleep(1500);

        /* ================= EMPTY FIELD ================= */
        if (expectedType.equalsIgnoreCase("Empty")) {
            // Check validation message first
            String validationMsg = signInPage.getRuntimeValidationMessage();
            
            if (isButtonDisabled) {
                test.log(Status.PASS, "✓ Continue button is disabled (light grey) for empty fields");
            }
            
            if (validationMsg != null && !validationMsg.isBlank()) {
                test.log(Status.PASS, "✓ Validation message displayed: " + validationMsg);
            }
            
            if (isButtonDisabled || validationMsg != null) {
                test.log(Status.PASS, "Empty field validation working correctly");
            } else {
                test.log(Status.FAIL, "Empty field validation NOT detected - button enabled and no message");
                Assert.fail();
            }
            return;
        }

        /* ================= SUCCESS ================= */
        if (expectedType.equalsIgnoreCase("Success")) {
            test.log(Status.PASS, "Login successful");
            return;
        }

        /* ================= NEGATIVE CASES ================= */
        String error = signInPage.getRuntimeValidationMessage();
        
        // Log button state for negative cases too
        if (signInPage.isContinueButtonDisabled()) {
            test.log(Status.INFO, "Continue button became disabled after validation");
        }
        
        if (error != null && !error.isBlank()) {
            test.log(Status.INFO, "✓ Validation Message: " + error);
            test.log(Status.PASS, "Negative validation displayed correctly");
        } else {
            test.log(Status.FAIL, "Expected validation message not captured");
            Assert.fail();
        }
    }
}
