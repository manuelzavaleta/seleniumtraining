package com.starbucks.core.pages;

import com.starbucks.core.Driver;
import com.starbucks.core.SeleniumUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.stream.Collectors;

public class StarbucksHome extends Page {
    private static StarbucksHome starbucksHome;

    private StarbucksHome() {
    }

    private static StarbucksHome instance() {
        if (starbucksHome == null) {
            starbucksHome = new StarbucksHome();
        }
        return starbucksHome;
    }

    public static StarbucksHome go() {
        Driver
                .getDriver()
                .get("https://www.starbucks.com/");

        return instance();
    }

    public String findMenuElementsToString() {
        return Driver
                .getDriver()
                .findElement(SELECTOR_ID_NAV)
                .findElements(SELECTOR_TAG_LI)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList())
                .stream()
                .collect(Collectors.joining(","));
    }

    public StarbucksFindCoffee findPerfectCoffee() {
        menuBarNavigation(SELECTOR_ID_NAV_COFFEE, SELECTOR_LINK_TEXT_FIND_YOUR_PERFECT_COFFEE);
        return StarbucksFindCoffee.instance();
    }

    public StarbucksGiftCard giftCard() {
        menuBarNavigation(SELECTOR__ID_NAV_GIFT_CARDS, SELECTOR_LINK_TEXT_GIVE_A_GIFT_BY_EMAIL);
        return StarbucksGiftCard.instance();
    }

    private void menuBarNavigation(By menuBarOptionSelector, By subMenuBarOptionSelector) {
        WebDriver driver = Driver.getDriver();
        SeleniumUtil.addExplicitWait(3, menuBarOptionSelector);
        driver.findElement(menuBarOptionSelector).click();

        SeleniumUtil.addExplicitWait(2, subMenuBarOptionSelector);
        driver.findElement(subMenuBarOptionSelector).click();
    }
}
