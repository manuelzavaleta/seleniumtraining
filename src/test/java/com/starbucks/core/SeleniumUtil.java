package com.starbucks.core;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtil {
    public static void addExplicitWait(int timeOutInSeconds, By by) {
        new WebDriverWait(Driver.getDriver(), timeOutInSeconds)
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}
