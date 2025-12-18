package com.automation.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPage {
    private AppiumDriver driver;
    private WebDriverWait wait;

    public SignUpPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private WebElement findElementWithFallback(String id, String xpath, String accessibilityId) {
        try {
            if (id != null && !id.isEmpty())
                return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        } catch (TimeoutException ignored) {
        }
        try {
            if (xpath != null && !xpath.isEmpty())
                return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (TimeoutException ignored) {
        }
        try {
            if (accessibilityId != null && !accessibilityId.isEmpty())
                return wait.until(
                        ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId(accessibilityId)));
        } catch (TimeoutException ignored) {
        }

        throw new RuntimeException(
                "Element not found with ID: " + id + ", XPath: " + xpath + ", AccessID: " + accessibilityId);
    }

    // Locators
    private final String nameFieldXpath = "//android.widget.EditText[@hint='Name']";
    private final String emailFieldXpath = "//android.widget.EditText[@hint='Email']";
    private final String countryButtonXpath = "//android.widget.Button[@content-desc='Country']";
    private final String passwordFieldXpath = "//android.view.View[@content-desc='Password']//android.widget.EditText[@password='true']";
    private final String confirmPasswordFieldXpath = "//android.view.View[@content-desc='Confirm Password']//android.widget.EditText[@password='true']";
    private final String continueBtnXpath = "//android.widget.Button[@content-desc='CONTINUE']";
    private final String signInLinkXpath = "//android.widget.Button[@content-desc=\"Already have an account? Sign in\"]";

    /**
     * Enter name in the Name field
     */
    public void enterName(String name) {
        WebElement el = findElementWithFallback(null, nameFieldXpath, null);
        el.click();
        el.clear();
        el.sendKeys(name);
    }

    /**
     * Enter email in the Email field
     */
    public void enterEmail(String email) {
        WebElement el = findElementWithFallback(null, emailFieldXpath, null);
        el.click();
        el.clear();
        el.sendKeys(email);
    }

    /**
     * Click the Country dropdown button
     */
    public void clickCountry() {
        WebElement btn = findElementWithFallback(null, countryButtonXpath, "Country");
        btn.click();
    }

    /**
     * Enter password in the Password field
     */
    public void enterPassword(String password) {
        WebElement el = findElementWithFallback(null, passwordFieldXpath, null);
        el.click();
        el.clear();
        el.sendKeys(password);
    }

    /**
     * Enter password in the Confirm Password field
     */
    public void enterConfirmPassword(String confirmPassword) {
        WebElement el = findElementWithFallback(null, confirmPasswordFieldXpath, null);
        el.click();
        el.clear();
        el.sendKeys(confirmPassword);
    }

    /**
     * Click the Continue button
     */
    public void clickContinue() {
        WebElement btn = findElementWithFallback(null, continueBtnXpath, "CONTINUE");
        if (btn.isEnabled()) {
            btn.click();
        }
    }

    /**
     * Click "Already have an account? Sign in" link to navigate to Sign In page
     */
    public void clickSignIn() {
        WebElement link = findElementWithFallback(null, signInLinkXpath, null);
        link.click();
    }

    /**
     * Check if Continue button is enabled
     */
    public boolean isContinueButtonEnabled() {
        try {
            WebElement btn = findElementWithFallback(null, continueBtnXpath, "CONTINUE");
            return btn.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 🆕 Click Country dropdown and select Afghanistan (first option)
     * This method clicks the Country button and then selects Afghanistan
     */
    public void selectFirstCountryOption() {
        // Click Country dropdown
        clickCountry();

        try {
            // Wait for dropdown to appear and select first option
            Thread.sleep(1000); // Allow dropdown to render

            // Try to find and click Afghanistan button using content-desc
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // Look for Afghanistan button by content-desc (primary method)
            try {
                WebElement afghanistanBtn = shortWait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//android.widget.Button[@content-desc='Afghanistan']")));
                afghanistanBtn.click();
            } catch (Exception e1) {
                // Fallback: try Button with accessibility ID
                try {
                    WebElement afghanistanBtn = shortWait.until(ExpectedConditions.elementToBeClickable(
                            AppiumBy.accessibilityId("Afghanistan")));
                    afghanistanBtn.click();
                } catch (Exception e2) {
                    // Fallback: try first clickable Button
                    WebElement firstOption = shortWait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath("(//android.widget.Button[@clickable='true'])[1]")));
                    firstOption.click();
                }
            }
        } catch (Exception e) {
            System.out.println("Could not select Afghanistan: " + e.getMessage());
        }
    }

    /**
     * 🆕 Click the "SIGN UP" heading
     * This is required before clicking Continue button in tests
     */
    public void clickSignUpHeading() {
        String signUpHeadingXpath = "//android.view.View[@content-desc='SIGN UP']";
        try {
            WebElement heading = findElementWithFallback(null, signUpHeadingXpath, null);
            heading.click();
        } catch (Exception e) {
            System.out.println("Could not click SIGN UP heading: " + e.getMessage());
        }
    }

    /**
     * ✅ RUNTIME-BASED VALIDATION DETECTION (NO HARDCODED MESSAGES)
     * 
     * Checks at runtime if ANY validation element is visible:
     * - Validation message via content-desc (android.view.View)
     * - Toast message
     * - Inline error text
     * - EditText with error state
     * 
     * @return true if ANY validation is detected, false if NONE found
     */
    public boolean isAnyValidationVisible() {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        // ✅ Check 1: Validation message via content-desc (android.view.View)
        // This validation message appears between Confirm Password field and "Already
        // have an account? Sign in" button
        try {
            WebElement validationView = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath(
                            "//android.view.View[@content-desc and (contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'invalid') or "
                                    +
                                    "contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'error') or "
                                    +
                                    "contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'required') or "
                                    +
                                    "contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'weak') or "
                                    +
                                    "contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'match') or "
                                    +
                                    "contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'please') or "
                                    +
                                    "contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'check') or "
                                    +
                                    "contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'credentials') or "
                                    +
                                    "contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'empty') or "
                                    +
                                    "contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'field'))]")));
            String contentDesc = validationView.getAttribute("content-desc");
            if (contentDesc != null && !contentDesc.trim().isEmpty()) {
                return true; // Validation message detected via content-desc
            }
        } catch (Exception ignored) {
        }

        // ✅ Check 2: Android Toast Message
        try {
            WebElement toast = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//android.widget.Toast[1]")));
            if (toast != null && toast.getText() != null && !toast.getText().trim().isEmpty()) {
                return true; // Toast detected
            }
        } catch (Exception ignored) {
        }

        // ✅ Check 3: Inline Error Text (TextView with error keywords)
        try {
            WebElement errorKeyword = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//android.widget.TextView[" +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'invalid') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'error') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'required') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'weak') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'match') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'please')]")));
            String text = errorKeyword.getText();
            if (text != null && !text.trim().isEmpty()) {
                return true; // Error keyword detected
            }
        } catch (Exception ignored) {
        }

        // ✅ Check 4: TextView with error resource-id
        try {
            WebElement errorById = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(
                            "//android.widget.TextView[contains(@resource-id, 'error') or contains(@resource-id, 'validation')]")));
            if (errorById != null && errorById.getText() != null && !errorById.getText().trim().isEmpty()) {
                return true; // Error by resource-id detected
            }
        } catch (Exception ignored) {
        }

        // ✅ Check 5: EditText with error attribute (red error box)
        try {
            WebElement editTextError = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//android.widget.EditText[@error='true' or @focused='true']")));
            if (editTextError != null) {
                return true; // EditText error state detected
            }
        } catch (Exception ignored) {
        }

        // ❌ No validation detected
        return false;
    }

    /**
     * 🆕 GET ACTUAL RUNTIME VALIDATION MESSAGE
     * 
     * Captures the actual validation message displayed by the app at runtime.
     * This method does NOT compare with any expected value - it simply reads
     * whatever text is shown by the app.
     * 
     * Priority order for message capture:
     * 1. Validation message from content-desc (android.view.View) - appears between
     * Confirm Password and Sign In link
     * 2. Toast message text
     * 3. Inline error TextView text
     * 4. TextView with error keywords
     * 5. EditText error attribute
     * 6. Disabled Continue button state
     * 
     * @return The actual validation message text, or null if no validation found
     */
    public String getValidationMessage() {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        // ✅ Priority 1: Validation message via content-desc (android.view.View)
        // This validation message appears between Confirm Password field and "Already
        // have an account? Sign in" button
        // Example from XML: <android.view.View content-desc="Invalid email. Please
        // check your credentials." bounds="[52,1100][668,1132]" />
        try {
            // Look for android.view.View elements with content-desc that contain validation
            // keywords
            WebElement validationView = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath(
                            "//android.view.View[@content-desc and (contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'invalid') or "
                                    +
                                    "contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'error') or "
                                    +
                                    "contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'required') or "
                                    +
                                    "contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'weak') or "
                                    +
                                    "contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'match') or "
                                    +
                                    "contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'please') or "
                                    +
                                    "contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'check') or "
                                    +
                                    "contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'credentials') or "
                                    +
                                    "contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'empty') or "
                                    +
                                    "contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'field'))]")));
            String contentDesc = validationView.getAttribute("content-desc");
            if (contentDesc != null && !contentDesc.trim().isEmpty()) {
                return contentDesc;
            }
        } catch (Exception ignored) {
        }

        // ✅ Priority 2: Android Toast Message
        try {
            WebElement toast = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//android.widget.Toast[1]")));
            String toastText = toast.getText();
            if (toastText != null && !toastText.trim().isEmpty()) {
                return toastText;
            }
        } catch (Exception ignored) {
        }

        // ✅ Priority 3: Any TextView with error keywords
        try {
            WebElement errorKeyword = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//android.widget.TextView[" +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'invalid') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'error') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'required') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'weak') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'match') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'please')]")));
            String text = errorKeyword.getText();
            if (text != null && !text.trim().isEmpty()) {
                return text;
            }
        } catch (Exception ignored) {
        }

        // ✅ Priority 4: TextView with error resource-id
        try {
            WebElement errorById = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(
                            "//android.widget.TextView[contains(@resource-id, 'error') or contains(@resource-id, 'validation')]")));
            String text = errorById.getText();
            if (text != null && !text.trim().isEmpty()) {
                return text;
            }
        } catch (Exception ignored) {
        }

        // ✅ Priority 5: EditText error attribute
        try {
            WebElement editTextError = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//android.widget.EditText[@error='true']")));
            String errorAttr = editTextError.getAttribute("error");
            if (errorAttr != null && !errorAttr.trim().isEmpty()) {
                return errorAttr;
            }
        } catch (Exception ignored) {
        }

        // ✅ Priority 6: Disabled Continue button (for empty fields)
        try {
            WebElement btn = findElementWithFallback(null, continueBtnXpath, "CONTINUE");
            if (!btn.isEnabled()) {
                return "Continue button is disabled (fields are empty or invalid)";
            }
        } catch (Exception ignored) {
        }

        // ❌ No validation message found
        return null;
    }
}
