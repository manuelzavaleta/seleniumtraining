package com.starbucks.core;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class StarbucksTest {

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        System.setProperty("webdriver.gecko.driver", "C:\\selenium\\geckodriver.exe");
        Driver.setupDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        Driver.getDriver().quit();
    }
}
