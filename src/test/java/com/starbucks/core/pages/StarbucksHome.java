package com.starbucks.core.pages;

import com.starbucks.core.Driver;
import com.starbucks.core.SeleniumUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class StarbucksHome {
    private static StarbucksHome starbucksHome;

    private StarbucksHome() {
    }

    public static StarbucksHome instance() {
        if (starbucksHome == null) {
            starbucksHome = new StarbucksHome();
        }
        return starbucksHome;
    }

    public static StarbucksHome go() {
        Driver.getDriver().get("https://www.starbucks.com/");
        return instance();
    }

    public List<WebElement> findMenuElements() {
        return Driver
                .getDriver()
                .findElement(By.id("nav"))
                .findElements(By.tagName("li"));
    }

    public String findMenuElementsToString() {
        return findMenuElements()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList())
                .stream()
                .collect(Collectors.joining(","));
    }

    public StarbucksFindCoffee findPerfectCoffee() {
        menuBarNavigation("nav_coffee", "Find Your Perfect Coffee");
        return StarbucksFindCoffee.instance();
    }

    private void menuBarNavigation(String menuBarOption, String subMenuBarOption) {
        WebDriver driver = Driver.getDriver();
        By byIdNavCoffee = By.id(menuBarOption);
        SeleniumUtil.addExplicitWait(3, byIdNavCoffee);
        driver.findElement(byIdNavCoffee).click();

        By byLinkTextFindYourPerfectCoffee = By.linkText(subMenuBarOption);
        SeleniumUtil.addExplicitWait(2, byLinkTextFindYourPerfectCoffee);
        driver.findElement(byLinkTextFindYourPerfectCoffee).click();
    }


    public StarbucksGiftCard giftCard() {
        menuBarNavigation("nav_gift_cards", "Give a Gift by Email");
        return StarbucksGiftCard.instance();
    }
}
