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

        // TODO: Add your test-specific steps for Test Case 3 here
        test.log(Status.INFO, "TODO: Implement test-specific steps for Case 3");

        test.log(Status.PASS, "Data Bank Test Case 3 completed");
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

        // TODO: Add your test-specific steps for Test Case 4 here
        test.log(Status.INFO, "TODO: Implement test-specific steps for Case 4");

        test.log(Status.PASS, "Data Bank Test Case 4 completed");
    }
}
