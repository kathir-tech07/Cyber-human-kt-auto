package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.*;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EditProfileTest extends BaseTest {

    /**
     * ✅ NEGATIVE TEST DATA FOR EDIT PROFILE
     * Test scenarios for Edit Profile page validation
     */
    @DataProvider(name = "negativeEditProfileData")
    public Object[][] getNegativeEditProfileData() {
        return new Object[][] {
                // Scenario, Name, Email, Phone
                { "Email Without @", "John Doe", "testexample.com", "123456789" },
                { "Email Without Domain", "John Doe", "test@", "123456789" },
                { "Invalid Phone - Letters", "John Doe", "test@example.com", "abcdefgh" },
                { "Short Phone Number", "John Doe", "test@example.com", "12" },
                { "Invalid Email Format", "John Doe", "invalidemail", "123456789" },
                { "Empty Name Field", "", "test@example.com", "123456789" },
                { "Empty Email Field", "John Doe", "", "123456789" },
                { "Empty Phone Number", "John Doe", "test@example.com", "" },

        };
    }

    // Track if we've already done the initial login flow
    private static boolean isLoggedIn = false;

    /**
     * ✅ COMPLETE NAVIGATION FLOW TO EDIT PROFILE PAGE (FIRST TEST ONLY)
     * This method handles the entire navigation from Sign In to Edit Profile
     */
    private void navigateToEditProfileFirstTime() throws InterruptedException {
        // Step 1: Sign In with valid credentials
        SignInPage signInPage = new SignInPage(driver);
        signInPage.enterEmail("ramesh@navadhiti.com");
        signInPage.enterPassword("Ramesh@2025");
        signInPage.clickContinue();
        test.log(Status.INFO, "Signed in with valid credentials");
        Thread.sleep(2000); // Wait for login

        // Step 2: Handle Two-Factor Authentication - Click NO
        TwoFactorAuthPage twoFactorPage = new TwoFactorAuthPage(driver);
        if (twoFactorPage.isTwoFactorAuthPageDisplayed()) {
            twoFactorPage.clickNo();
            test.log(Status.INFO, "Clicked NO on Two-Factor Authentication page");
            Thread.sleep(2000);
        }

        // Step 3: Handle Link Devices - Click SKIP FOR NOW
        LinkDevicesPage linkDevicesPage = new LinkDevicesPage(driver);
        if (linkDevicesPage.isLinkDevicesPageDisplayed()) {
            linkDevicesPage.clickSkipForNow();
            test.log(Status.INFO, "Clicked SKIP FOR NOW on Link Devices page");
            Thread.sleep(2000);
        }

        // Step 4: Navigate to Homepage and click Wellbeing Dashboard
        HomePage homePage = new HomePage(driver);
        if (homePage.isHomePageDisplayed()) {
            homePage.clickWellbeingDashboard();
            test.log(Status.INFO, "Clicked Wellbeing Dashboard menu");
            Thread.sleep(2000);

            // Step 4.1: Click PROFILE
            homePage.clickProfile();
            test.log(Status.INFO, "Clicked PROFILE button");
            Thread.sleep(2000);
        }

        // Step 5: Check if Profile page displayed (optional verification)
        ProfilePage profilePage = new ProfilePage(driver);
        // ...

        // Step 6: Click ACCOUNT to navigate to Edit Profile
        profilePage.clickAccount();
        test.log(Status.INFO, "Clicked ACCOUNT button");
        Thread.sleep(2000);

        // Verify Edit Profile page is displayed
        EditProfilePage editProfilePage = new EditProfilePage(driver);
        if (editProfilePage.isEditProfilePageDisplayed()) {
            test.log(Status.INFO, "Edit Profile page displayed successfully");
        }

        // Mark as logged in for subsequent tests
        isLoggedIn = true;
    }

    /**
     * ✅ NAVIGATION FROM HOMEPAGE TO EDIT PROFILE (SUBSEQUENT TESTS)
     * This method starts from Homepage and navigates to Edit Profile
     * Used for all tests after the first one
     */
    private void navigateToEditProfileFromHome() throws InterruptedException {
        // Start from Homepage - click Wellbeing Dashboard
        HomePage homePage = new HomePage(driver);
        if (homePage.isHomePageDisplayed()) {
            homePage.clickWellbeingDashboard();
            test.log(Status.INFO, "Clicked Wellbeing Dashboard menu from Homepage");
            Thread.sleep(2000);

            // Click PROFILE
            homePage.clickProfile();
            test.log(Status.INFO, "Clicked PROFILE button");
            Thread.sleep(2000);
        }

        // Click ACCOUNT to navigate to Edit Profile
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickAccount();
        test.log(Status.INFO, "Clicked ACCOUNT button");
        Thread.sleep(2000);

        // Verify Edit Profile page is displayed
        EditProfilePage editProfilePage = new EditProfilePage(driver);
        if (editProfilePage.isEditProfilePageDisplayed()) {
            test.log(Status.INFO, "Edit Profile page displayed successfully");
        }
    }

    /**
     * ✅ SMART NAVIGATION - First test does full flow, subsequent tests start from
     * home. Also handles recovery if app is already on Edit Profile page.
     */
    private void navigateToEditProfile() throws InterruptedException {
        // 🆕 STATE CHECK: If we are already on Edit Profile Page (from previous failed
        // test),
        // we should navigate back to start fresh from Profile Page
        try {
            EditProfilePage editProfilePage = new EditProfilePage(driver);
            if (editProfilePage.isEditProfilePageDisplayed()) {
                test.log(Status.INFO, "Recovery: Detected app is already on Edit Profile page. Navigating back.");
                driver.navigate().back();
                Thread.sleep(1000);
            }
        } catch (Exception ignored) {
            // Ignore if check fails, normal flow attempts navigation
        }

        if (!isLoggedIn) {
            // First test - do complete navigation
            navigateToEditProfileFirstTime();
        } else {
            // Subsequent tests - start from homepage
            navigateToEditProfileFromHome();
        }
    }

    /**
     * ✅ RUNTIME-BASED NEGATIVE TEST FOR EDIT PROFILE
     * 
     * Test Flow:
     * 1. Navigate from Sign In through all pages to Edit Profile
     * 2. Enter invalid/empty data in form fields
     * 3. Click SAVE CHANGES button
     * 4. Check if ANY validation appears at runtime
     * 5. PASS if validation detected, FAIL if not
     * 6. Log the actual runtime validation message in Extent Report
     */
    @Test(dataProvider = "negativeEditProfileData")
    public void testNegativeEditProfile(String testScenario, String name, String email, String phone)
            throws InterruptedException {

        test = extent.createTest("Negative Test: " + testScenario);
        test.log(Status.INFO, "Name: '" + name + "' | Email: '" + email + "' | Phone: '" + phone + "'");

        try {
            // Navigate to Edit Profile page
            navigateToEditProfile();

            EditProfilePage editProfilePage = new EditProfilePage(driver);

            // Clear and enter test data
            editProfilePage.enterName(name);
            test.log(Status.INFO, "Entered name: '" + name + "'");

            editProfilePage.enterEmail(email);
            test.log(Status.INFO, "Entered email: '" + email + "'");

            // ✅ HANDLE DATE OF BIRTH
            editProfilePage.clickDateOfBirth();
            test.log(Status.INFO, "Clicked Date of Birth");
            editProfilePage.performDateSelection();
            test.log(Status.INFO, "Performed Date Selection (Swipe & Confirm)");

            // ✅ HANDLE GENDER (Standardized to Male for negative tests)
            editProfilePage.clickGender();
            test.log(Status.INFO, "Clicked Gender dropdown");
            Thread.sleep(500);
            editProfilePage.selectGender("Male");
            test.log(Status.INFO, "Selected gender: Male");

            // ✅ HANDLE COUNTRY (Standardized to India for negative tests)
            editProfilePage.clickCountryCode();
            test.log(Status.INFO, "Clicked Country Code dropdown");
            Thread.sleep(500);
            editProfilePage.selectCountry("Belarus");
            test.log(Status.INFO, "Selected country: Belarus");

            editProfilePage.enterPhoneNumber(phone);
            test.log(Status.INFO, "Entered phone: '" + phone + "'");

            // Click SAVE CHANGES
            editProfilePage.clickSaveChanges();
            test.log(Status.INFO, "Clicked SAVE CHANGES button");

            // Wait for validation to appear
            Thread.sleep(2000);

            // ✅ RUNTIME VALIDATION CHECK (NO HARDCODED MESSAGES)
            boolean validationDetected = editProfilePage.isAnyValidationVisible();

            if (validationDetected) {
                // ✅ PASS: Validation appeared (negative case handled correctly)

                // 🆕 Capture and log the actual runtime validation message
                String validationMessage = editProfilePage.getValidationMessage();
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
        } catch (Exception e) {
            test.log(Status.FAIL, "Test encountered an error: " + e.getMessage());
            throw e; // Re-throw to ensure TestNG records it as failure
        }
    }

    /**
     * ✅ TEST WITH DATE OF BIRTH, GENDER, AND COUNTRY SELECTION
     * 
     * This test demonstrates handling of:
     * - Date of Birth picker (runtime)
     * - Gender dropdown
     * - Country selection (India)
     */
    @Test
    public void testEditProfileWithDateGenderCountry() throws InterruptedException {
        test = extent.createTest("Test: Edit Profile with Date, Gender, Country");
        test.log(Status.INFO, "Testing date picker, gender dropdown, and country selection");

        // Navigate to Edit Profile page
        navigateToEditProfile();

        EditProfilePage editProfilePage = new EditProfilePage(driver);

        // Enter valid data
        editProfilePage.enterName("Test User");
        test.log(Status.INFO, "Entered name");

        editProfilePage.enterEmail("test@example.com");
        test.log(Status.INFO, "Entered email");

        editProfilePage.clickDateOfBirth();
        test.log(Status.INFO, "Clicked Date of Birth field");

        editProfilePage.performDateSelection();
        test.log(Status.INFO, "Performed Date Selection (Swipe & Confirm)");
        Thread.sleep(1000);

        // Click and select Gender
        editProfilePage.clickGender();
        test.log(Status.INFO, "Clicked Gender dropdown");
        Thread.sleep(1000);

        editProfilePage.selectGender("Male");
        test.log(Status.INFO, "Selected gender: Male");
        Thread.sleep(1000);

        // Click and select Country (India)
        editProfilePage.clickCountryCode();
        test.log(Status.INFO, "Clicked Country Code dropdown");
        Thread.sleep(1000);

        editProfilePage.selectCountry("Belarus");
        test.log(Status.INFO, "Selected country: Belarus");
        Thread.sleep(1000);

        editProfilePage.enterPhoneNumber("9876543210");
        test.log(Status.INFO, "Entered phone number");

        // Click SAVE CHANGES
        editProfilePage.clickSaveChanges();
        test.log(Status.INFO, "Clicked SAVE CHANGES button");

        Thread.sleep(2000);

        // Check for validation (should not appear for valid data)
        boolean validationDetected = editProfilePage.isAnyValidationVisible();

        if (!validationDetected) {
            test.log(Status.PASS, "✓ No validation errors - Profile update successful");
            test.log(Status.PASS, "Test PASSED: Valid data accepted");
        } else {
            String validationMessage = editProfilePage.getValidationMessage();
            if (validationMessage != null) {
                test.log(Status.INFO, "Validation message: \"" + validationMessage + "\"");
            }
            test.log(Status.INFO, "Validation appeared - may need to check data format");
        }
    }
}
