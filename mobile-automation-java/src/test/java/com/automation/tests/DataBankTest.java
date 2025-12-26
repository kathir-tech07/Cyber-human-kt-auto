package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.HomePage;
import com.automation.pages.DataBankPage;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataBankTest extends BaseTest {

    /**
     * ==================== DATA BANK TEST CASE 1 ====================
     * 
     * Common Steps (1, 2 & 3):
     * 1. Verify DAILY PRIORITY heading on home page
     * 2. Click Wellbeing Dashboard
     * 3. Click DATA BANK and verify it's displayed
     * 
     * Test-Specific Steps:
     * 4. Click PACKAGES & PRICING
     * 5. Click CONTINUE button
     * 6. Click checkbox
     * 7. Click CONTINUE button
     */
    @Test(priority = 1)
    public void testDataBank_Case1() throws InterruptedException {
        test = extent.createTest("Data Bank Test Case 1");
        test.log(Status.INFO, "Starting Data Bank Test Case 1");

        HomePage homePage = new HomePage(driver);
        DataBankPage dataBankPage = new DataBankPage(driver);

        // ✅ COMMON STEP 1: Verify DAILY PRIORITY heading is displayed on home page
        test.log(Status.INFO, "Step 1: Verifying DAILY PRIORITY heading on home page");
        boolean isHomePageDisplayed = homePage.isHomePageDisplayed();
        if (!isHomePageDisplayed) {
            test.log(Status.FAIL, "DAILY PRIORITY heading not found on home page");
            Assert.fail("Home page validation failed - DAILY PRIORITY heading not displayed");
        }
        test.log(Status.PASS, "✓ Step 1: DAILY PRIORITY heading is displayed on home page");

        // ✅ COMMON STEP 2: Click Wellbeing Dashboard (if not already there)
        test.log(Status.INFO, "Step 2: Clicking Wellbeing Dashboard");
        try {
            homePage.clickWellbeingDashboard();
            test.log(Status.PASS, "✓ Step 2: Wellbeing Dashboard clicked");
        } catch (Exception e) {
            test.log(Status.INFO, "Wellbeing Dashboard not found, assuming already on dashboard");
        }

        // ✅ COMMON STEP 3: Click DATA BANK and verify
        test.log(Status.INFO, "Step 3: Clicking DATA BANK");
        dataBankPage.clickAndVerifyDataBank();
        test.log(Status.PASS, "✓ Step 3: DATA BANK clicked and verified");

        // Verify Data Bank page is displayed
        Assert.assertTrue(dataBankPage.isDataBankPageDisplayed(),
                "Data Bank page should be displayed");
        test.log(Status.PASS, "✓ Verified: Data Bank page is displayed");

        // ✅ TEST CASE 1 - STEP 4: Click PACKAGES & PRICING
        test.log(Status.INFO, "Step 4: Clicking PACKAGES & PRICING");
        dataBankPage.clickPackagesAndPricing();
        test.log(Status.PASS, "✓ Step 4: PACKAGES & PRICING clicked");

        // ✅ TEST CASE 1 - STEP 5: Click CONTINUE button
        test.log(Status.INFO, "Step 5: Clicking CONTINUE button");
        dataBankPage.clickContinueButton();
        test.log(Status.PASS, "✓ Step 5: CONTINUE button clicked");

        // ✅ TEST CASE 1 - STEP 6: Click checkbox
        test.log(Status.INFO, "Step 6: Clicking checkbox");
        dataBankPage.clickCheckbox();
        test.log(Status.PASS, "✓ Step 6: Checkbox clicked");

        // ✅ TEST CASE 1 - STEP 7: Click CONTINUE button again
        test.log(Status.INFO, "Step 7: Clicking CONTINUE button");
        dataBankPage.clickContinueButton();
        test.log(Status.PASS, "✓ Step 7: CONTINUE button clicked");

        test.log(Status.PASS, "Data Bank Test Case 1 completed successfully");
    }

    /**
     * ==================== DATA BANK TEST CASE 2 ====================
     * 
     * Common Steps (1, 2 & 3):
     * 1. Verify DAILY PRIORITY heading on home page
     * 2. Click Wellbeing Dashboard
     * 3. Click DATA BANK and verify it's displayed
     * 
     * Test-Specific Steps:
     * 4. Click UPLOAD DATA
     * 5. Click UPLOAD REPORT button
     * 6. Click checkbox
     * 7. Click CONTINUE button
     * 8. Upload wrong format file (JPG)
     * 9. Validate UPLOAD FAILED dialog and capture error message
     * 10. Click OK button
     * 11. Repeat steps 5, 6, 7 (Upload Report, Checkbox, Continue)
     * 12. Upload correct format file (PDF)
     * 13. Validate UPLOAD SUCCESSFUL dialog
     * 14. Capture success message
     * 15. Click OK button
     * 16. Click remove button and verify ARE YOU SURE? dialog
     * 17. Click YES button and verify DELETE SUCCESSFUL dialog
     * 18. Capture delete success message
     */
    @Test(priority = 2)
    public void testDataBank_Case2() throws InterruptedException {
        test = extent.createTest("Data Bank Test Case 2");
        test.log(Status.INFO, "Starting Data Bank Test Case 2");

        HomePage homePage = new HomePage(driver);
        DataBankPage dataBankPage = new DataBankPage(driver);

        // ✅ COMMON STEP 1: Verify DAILY PRIORITY heading is displayed on home page
        test.log(Status.INFO, "Step 1: Verifying DAILY PRIORITY heading on home page");
        boolean isHomePageDisplayed = homePage.isHomePageDisplayed();
        if (!isHomePageDisplayed) {
            test.log(Status.FAIL, "DAILY PRIORITY heading not found on home page");
            Assert.fail("Home page validation failed - DAILY PRIORITY heading not displayed");
        }
        test.log(Status.PASS, "✓ Step 1: DAILY PRIORITY heading is displayed on home page");

        // ✅ COMMON STEP 2: Click Wellbeing Dashboard (if not already there)
        test.log(Status.INFO, "Step 2: Clicking Wellbeing Dashboard");
        try {
            homePage.clickWellbeingDashboard();
            test.log(Status.PASS, "✓ Step 2: Wellbeing Dashboard clicked");
        } catch (Exception e) {
            test.log(Status.INFO, "Wellbeing Dashboard not found, assuming already on dashboard");
        }

        // ✅ COMMON STEP 3: Click DATA BANK and verify
        test.log(Status.INFO, "Step 3: Clicking DATA BANK");
        dataBankPage.clickAndVerifyDataBank();
        test.log(Status.PASS, "✓ Step 3: DATA BANK clicked and verified");

        // Verify Data Bank page is displayed
        Assert.assertTrue(dataBankPage.isDataBankPageDisplayed(),
                "Data Bank page should be displayed");
        test.log(Status.PASS, "✓ Verified: Data Bank page is displayed");

        // ✅ TEST CASE 2 - STEP 4: Click UPLOAD DATA
        test.log(Status.INFO, "Step 4: Clicking UPLOAD DATA");
        dataBankPage.clickUploadData();
        test.log(Status.PASS, "✓ Step 4: UPLOAD DATA clicked");

        // ✅ TEST CASE 2 - STEP 5: Click UPLOAD REPORT button
        test.log(Status.INFO, "Step 5: Clicking UPLOAD REPORT button");
        dataBankPage.clickUploadReportButton();
        test.log(Status.PASS, "✓ Step 5: UPLOAD REPORT button clicked");

        // ✅ TEST CASE 2 - STEP 6: Click checkbox
        test.log(Status.INFO, "Step 6: Clicking checkbox");
        dataBankPage.clickCheckbox();
        test.log(Status.PASS, "✓ Step 6: Checkbox clicked");

        // ✅ TEST CASE 2 - STEP 7: Click CONTINUE button
        test.log(Status.INFO, "Step 7: Clicking CONTINUE button");
        dataBankPage.clickContinueButton();
        test.log(Status.PASS, "✓ Step 7: CONTINUE button clicked");

        // ✅ TEST CASE 2 - STEP 8: Upload wrong format file (JPG instead of PDF)
        test.log(Status.INFO, "Step 8: Uploading wrong format file (JPG)");
        dataBankPage.uploadWrongFormatFile();
        test.log(Status.INFO, "✓ Step 8: Wrong format file upload attempted");

        // ✅ TEST CASE 2 - STEP 9: Validate UPLOAD FAILED dialog and capture error
        // message
        test.log(Status.INFO, "Step 9: Validating UPLOAD FAILED dialog");
        String errorMessage = dataBankPage.validateUploadFailedDialog();
        test.log(Status.PASS, "✓ Step 9: UPLOAD FAILED dialog validated");
        test.log(Status.INFO, "📋 Error Message: " + errorMessage);

        // Verify the error message is correct
        Assert.assertEquals(errorMessage, "Wrong file type chosen. Only allowed type is pdf.",
                "Error message should indicate wrong file type");
        test.log(Status.PASS, "✓ Verified: Correct error message displayed");

        // ✅ TEST CASE 2 - STEP 10: Click OK button after failed upload
        test.log(Status.INFO, "Step 10: Clicking OK button");
        dataBankPage.clickOkButton();
        test.log(Status.PASS, "✓ Step 10: OK button clicked");

        // ✅ TEST CASE 2 - STEP 11: Repeat steps 5, 6, 7 (Upload Report, Checkbox,
        // Continue)
        test.log(Status.INFO, "Step 11: Repeating upload flow - Click UPLOAD REPORT button");
        dataBankPage.clickUploadReportButton();
        test.log(Status.PASS, "✓ Step 11a: UPLOAD REPORT button clicked");

        test.log(Status.INFO, "Step 11: Clicking checkbox");
        dataBankPage.clickCheckbox();
        test.log(Status.PASS, "✓ Step 11b: Checkbox clicked");

        test.log(Status.INFO, "Step 11: Clicking CONTINUE button");
        dataBankPage.clickContinueButton();
        test.log(Status.PASS, "✓ Step 11c: CONTINUE button clicked");

        // ✅ TEST CASE 2 - STEP 12: Upload correct format file (PDF)
        test.log(Status.INFO, "Step 12: Uploading correct format file (PDF)");
        dataBankPage.uploadCorrectFormatFile();
        test.log(Status.INFO, "✓ Step 12: Correct format file (PDF) upload attempted");

        // ✅ TEST CASE 2 - STEP 13 & 14: Validate UPLOAD SUCCESSFUL dialog and capture
        // success message
        test.log(Status.INFO, "Step 13: Validating UPLOAD SUCCESSFUL dialog");
        String successMessage = dataBankPage.validateUploadSuccessfulDialog();
        test.log(Status.PASS, "✓ Step 13: UPLOAD SUCCESSFUL dialog validated");
        test.log(Status.INFO, "📋 Success Message: " + successMessage);

        // Verify the success message is correct
        Assert.assertEquals(successMessage, "Your document has been successfully uploaded",
                "Success message should confirm successful upload");
        test.log(Status.PASS, "✓ Step 14: Verified correct success message displayed");

        // ✅ TEST CASE 2 - STEP 15: Click OK button after successful upload
        test.log(Status.INFO, "Step 15: Clicking OK button");
        dataBankPage.clickOkButton();
        test.log(Status.PASS, "✓ Step 15: OK button clicked");

        // Wait for UI to settle after closing the success dialog
        Thread.sleep(2000);

        // ✅ TEST CASE 2 - STEP 16: Click remove button and verify ARE YOU SURE? dialog
        test.log(Status.INFO, "Step 16: Clicking remove button and verifying ARE YOU SURE? dialog");
        dataBankPage.clickRemoveAndVerifyAreYouSureDialog();
        test.log(Status.PASS, "✓ Step 16: Remove button clicked and ARE YOU SURE? dialog verified");

        // ✅ TEST CASE 2 - STEP 17: Click YES button and verify DELETE SUCCESSFUL dialog
        test.log(Status.INFO, "Step 17: Clicking YES button and verifying DELETE SUCCESSFUL dialog");
        dataBankPage.clickYesAndVerifyDeleteSuccessfulDialog();
        test.log(Status.PASS, "✓ Step 17: YES button clicked and DELETE SUCCESSFUL dialog verified");

        // ✅ TEST CASE 2 - STEP 18: Capture delete success message
        test.log(Status.INFO, "Step 18: Capturing delete success message");
        String deleteSuccessMessage = dataBankPage.captureDeleteSuccessMessage();
        test.log(Status.PASS, "✓ Step 18: Delete success message captured");
        test.log(Status.INFO, "📋 Delete Success Message: " + deleteSuccessMessage);

        // Verify the delete success message is correct
        Assert.assertEquals(deleteSuccessMessage, "Your report has been successfully removed.",
                "Delete success message should confirm successful removal");
        test.log(Status.PASS, "✓ Verified: Correct delete success message displayed");

        // ✅ TEST CASE 2 - STEP 19: Click OK button after delete success
        test.log(Status.INFO, "Step 19: Clicking OK button");
        dataBankPage.clickOkButton();
        test.log(Status.PASS, "✓ Step 19: OK button clicked");

        test.log(Status.PASS, "Data Bank Test Case 2 completed successfully");
    }

    /**
     * ==================== DATA BANK TEST CASE 3 ====================
     * 
     * Common Steps (1, 2 & 3):
     * 1. Verify DAILY PRIORITY heading on home page
     * 2. Click Wellbeing Dashboard
     * 3. Click DATA BANK and verify it's displayed
     * 
     * Test-Specific Steps:
     * TODO: Add your specific test steps here
     */
    @Test(priority = 3)
    public void testDataBank_Case3() throws InterruptedException {
        test = extent.createTest("Data Bank Test Case 3");
        test.log(Status.INFO, "Starting Data Bank Test Case 3");

        HomePage homePage = new HomePage(driver);
        DataBankPage dataBankPage = new DataBankPage(driver);

        // ✅ COMMON STEP 1: Verify DAILY PRIORITY heading is displayed on home page
        test.log(Status.INFO, "Step 1: Verifying DAILY PRIORITY heading on home page");
        boolean isHomePageDisplayed = homePage.isHomePageDisplayed();
        if (!isHomePageDisplayed) {
            test.log(Status.FAIL, "DAILY PRIORITY heading not found on home page");
            Assert.fail("Home page validation failed - DAILY PRIORITY heading not displayed");
        }
        test.log(Status.PASS, "✓ Step 1: DAILY PRIORITY heading is displayed on home page");

        // ✅ COMMON STEP 2: Click Wellbeing Dashboard (if not already there)
        test.log(Status.INFO, "Step 2: Clicking Wellbeing Dashboard");
        try {
            homePage.clickWellbeingDashboard();
            test.log(Status.PASS, "✓ Step 2: Wellbeing Dashboard clicked");
        } catch (Exception e) {
            test.log(Status.INFO, "Wellbeing Dashboard not found, assuming already on dashboard");
        }

        // ✅ COMMON STEP 3: Click DATA BANK and verify
        test.log(Status.INFO, "Step 3: Clicking DATA BANK");
        dataBankPage.clickAndVerifyDataBank();
        test.log(Status.PASS, "✓ Step 3: DATA BANK clicked and verified");

        // Verify Data Bank page is displayed
        Assert.assertTrue(dataBankPage.isDataBankPageDisplayed(),
                "Data Bank page should be displayed");
        test.log(Status.PASS, "✓ Verified: Data Bank page is displayed");

        // ✅ TEST CASE 3 - STEP 4: Click DEVICES
        test.log(Status.INFO, "Step 4: Clicking DEVICES");
        dataBankPage.clickDevices();
        test.log(Status.PASS, "✓ Step 4: DEVICES clicked");
        Thread.sleep(2000); // Wait for UI to update after clicking DEVICES

        // ✅ TEST CASE 3 - STEP 5: Click LINK DEVICE button
        test.log(Status.INFO, "Step 5: Clicking LINK DEVICE button");
        dataBankPage.clickLinkDeviceButton();
        test.log(Status.PASS, "✓ Step 5: LINK DEVICE button clicked");

        // ✅ TEST CASE 3 - STEP 6: Click checkbox
        test.log(Status.INFO, "Step 6: Clicking checkbox");
        dataBankPage.clickCheckbox();
        test.log(Status.PASS, "✓ Step 6: Checkbox clicked");

        // ✅ TEST CASE 3 - STEP 7: Click CONTINUE button
        test.log(Status.INFO, "Step 7: Clicking CONTINUE button");
        dataBankPage.clickContinueButton();
        test.log(Status.PASS, "✓ Step 7: CONTINUE button clicked");

        // ✅ TEST CASE 3 - STEP 8: Verify CONNECT WITH ULTRAHUMAN dialog
        test.log(Status.INFO, "Step 8: Verifying CONNECT WITH ULTRAHUMAN dialog");
        dataBankPage.verifyConnectWithUltrahumanDialog();
        test.log(Status.PASS, "✓ Step 8: CONNECT WITH ULTRAHUMAN dialog verified");

        // ✅ TEST CASE 3 - STEP 9: Enter invalid email
        test.log(Status.INFO, "Step 9: Entering invalid email");
        dataBankPage.enterInvalidEmail("invalid.email");
        test.log(Status.PASS, "✓ Step 9: Invalid email entered");

        // ✅ TEST CASE 3 - STEP 10: Click VERIFY button
        test.log(Status.INFO, "Step 10: Clicking VERIFY button");
        dataBankPage.clickVerifyButton();
        test.log(Status.PASS, "✓ Step 10: VERIFY button clicked");

        // ✅ TEST CASE 3 - STEP 11 & 12: Validate INVALID EMAIL ID dialog and capture
        // error message
        test.log(Status.INFO, "Step 11: Validating INVALID EMAIL ID dialog");
        String errorMessage = dataBankPage.validateInvalidEmailDialog();
        test.log(Status.PASS, "✓ Step 11: INVALID EMAIL ID dialog validated");
        test.log(Status.INFO, "📋 Step 12 - Error Message: " + errorMessage);

        // Verify the error message
        Assert.assertEquals(errorMessage, "INVALID EMAIL ID",
                "Error message should indicate invalid email");
        test.log(Status.PASS, "✓ Step 12: Verified error message captured");

        // ✅ TEST CASE 3 - STEP 13: Click OK button after invalid email
        test.log(Status.INFO, "Step 13: Clicking OK button");
        dataBankPage.clickOkButton();
        test.log(Status.PASS, "✓ Step 13: OK button clicked");

        // ✅ TEST CASE 3 - STEP 14-17: Repeat steps 5, 6, 7, 8 (Link Device, Checkbox,
        // Continue, Verify Dialog)
        test.log(Status.INFO, "Step 14: Repeating flow - Click LINK DEVICE button");
        dataBankPage.clickLinkDeviceButton();
        test.log(Status.PASS, "✓ Step 14: LINK DEVICE button clicked");

        test.log(Status.INFO, "Step 15: Clicking checkbox");
        dataBankPage.clickCheckbox();
        test.log(Status.PASS, "✓ Step 15: Checkbox clicked");

        test.log(Status.INFO, "Step 16: Clicking CONTINUE button");
        dataBankPage.clickContinueButton();
        test.log(Status.PASS, "✓ Step 16: CONTINUE button clicked");

        test.log(Status.INFO, "Step 17: Verifying CONNECT WITH ULTRAHUMAN dialog");
        dataBankPage.verifyConnectWithUltrahumanDialog();
        test.log(Status.PASS, "✓ Step 17: CONNECT WITH ULTRAHUMAN dialog verified");

        // ✅ TEST CASE 3 - STEP 18: Enter valid email
        test.log(Status.INFO, "Step 18: Entering valid email");
        dataBankPage.enterValidEmail("kathirmskgt@gmail.com");
        test.log(Status.PASS, "✓ Step 18: Valid email entered (kathirmskgt@gmail.com)");

        // ✅ TEST CASE 3 - STEP 19: Click VERIFY button
        test.log(Status.INFO, "Step 19: Clicking VERIFY button");
        dataBankPage.clickVerifyButton();
        test.log(Status.PASS, "✓ Step 19: VERIFY button clicked");

        // ✅ TEST CASE 3 - STEP 20 & 21: Validate DEVICE LINKED dialog and capture
        // success message
        test.log(Status.INFO, "Step 20: Validating DEVICE LINKED dialog");
        String successMessage = dataBankPage.validateDeviceLinkedDialog();
        test.log(Status.PASS, "✓ Step 20: DEVICE LINKED dialog validated");
        test.log(Status.INFO, "📋 Step 21 - Success Message: " + successMessage);

        // Verify the success message
        Assert.assertEquals(successMessage, "Your device has been successfully linked.",
                "Success message should confirm device linking");
        test.log(Status.PASS, "✓ Step 21: Verified success message captured");

        // ✅ TEST CASE 3 - STEP 22: Click OK button after successful device linking
        test.log(Status.INFO, "Step 22: Clicking OK button");
        dataBankPage.clickOkButton();
        test.log(Status.PASS, "✓ Step 22: OK button clicked");

        // ✅ Navigate back to home page for next test
        test.log(Status.INFO, "Step 23: Navigating back to home page");
        dataBankPage.clickBackButtonTwice();
        test.log(Status.PASS, "✓ Step 23: Navigated back to home page (clicked back button twice)");

        test.log(Status.PASS, "Data Bank Test Case 3 completed successfully");
    }

    /**
     * ==================== DATA BANK TEST CASE 4 ====================
     * 
     * Common Steps (1, 2 & 3):
     * 1. Verify DAILY PRIORITY heading on home page
     * 2. Click Wellbeing Dashboard
     * 3. Click DATA BANK and verify it's displayed
     * 
     * Test-Specific Steps:
     * TODO: Add your specific test steps here
     */
    @Test(priority = 4)
    public void testDataBank_Case4() throws InterruptedException {
        test = extent.createTest("Data Bank Test Case 4");
        test.log(Status.INFO, "Starting Data Bank Test Case 4");

        HomePage homePage = new HomePage(driver);
        DataBankPage dataBankPage = new DataBankPage(driver);

        // ✅ COMMON STEP 1: Verify DAILY PRIORITY heading is displayed on home page
        test.log(Status.INFO, "Step 1: Verifying DAILY PRIORITY heading on home page");
        boolean isHomePageDisplayed = homePage.isHomePageDisplayed();
        if (!isHomePageDisplayed) {
            test.log(Status.INFO,
                    "DAILY PRIORITY heading not found - app is already on Data Bank page from previous test, continuing...");
        } else {
            test.log(Status.PASS, "✓ Step 1: DAILY PRIORITY heading is displayed on home page");
        }

        // ✅ COMMON STEP 2: Click Wellbeing Dashboard (if not already there)
        test.log(Status.INFO, "Step 2: Clicking Wellbeing Dashboard");
        try {
            homePage.clickWellbeingDashboard();
            test.log(Status.PASS, "✓ Step 2: Wellbeing Dashboard clicked");
        } catch (Exception e) {
            test.log(Status.INFO, "Wellbeing Dashboard not found, assuming already on dashboard");
        }

        // ✅ COMMON STEP 3: Click DATA BANK and verify (or verify if already there)
        test.log(Status.INFO, "Step 3: Checking if DATA BANK page is displayed");

        // Check if we're already on the Data Bank page (by looking for REPORTS tab)
        if (dataBankPage.isAlreadyOnDataBankPage()) {
            test.log(Status.INFO, "Already on DATA BANK page from previous test, skipping click");
            test.log(Status.PASS, "✓ Step 3: DATA BANK page is already displayed");
        } else {
            // Not on Data Bank page, need to click it
            test.log(Status.INFO, "Step 3: Clicking DATA BANK");
            dataBankPage.clickAndVerifyDataBank();
            test.log(Status.PASS, "✓ Step 3: DATA BANK clicked and verified");
        }

        // Verify Data Bank page is displayed
        Assert.assertTrue(dataBankPage.isDataBankPageDisplayed(),
                "Data Bank page should be displayed");
        test.log(Status.PASS, "✓ Verified: Data Bank page is displayed");

        // ✅ TEST CASE 4 - STEP 4: Click REPORTS
        test.log(Status.INFO, "Step 4: Clicking REPORTS");
        dataBankPage.clickReports();
        test.log(Status.PASS, "✓ Step 4: REPORTS clicked");

        // ✅ TEST CASE 4 - STEP 5: Click BLOOD REPORT and verify page
        test.log(Status.INFO, "Step 5: Clicking BLOOD REPORT and verifying page");
        dataBankPage.clickAndVerifyBloodReport();
        test.log(Status.PASS, "✓ Step 5: BLOOD REPORT clicked and page verified");

        // ✅ TEST CASE 4 - STEP 6: Click back button once
        test.log(Status.INFO, "Step 6: Clicking back button once");
        dataBankPage.clickBackButton();
        test.log(Status.PASS, "✓ Step 6: Back button clicked once");

        // ✅ TEST CASE 4 - STEP 7: Click REPORTS again
        test.log(Status.INFO, "Step 7: Clicking REPORTS");
        dataBankPage.clickReports();
        test.log(Status.PASS, "✓ Step 7: REPORTS clicked");

        // ✅ TEST CASE 4 - STEP 8: Click DNA REPORT and verify page
        test.log(Status.INFO, "Step 8: Clicking DNA REPORT and verifying page");
        dataBankPage.clickAndVerifyDnaReport();
        test.log(Status.PASS, "✓ Step 8: DNA REPORT clicked and page verified");

        // ✅ TEST CASE 4 - STEP 9: Click EPIGENETIC MAPPING button and verify PACKAGES
        // & PRICING page
        test.log(Status.INFO, "Step 9: Clicking EPIGENETIC MAPPING button and verifying PACKAGES & PRICING page");
        dataBankPage.clickEpigeneticMappingAndVerifyPackagesPage();
        test.log(Status.PASS, "✓ Step 9: EPIGENETIC MAPPING clicked and PACKAGES & PRICING page verified");

        // ✅ TEST CASE 4 - STEP 10: Click back button twice
        test.log(Status.INFO, "Step 10: Clicking back button twice");
        dataBankPage.clickBackButtonTwice();
        test.log(Status.PASS, "✓ Step 10: Back button clicked twice");

        // ✅ TEST CASE 4 - STEP 11: Click REPORTS again
        test.log(Status.INFO, "Step 11: Clicking REPORTS");
        dataBankPage.clickReports();
        test.log(Status.PASS, "✓ Step 11: REPORTS clicked");

        // ✅ TEST CASE 4 - STEP 12: Click DEVICE REPORT and verify page
        test.log(Status.INFO, "Step 12: Clicking DEVICE REPORT and verifying page");
        dataBankPage.clickAndVerifyDeviceReport();
        test.log(Status.PASS, "✓ Step 12: DEVICE REPORT clicked and page verified");

        // ✅ TEST CASE 4 - STEP 13: Click back button twice
        test.log(Status.INFO, "Step 13: Clicking back button twice");
        dataBankPage.clickBackButtonTwice();
        test.log(Status.PASS, "✓ Step 13: Back button clicked twice");

        test.log(Status.PASS, "Data Bank Test Case 4 completed successfully");
    }
}
