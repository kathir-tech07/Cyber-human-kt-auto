package com.automation.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage {
    private AppiumDriver driver;
    private WebDriverWait wait;

    public SignInPage(AppiumDriver driver) {
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

    private final String emailXpath = "//android.widget.EditText[@hint='Email']";
    private final String passwordXpath = "//android.widget.EditText[@password='true']";
    private final String continueBtnXpath = "//android.widget.Button[@content-desc='CONTINUE']";

    public void enterEmail(String email) {
        WebElement el = findElementWithFallback(null, emailXpath, null);
        el.click();
        el.clear();
        el.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement el = findElementWithFallback(null, passwordXpath, null);
        el.click();
        el.clear();
        el.sendKeys(password);
    }

    public void clickContinue() {
        WebElement btn = findElementWithFallback(null, continueBtnXpath, "CONTINUE");
        if (btn.isEnabled()) {
            btn.click();
        }
    }

    public boolean isContinueButtonEnabled() {
        try {
            WebElement btn = findElementWithFallback(null, continueBtnXpath, "CONTINUE");
            return btn.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get the runtime validation message that appears below the password field.
     * This method checks multiple locations and patterns for dynamic error
     * messages.
     * 
     * @return The validation message text, or null if no message is found
     */
    public String getRuntimeValidationMessage() {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));

        // Strategy 1: Look for validation message below password field (most specific)
        try {
            WebElement validationMsg = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(
                            "//android.widget.EditText[@password='true']/following-sibling::android.widget.TextView[1]")));
            String text = validationMsg.getText();
            if (text != null && !text.trim().isEmpty()) {
                return text;
            }
        } catch (Exception ignored) {
        }

        // Strategy 2: Look for any TextView with common error keywords
        // (case-insensitive)
        try {
            WebElement errorText = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//android.widget.TextView[" +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'invalid') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'error') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'required') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'wrong') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'incorrect') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'please') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'check') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'must') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'cannot')]")));
            String text = errorText.getText();
            if (text != null && !text.trim().isEmpty() &&
                    !text.equals("Forgot Password?") && !text.equals("Don't have an account? Sign up")) {
                return text;
            }
        } catch (Exception ignored) {
        }

        // Strategy 3: Look for TextView with error resource-id
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

        // Strategy 4: Android Toast message
        try {
            WebElement toast = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//android.widget.Toast[1]")));
            String text = toast.getText();
            if (text != null && !text.trim().isEmpty()) {
                return text;
            }
        } catch (Exception ignored) {
        }

        // Strategy 5: Look for any red-colored text (validation messages are typically
        // red)
        // This searches for TextViews between password field and Continue button
        try {
            WebElement errorText = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//android.widget.TextView[@text and string-length(@text) > 10]")));
            String text = errorText.getText();
            // Filter out known non-error texts
            if (text != null && !text.trim().isEmpty() &&
                    !text.equals("Forgot Password?") &&
                    !text.equals("Don't have an account? Sign up") &&
                    !text.equals("SIGN IN") &&
                    !text.contains("CONTINUE WITH")) {
                return text;
            }
        } catch (Exception ignored) {
        }

        return null;
    }

    /**
     * Check if the Continue button is in a disabled state (light grey background).
     * This method checks the button's enabled state and can be extended to check
     * visual properties.
     * 
     * @return true if button is disabled (light grey), false if enabled (black)
     */
    public boolean isContinueButtonDisabled() {
        try {
            WebElement btn = findElementWithFallback(null, continueBtnXpath, "CONTINUE");
            // Check if button is disabled
            boolean isDisabled = !btn.isEnabled();

            // Optional: Check for visual properties if needed
            // String btnClass = btn.getAttribute("class");
            // String btnEnabled = btn.getAttribute("enabled");

            return isDisabled;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get the Continue button's background color or state.
     * This can be used to assert that the button is light grey when fields are
     * empty/invalid.
     * 
     * @return A string describing the button state (e.g., "enabled", "disabled")
     */
    public String getContinueButtonState() {
        try {
            WebElement btn = findElementWithFallback(null, continueBtnXpath, "CONTINUE");
            boolean isEnabled = btn.isEnabled();
            String enabledAttr = btn.getAttribute("enabled");
            String clickableAttr = btn.getAttribute("clickable");

            if (!isEnabled || "false".equals(enabledAttr) || "false".equals(clickableAttr)) {
                return "disabled";
            } else {
                return "enabled";
            }
        } catch (Exception e) {
            return "not_found";
        }
    }
}
