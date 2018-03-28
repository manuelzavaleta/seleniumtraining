package com.starbucks.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {

    private static WebDriver driver;

    private Driver() {
    }

    public static void setupDriver() {
        driver = new FirefoxDriver();
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            setupDriver();
        }

        return driver;
    }
}
