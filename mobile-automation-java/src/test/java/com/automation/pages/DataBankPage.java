package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DataBankPage {
    private WebDriverWait wait;

    public DataBankPage(AppiumDriver driver) {
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
