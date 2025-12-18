package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private WebDriverWait wait;

    public ProfilePage(AppiumDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private final String profileHeadingXpath = "//android.view.View[@content-desc='PROFILE']";
    private final String accountButtonXpath = "//android.widget.Button[@content-desc='ACCOUNT']";
    private final String myOrdersXpath = "//android.view.View[@content-desc='MY ORDERS']";
    private final String helpSupportXpath = "//android.view.View[@content-desc='HELP & SUPPORT']";
    private final String logoutXpath = "//android.view.View[@content-desc='LOG OUT']";

    /**
     * Click the ACCOUNT button to navigate to Edit Profile page
     */
    public void clickAccount() {
        try {
            WebElement accountBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(accountButtonXpath)));
            accountBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("ACCOUNT button not found on Profile page", e);
        }
    }

    /**
     * Click the MY ORDERS option
     */
    public void clickMyOrders() {
        try {
            WebElement myOrdersBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(myOrdersXpath)));
            myOrdersBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("MY ORDERS option not found on Profile page", e);
        }
    }

    /**
     * Click the HELP & SUPPORT option
     */
    public void clickHelpSupport() {
        try {
            WebElement helpBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(helpSupportXpath)));
            helpBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("HELP & SUPPORT option not found on Profile page", e);
        }
    }

    /**
     * Click the LOG OUT option
     */
    public void clickLogout() {
        try {
            WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(logoutXpath)));
            logoutBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("LOG OUT option not found on Profile page", e);
        }
    }

    /**
     * Check if Profile page is displayed
     */
    public boolean isProfilePageDisplayed() {
        try {
            WebElement heading = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(profileHeadingXpath)));
            return heading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
