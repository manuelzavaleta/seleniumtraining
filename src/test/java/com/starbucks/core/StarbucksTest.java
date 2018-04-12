package com.starbucks.core;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class StarbucksTest {

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        String extension = System.getProperty("os.name").equals("Linux") ? "" : ".exe";
        String driverPath = String.format("src/test/resources/geckodriver%s", extension);
        System.setProperty("webdriver.gecko.driver", driverPath);
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "consoleLogs_Firefox.log");

        Driver.setupDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        Driver.getDriver().quit();
    }
}
