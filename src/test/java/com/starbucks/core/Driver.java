package com.starbucks.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Driver {

    private static WebDriver driver;

    private Driver() {
    }

    public static void setupDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions(DesiredCapabilities.firefox());
        firefoxOptions.addPreference("browser.popups.showPopupBlocker", false);
        firefoxOptions.addPreference("security.sandbox.content.level", 5);
        firefoxOptions.setAcceptInsecureCerts(true);
        firefoxOptions.setProfile(new FirefoxProfile());

        driver = new FirefoxDriver(firefoxOptions);
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            setupDriver();
        }

        return driver;
    }
}
