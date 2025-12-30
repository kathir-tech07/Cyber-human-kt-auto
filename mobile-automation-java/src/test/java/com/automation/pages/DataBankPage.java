package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DataBankPage {
    private AppiumDriver driver;
    private WebDriverWait wait;

    public DataBankPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ==================== LOCATORS ====================

    // Step 3 - Click and verify DATA BANK
    private final String dataBankButtonXpath = "//android.view.View[@content-desc=\"DATA BANK\"]";
    private final String dataBankHeadingXpath = "//android.view.View[@content-desc=\"DATA BANK\"]";

    // Test Case 1 - Specific Steps
    private final String packagesAndPricingXpath = "//android.view.View[@content-desc=\"PACKAGES & PRICING\"]";
    private final String continueButtonXpath = "//android.widget.Button[@content-desc=\"CONTINUE\"]";
    private final String checkboxXpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[1]";

    // Test Case 2 - Specific Steps
    private final String uploadDataXpath = "//android.view.View[@content-desc=\"UPLOAD DATA\"]";
    private final String uploadReportButtonXpath = "//android.widget.Button[@content-desc=\"UPLOAD REPORT\"]";
    private final String uploadFailedDialogXpath = "//android.view.View[@content-desc=\"UPLOAD FAILED\"]";
    private final String uploadFailedMessageXpath = "//android.view.View[@content-desc=\"Wrong file type chosen. Only allowed type is pdf.\"]";
    private final String okButtonXpath = "//android.widget.Button[@content-desc=\"OK\"]";
    private final String uploadSuccessfulDialogXpath = "//android.view.View[@content-desc=\"UPLOAD SUCCESSFUL\"]";
    private final String uploadSuccessMessageXpath = "//android.view.View[@content-desc=\"Your document has been successfully uploaded\"]";
    private final String removeButtonXpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.ImageView[2]";
    private final String areYouSureDialogXpath = "//android.view.View[@content-desc=\"ARE YOU SURE?\"]";
    private final String yesButtonXpath = "//android.widget.Button[@content-desc=\"YES\"]";
    private final String deleteSuccessfulDialogXpath = "//android.view.View[@content-desc=\"DELETE SUCCESSFUL\"]";
    private final String deleteSuccessMessageXpath = "//android.view.View[@content-desc=\"Your report has been successfully removed.\"]";

    // Test Case 3 - Specific Steps
    private final String devicesXpath = "//android.view.View[@content-desc=\"DEVICES\"]";
    private final String linkDeviceButtonXpath = "//android.widget.Button[@content-desc=\"LINK DEVICE\"]";
    private final String connectWithUltrahumanDialogXpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View";
    private final String emailFieldXpath = "//android.widget.EditText";
    private final String scrollViewXpath = "//android.widget.ScrollView";
    private final String verifyButtonXpath = "//android.widget.Button[@content-desc=\"VERIFY\"]";
    private final String invalidEmailDialogXpath = "//android.view.View[@content-desc=\"INVALID EMAIL ID\"]";
    private final String deviceLinkedDialogXpath = "//android.view.View[@content-desc=\"DEVICE LINKED\"]";
    private final String deviceLinkedMessageXpath = "//android.view.View[@content-desc=\"Your device has been successfully linked.\"]";

    // Test Case 4 - Specific Steps
    private final String reportsXpath = "//android.view.View[@content-desc=\"REPORTS\"]";
    private final String bloodReportXpath = "//android.view.View[@content-desc=\"BLOOD REPORT\"]";
    private final String dnaReportXpath = "//android.view.View[@content-desc=\"DNA REPORT\"]";
    private final String deviceReportXpath = "//android.view.View[@content-desc=\"DEVICE REPORT\"]";
    private final String uploadReportButtonCase4Xpath = "//android.widget.Button[@content-desc=\"UPLOAD REPORT\"]";
    private final String uploadDataPageXpath = "//android.view.View[@content-desc=\"UPLOAD DATA\"]";
    private final String epigeneticMappingButtonXpath = "//android.widget.Button[@content-desc=\"EPIGENETIC MAPPING\"]";
    private final String packagesAndPricingPageXpath = "//android.view.View[@content-desc=\"PACKAGES & PRICING\"]";
    private final String backButtonXpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView";

    // ==================== COMMON STEP 3 ====================

    /**
     * COMMON STEP 3 FOR ALL 4 TEST CASES:
     * Click "DATA BANK" and verify it's displayed
     */
    public void clickAndVerifyDataBank() {
        try {
            // Click DATA BANK button
            WebElement dataBankButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(dataBankButtonXpath)));
            dataBankButton.click();
            System.out.println("✓ Step 3: Clicked 'DATA BANK' button");

            // Verify DATA BANK heading is displayed
            WebElement dataBankHeading = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(dataBankHeadingXpath)));

            if (dataBankHeading.isDisplayed()) {
                System.out.println("✓ Step 3: Verified 'DATA BANK' heading is displayed");
            } else {
                throw new RuntimeException("DATA BANK heading is not displayed");
            }

        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to navigate to/verify Data Bank in Step 3", e);
        }
    }

    // ==================== TEST CASE 1 SPECIFIC METHODS ====================

    /**
     * TEST CASE 1 - STEP 4:
     * Click "PACKAGES & PRICING"
     */
    public void clickPackagesAndPricing() {
        try {
            WebElement packagesAndPricing = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(packagesAndPricingXpath)));
            packagesAndPricing.click();
            System.out.println("✓ Step 4: Clicked 'PACKAGES & PRICING'");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click PACKAGES & PRICING in Step 4", e);
        }
    }

    /**
     * TEST CASE 1 - STEP 5 & 7:
     * Click "CONTINUE" button
     */
    public void clickContinueButton() {
        try {
            WebElement continueButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(continueButtonXpath)));
            continueButton.click();
            System.out.println("✓ Clicked 'CONTINUE' button");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click CONTINUE button", e);
        }
    }

    /**
     * TEST CASE 1 - STEP 6:
     * Click the checkbox
     */
    public void clickCheckbox() {
        try {
            WebElement checkbox = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(checkboxXpath)));
            checkbox.click();
            System.out.println("✓ Step 6: Clicked checkbox");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click checkbox in Step 6", e);
        }
    }

    // ==================== TEST CASE 2 SPECIFIC METHODS ====================

    /**
     * TEST CASE 2 - STEP 4:
     * Click "UPLOAD DATA"
     */
    public void clickUploadData() {
        try {
            WebElement uploadData = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(uploadDataXpath)));
            uploadData.click();
            System.out.println("✓ Step 4: Clicked 'UPLOAD DATA'");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click UPLOAD DATA in Step 4", e);
        }
    }

    /**
     * TEST CASE 2 - STEP 5:
     * Click "UPLOAD REPORT" button
     */
    public void clickUploadReportButton() {
        try {
            WebElement uploadReportButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(uploadReportButtonXpath)));
            uploadReportButton.click();
            System.out.println("✓ Step 5: Clicked 'UPLOAD REPORT' button");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click UPLOAD REPORT button in Step 5", e);
        }
    }

    /**
     * TEST CASE 2 - STEP 8:
     * Upload wrong format file (JPG instead of PDF)
     * Uses Android file picker to select the file
     */
    public void uploadWrongFormatFile() {
        try {
            // Wait for file picker to open
            Thread.sleep(3000);

            System.out.println("✓ Step 8: Navigating Android file picker to select JPG file");

            // Click on the menu/hamburger icon to show storage options (if needed)
            try {
                WebElement menuButton = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//android.widget.ImageButton[@content-desc='Show roots']")));
                menuButton.click();
                Thread.sleep(1000);
                System.out.println("  - Opened storage menu");
            } catch (Exception e) {
                System.out.println("  - Storage menu already open or not needed");
            }

            // Select "Internal storage" or device name
            try {
                WebElement internalStorage = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                        "//android.widget.TextView[contains(@text, 'Internal') or contains(@text, '24116RNC1I')]")));
                internalStorage.click();
                Thread.sleep(1000);
                System.out.println("  - Selected Internal storage");
            } catch (Exception e) {
                System.out.println("  - Already in Internal storage");
            }

            // Navigate to Download folder
            WebElement downloadFolder = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@text='Download']")));
            downloadFolder.click();
            Thread.sleep(1000);
            System.out.println("  - Opened Download folder");

            // Click Download folder again to navigate deeper
            WebElement downloadFolderAgain = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@text='Download']")));
            downloadFolderAgain.click();
            Thread.sleep(1000);
            System.out.println("  - Opened Download folder again");

            // Select the JPEG file
            WebElement jpegFile = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                    "//android.widget.TextView[contains(@text, 'images.jpeg') or @text='images.jpeg']")));
            jpegFile.click();
            Thread.sleep(1000);
            System.out.println(
                    "✓ Step 8: Selected JPEG file - images.jpeg from Download folder");

        } catch (InterruptedException e) {
            throw new RuntimeException("Thread interrupted during file upload in Step 8", e);
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to navigate file picker or select file in Step 8", e);
        }
    }

    /**
     * TEST CASE 2 - STEP 9:
     * Validate UPLOAD FAILED dialog is shown and capture error message
     * 
     * @return The error message displayed in the dialog
     */
    public String validateUploadFailedDialog() {
        try {
            // Verify UPLOAD FAILED dialog is displayed
            WebElement uploadFailedDialog = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(uploadFailedDialogXpath)));

            if (uploadFailedDialog.isDisplayed()) {
                System.out.println("✓ Step 9: UPLOAD FAILED dialog is displayed");
            } else {
                throw new RuntimeException("UPLOAD FAILED dialog is not displayed");
            }

            // Capture the error message
            WebElement errorMessage = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(uploadFailedMessageXpath)));

            String message = errorMessage.getAttribute("content-desc");
            System.out.println("✓ Step 9: Error message captured: " + message);
            return message;

        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to validate UPLOAD FAILED dialog in Step 9", e);
        }
    }

    /**
     * TEST CASE 2 - STEP 10 & 15:
     * Click OK button (used after both failed and successful upload dialogs)
     */
    public void clickOkButton() {
        try {
            WebElement okButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(okButtonXpath)));
            okButton.click();
            System.out.println("✓ Clicked 'OK' button");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click OK button", e);
        }
    }

    /**
     * TEST CASE 2 - STEP 12:
     * Upload correct format file (PDF)
     * Navigates through file picker to select PM0140.pdf from Download folder
     */
    public void uploadCorrectFormatFile() {
        try {
            // Wait for file picker to open
            Thread.sleep(3000);

            System.out.println("✓ Step 12: Navigating Android file picker to select PDF file");

            // Click on the menu/hamburger icon to show storage options (if needed)
            try {
                WebElement menuButton = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//android.widget.ImageButton[@content-desc='Show roots']")));
                menuButton.click();
                Thread.sleep(1000);
                System.out.println("  - Opened storage menu");
            } catch (Exception e) {
                System.out.println("  - Storage menu already open or not needed");
            }

            // Select "Internal storage" or device name
            try {
                WebElement internalStorage = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                        "//android.widget.TextView[contains(@text, 'Internal') or contains(@text, '24116RNC1I')]")));
                internalStorage.click();
                Thread.sleep(1000);
                System.out.println("  - Selected Internal storage");
            } catch (Exception e) {
                System.out.println("  - Already in Internal storage");
            }

            // Strategy 1: Try to navigate via Document → Download path
            boolean foundViaDocumentPath = false;
            try {
                WebElement documentFolder = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                        "//android.widget.TextView[contains(@text, 'Documents') or @text='Documents']")));
                documentFolder.click();
                Thread.sleep(1000);
                System.out.println("  - Opened Document folder");

                // Navigate to Download subfolder inside Document
                WebElement downloadFolder = wait.until(
                        ExpectedConditions
                                .elementToBeClickable(By.xpath("//android.widget.TextView[@text='Download']")));
                downloadFolder.click();
                Thread.sleep(1000);
                System.out.println("  - Opened Download subfolder");
                foundViaDocumentPath = true;

            } catch (TimeoutException e) {
                System.out.println("  - Document folder path not available, trying alternative...");
            }

            // Strategy 2: If Document path failed, try Download folder directly (like JPEG
            // upload)
            if (!foundViaDocumentPath) {
                try {
                    WebElement downloadFolder = wait.until(
                            ExpectedConditions
                                    .elementToBeClickable(By.xpath("//android.widget.TextView[@text='Download']")));
                    downloadFolder.click();
                    Thread.sleep(1000);
                    System.out.println("  - Opened Download folder directly");

                    // Try clicking Download again (nested structure)
                    try {
                        WebElement downloadFolderAgain = wait.until(
                                ExpectedConditions
                                        .elementToBeClickable(By.xpath("//android.widget.TextView[@text='Download']")));
                        downloadFolderAgain.click();
                        Thread.sleep(1000);
                        System.out.println("  - Opened Download folder again");
                    } catch (Exception nested) {
                        System.out.println("  - Single Download folder (no nesting)");
                    }
                } catch (TimeoutException e2) {
                    System.out.println("  - Download folder also not found, will try to find PDF directly");
                }
            }

            // Select the PDF file
            WebElement pdfFile = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                    "//android.widget.TextView[contains(@text, 'PM0140.pdf') or @text='PM0140.pdf']")));
            pdfFile.click();
            Thread.sleep(1000);
            System.out.println("✓ Step 12: Selected PDF file - PM0140.pdf from Document/Download folder");

        } catch (InterruptedException e) {
            throw new RuntimeException("Thread interrupted during PDF file upload in Step 12", e);
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to navigate file picker or select PDF file in Step 12", e);
        }
    }

    /**
     * TEST CASE 2 - STEP 13 & 14:
     * Validate UPLOAD SUCCESSFUL dialog is shown and capture success message
     * 
     * @return The success message displayed in the dialog
     */
    public String validateUploadSuccessfulDialog() {
        try {
            // Verify UPLOAD SUCCESSFUL dialog is displayed
            WebElement uploadSuccessDialog = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(uploadSuccessfulDialogXpath)));

            if (uploadSuccessDialog.isDisplayed()) {
                System.out.println("✓ Step 13: UPLOAD SUCCESSFUL dialog is displayed");
            } else {
                throw new RuntimeException("UPLOAD SUCCESSFUL dialog is not displayed");
            }

            // Capture the success message
            WebElement successMessage = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(uploadSuccessMessageXpath)));

            String message = successMessage.getAttribute("content-desc");
            System.out.println("✓ Step 14: Success message captured: " + message);
            return message;

        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to validate UPLOAD SUCCESSFUL dialog in Step 13/14", e);
        }
    }

    /**
     * TEST CASE 2 - STEP 16:
     * Click remove button and verify "ARE YOU SURE?" dialog is displayed
     */
    public void clickRemoveAndVerifyAreYouSureDialog() {
        try {
            // Click remove button
            WebElement removeButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(removeButtonXpath)));
            removeButton.click();
            System.out.println("✓ Step 16: Clicked remove button");

            // Verify ARE YOU SURE? dialog is displayed
            WebElement areYouSureDialog = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(areYouSureDialogXpath)));

            if (areYouSureDialog.isDisplayed()) {
                System.out.println("✓ Step 16: ARE YOU SURE? dialog is displayed");
            } else {
                throw new RuntimeException("ARE YOU SURE? dialog is not displayed");
            }

        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click remove button or verify ARE YOU SURE? dialog in Step 16", e);
        }
    }

    /**
     * TEST CASE 2 - STEP 17:
     * Click YES button and verify DELETE SUCCESSFUL dialog is displayed
     */
    public void clickYesAndVerifyDeleteSuccessfulDialog() {
        try {
            // Click YES button
            WebElement yesButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(yesButtonXpath)));
            yesButton.click();
            System.out.println("✓ Step 17: Clicked YES button");

            // Verify DELETE SUCCESSFUL dialog is displayed
            WebElement deleteSuccessDialog = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(deleteSuccessfulDialogXpath)));

            if (deleteSuccessDialog.isDisplayed()) {
                System.out.println("✓ Step 17: DELETE SUCCESSFUL dialog is displayed");
            } else {
                throw new RuntimeException("DELETE SUCCESSFUL dialog is not displayed");
            }

        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click YES button or verify DELETE SUCCESSFUL dialog in Step 17", e);
        }
    }

    /**
     * TEST CASE 2 - STEP 18:
     * Capture the delete success message at runtime
     * 
     * @return The delete success message displayed in the dialog
     */
    public String captureDeleteSuccessMessage() {
        try {
            // Capture the success message
            WebElement successMessage = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(deleteSuccessMessageXpath)));

            String message = successMessage.getAttribute("content-desc");
            System.out.println("✓ Step 18: Delete success message captured: " + message);
            return message;

        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to capture delete success message in Step 18", e);
        }
    }

    // ==================== TEST CASE 3 SPECIFIC METHODS ====================

    /**
     * TEST CASE 3 - STEP 4:
     * Click "DEVICES"
     */
    public void clickDevices() {
        try {
            WebElement devices = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(devicesXpath)));
            devices.click();
            System.out.println("✓ Step 4: Clicked 'DEVICES'");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click DEVICES in Step 4", e);
        }
    }

    /**
     * TEST CASE 3 - STEP 5 (also used in Step 14):
     * Click "LINK DEVICE" button
     */
    public void clickLinkDeviceButton() {
        try {
            WebElement linkDeviceButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(linkDeviceButtonXpath)));
            linkDeviceButton.click();
            System.out.println("✓ Clicked 'LINK DEVICE' button");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click LINK DEVICE button", e);
        }
    }

    /**
     * TEST CASE 3 - STEP 8:
     * Verify "CONNECT WITH ULTRAHUMAN" dialog is displayed
     */
    public void verifyConnectWithUltrahumanDialog() {
        try {
            WebElement dialog = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(connectWithUltrahumanDialogXpath)));

            if (dialog.isDisplayed()) {
                System.out.println("✓ Step 8: CONNECT WITH ULTRAHUMAN dialog is displayed");
            } else {
                throw new RuntimeException("CONNECT WITH ULTRAHUMAN dialog is not displayed");
            }
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to verify CONNECT WITH ULTRAHUMAN dialog in Step 8", e);
        }
    }

    /**
     * TEST CASE 3 - STEP 9:
     * Enter invalid email in the email field
     * 
     * @param email The invalid email to enter
     */
    public void enterInvalidEmail(String email) {
        try {
            // Click, clear, and send keys to email field
            WebElement emailField = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(emailFieldXpath)));
            emailField.click();
            emailField.clear();
            emailField.sendKeys(email);
            System.out.println("✓ Step 9: Entered invalid email: " + email);

            // Click on ScrollView to dismiss keyboard
            WebElement scrollView = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(scrollViewXpath)));
            scrollView.click();
            System.out.println("✓ Step 9: Clicked ScrollView");

        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to enter invalid email in Step 9", e);
        }
    }

    /**
     * TEST CASE 3 - STEP 18:
     * Enter valid email in the email field
     * 
     * @param email The valid email to enter
     */
    public void enterValidEmail(String email) {
        try {
            // Click, clear, and send keys to email field
            WebElement emailField = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(emailFieldXpath)));
            emailField.click();
            emailField.clear();
            emailField.sendKeys(email);
            System.out.println("✓ Step 18: Entered valid email: " + email);

            // Click on ScrollView to dismiss keyboard
            WebElement scrollView = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(scrollViewXpath)));
            scrollView.click();
            System.out.println("✓ Step 18: Clicked ScrollView");

        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to enter valid email in Step 18", e);
        }
    }

    /**
     * TEST CASE 3 - STEP 10 & 19:
     * Click "VERIFY" button
     */
    public void clickVerifyButton() {
        try {
            WebElement verifyButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(verifyButtonXpath)));
            verifyButton.click();
            System.out.println("✓ Clicked 'VERIFY' button");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click VERIFY button", e);
        }
    }

    /**
     * TEST CASE 3 - STEP 11 & 12:
     * Validate INVALID EMAIL ID dialog is shown and capture error message
     * 
     * @return The error message displayed in the dialog
     */
    public String validateInvalidEmailDialog() {
        try {
            // Verify INVALID EMAIL ID dialog is displayed
            WebElement invalidEmailDialog = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(invalidEmailDialogXpath)));

            if (invalidEmailDialog.isDisplayed()) {
                System.out.println("✓ Step 11: INVALID EMAIL ID dialog is displayed");
            } else {
                throw new RuntimeException("INVALID EMAIL ID dialog is not displayed");
            }

            // Capture the error message (the dialog itself contains the message)
            String message = invalidEmailDialog.getAttribute("content-desc");
            System.out.println("✓ Step 12: Error message captured: " + message);
            return message;

        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to validate INVALID EMAIL ID dialog in Step 11/12", e);
        }
    }

    /**
     * TEST CASE 3 - STEP 20 & 21:
     * Validate DEVICE LINKED dialog is shown and capture success message
     * 
     * @return The success message displayed in the dialog
     */
    public String validateDeviceLinkedDialog() {
        try {
            // Verify DEVICE LINKED dialog is displayed
            WebElement deviceLinkedDialog = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(deviceLinkedDialogXpath)));

            if (deviceLinkedDialog.isDisplayed()) {
                System.out.println("✓ Step 20: DEVICE LINKED dialog is displayed");
            } else {
                throw new RuntimeException("DEVICE LINKED dialog is not displayed");
            }

            // Capture the success message
            WebElement successMessage = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(deviceLinkedMessageXpath)));

            String message = successMessage.getAttribute("content-desc");
            System.out.println("✓ Step 21: Success message captured: " + message);
            return message;

        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to validate DEVICE LINKED dialog in Step 20/21", e);
        }
    }

    // ==================== TEST CASE 4 SPECIFIC METHODS ====================

    /**
     * TEST CASE 4 - STEP 4, 8, 12:
     * Click "REPORTS"
     */
    public void clickReports() {
        try {
            WebElement reports = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(reportsXpath)));
            reports.click();
            System.out.println("✓ Clicked 'REPORTS'");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click REPORTS", e);
        }
    }

    /**
     * TEST CASE 4 - STEP 5:
     * Click "BLOOD REPORT" and verify page is displayed
     */
    public void clickAndVerifyBloodReport() {
        try {
            // Click BLOOD REPORT
            WebElement bloodReport = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(bloodReportXpath)));
            bloodReport.click();
            System.out.println("✓ Step 5: Clicked 'BLOOD REPORT'");

            // Verify BLOOD REPORT page is displayed
            WebElement bloodReportPage = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(bloodReportXpath)));

            if (bloodReportPage.isDisplayed()) {
                System.out.println("✓ Step 5: Verified 'BLOOD REPORT' page is displayed");
            } else {
                throw new RuntimeException("BLOOD REPORT page is not displayed");
            }
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click/verify BLOOD REPORT in Step 5", e);
        }
    }

    /**
     * TEST CASE 4 - STEP 9:
     * Click "DNA REPORT" and verify page is displayed
     */
    public void clickAndVerifyDnaReport() {
        try {
            // Click DNA REPORT
            WebElement dnaReport = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(dnaReportXpath)));
            dnaReport.click();
            System.out.println("✓ Step 9: Clicked 'DNA REPORT'");

            // Verify DNA REPORT page is displayed
            WebElement dnaReportPage = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(dnaReportXpath)));

            if (dnaReportPage.isDisplayed()) {
                System.out.println("✓ Step 9: Verified 'DNA REPORT' page is displayed");
            } else {
                throw new RuntimeException("DNA REPORT page is not displayed");
            }
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click/verify DNA REPORT in Step 9", e);
        }
    }

    /**
     * TEST CASE 4 - STEP 13:
     * Click "DEVICE REPORT" and verify page is displayed
     */
    public void clickAndVerifyDeviceReport() {
        try {
            // Click DEVICE REPORT
            WebElement deviceReport = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(deviceReportXpath)));
            deviceReport.click();
            System.out.println("✓ Step 13: Clicked 'DEVICE REPORT'");

            // Verify DEVICE REPORT page is displayed
            WebElement deviceReportPage = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(deviceReportXpath)));

            if (deviceReportPage.isDisplayed()) {
                System.out.println("✓ Step 13: Verified 'DEVICE REPORT' page is displayed");
            } else {
                throw new RuntimeException("DEVICE REPORT page is not displayed");
            }
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click/verify DEVICE REPORT in Step 13", e);
        }
    }

    /**
     * TEST CASE 4 - STEP 6:
     * Click "UPLOAD REPORT" button and verify UPLOAD DATA page is displayed
     */
    public void clickUploadReportAndVerifyUploadDataPage() {
        try {
            // Click UPLOAD REPORT button
            WebElement uploadReportButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(uploadReportButtonCase4Xpath)));
            uploadReportButton.click();
            System.out.println("✓ Step 6: Clicked 'UPLOAD REPORT' button");

            // Verify UPLOAD DATA page is displayed
            WebElement uploadDataPage = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(uploadDataPageXpath)));

            if (uploadDataPage.isDisplayed()) {
                System.out.println("✓ Step 6: Verified 'UPLOAD DATA' page is displayed");
            } else {
                throw new RuntimeException("UPLOAD DATA page is not displayed");
            }
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click UPLOAD REPORT or verify UPLOAD DATA page in Step 6", e);
        }
    }

    /**
     * TEST CASE 4 - STEP 10:
     * Click "EPIGENETIC MAPPING" button and verify PACKAGES & PRICING page is
     * displayed
     */
    public void clickEpigeneticMappingAndVerifyPackagesPage() {
        try {
            // Click EPIGENETIC MAPPING button
            WebElement epigeneticMappingButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(epigeneticMappingButtonXpath)));
            epigeneticMappingButton.click();
            System.out.println("✓ Step 10: Clicked 'EPIGENETIC MAPPING' button");

            // Verify PACKAGES & PRICING page is displayed
            WebElement packagesPage = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(packagesAndPricingPageXpath)));

            if (packagesPage.isDisplayed()) {
                System.out.println("✓ Step 10: Verified 'PACKAGES & PRICING' page is displayed");
            } else {
                throw new RuntimeException("PACKAGES & PRICING page is not displayed");
            }
        } catch (TimeoutException e) {
            throw new RuntimeException(
                    "Failed to click EPIGENETIC MAPPING or verify PACKAGES & PRICING page in Step 10", e);
        }
    }

    /**
     * TEST CASE 4 - STEP 7, 11, 14:
     * Click back button (can be called multiple times)
     */
    public void clickBackButton() {
        try {
            WebElement backButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(backButtonXpath)));
            backButton.click();
            System.out.println("✓ Clicked back button");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click back button", e);
        }
    }

    /**
     * TEST CASE 4 - STEP 7, 11, 14:
     * Click back button twice
     */
    public void clickBackButtonTwice() {
        try {
            // First click
            WebElement backButton1 = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(backButtonXpath)));
            backButton1.click();
            System.out.println("✓ Clicked back button (1st time)");

            Thread.sleep(500); // Small delay between clicks

            // Second click
            WebElement backButton2 = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(backButtonXpath)));
            backButton2.click();
            System.out.println("✓ Clicked back button (2nd time)");

        } catch (InterruptedException e) {
            throw new RuntimeException("Thread interrupted during back button clicks", e);
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click back button twice", e);
        }
    }

    // ==================== VERIFICATION METHODS ====================

    /**
     * Verify if Data Bank page is currently displayed
     * 
     * @return true if Data Bank heading is visible, false otherwise
     */
    public boolean isDataBankPageDisplayed() {
        try {
            WebElement dataBankHeading = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(dataBankHeadingXpath)));
            return dataBankHeading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if already on Data Bank page by looking for REPORTS tab
     * This is more reliable than checking for DATA BANK heading when already inside
     * the section
     * 
     * @return true if REPORTS tab is visible (indicating we're on Data Bank page),
     *         false otherwise
     */
    public boolean isAlreadyOnDataBankPage() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            WebElement reportsTab = shortWait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(reportsXpath)));
            return reportsTab.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get the Data Bank heading text for verification
     * 
     * @return Content description of the Data Bank heading
     */
    public String getDataBankHeadingText() {
        try {
            WebElement dataBankHeading = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(dataBankHeadingXpath)));
            return dataBankHeading.getAttribute("content-desc");
        } catch (Exception e) {
            return null;
        }
    }
}