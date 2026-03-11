package com.crm.qa.util;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyListener implements WebDriverListener {

    /* ===================== FIND ELEMENT ===================== */

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        System.out.println("🔍 Finding element using: " + locator);
    }

    /* ===================== CLICK ===================== */

    @Override
    public void beforeClick(WebElement element) {
        System.out.println("🖱️ Before clicking element");
    }

    @Override
    public void afterClick(WebElement element) {
        System.out.println("✅ Click action completed");
    }

    /* ===================== SEND KEYS ===================== */

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        System.out.println("⌨️ Typing value: " + String.join("", keysToSend));
    }

    /* ===================== ERROR HANDLING + SCREENSHOT ===================== */

    @Override
    public void onError(Object target,
                        Method method,
                        Object[] args,
                        InvocationTargetException e) {

        System.out.println("❌ Exception occurred in method: " + method.getName());
        System.out.println("❌ Root cause: " + e.getCause());

        // Take screenshot only if driver is available
        if (target instanceof WebDriver) {
            WebDriver driver = (WebDriver) target;

            try {
                TakesScreenshot ts = (TakesScreenshot) driver;

                String timestamp = LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);

                // You can save this byte[] to file or attach to report
                System.out.println("📸 Screenshot captured at: " + timestamp);

            } catch (Exception ex) {
                System.out.println("⚠️ Screenshot capture failed: " + ex.getMessage());
            }
        }
    }
}
