package com.starbucks.core.pages;

import com.starbucks.core.Driver;
import com.starbucks.core.SeleniumUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StarbucksGiftCard {
    private static final By SELECTOR_TAG_TD = By.tagName("td");
    private static final By SELECTOR_ID_EGIFT_CATEGORIES = By.id("egift-categories");
    private static final By SELECTOR_ID_MESSAGE = By.id("message");
    private static final By SELECTOR_ID_RECIPIENT_NAME = By.id("recipient_name");
    private static final By SELECTOR_ID_PRE_RANGE = By.id("pre_range");
    private static final By SELECTOR_TAG_LABEL = By.tagName("label");
    private static final By SELECTOR_ID_SENDER_NAME = By.id("sender_name");
    private static final By SELECTOR_ID_SENDER_EMAIL = By.id("sender_email");
    private static final By SELECTOR_ID_RECIPIENT_EMAIL = By.id("recipient_email");
    private static final By SELECTOR_CLASS_UI_DATEPICKER_TRIGGER = By.className("ui-datepicker-trigger");
    private static final By SELECTOR_CLASS_UI_DATEPICKER_DIV = By.id("ui-datepicker-div");
    private static final By SELECTOR_CLASS_UI_DATEPICKER_NEXT = By.className("ui-datepicker-next");
    private static final By SELECTOR_CLASS_UI_DATEPICKER_CALENDAR = By.className("ui-datepicker-calendar");
    private static final By SELECTOR_CLASS_ACTION = By.className("action");
    private static final By SELECTOR_CLASS_CART_TABLE = By.className("cart_table");
    private static final By SELECTOR_ID_CAROUSEL_SCROLLABLE = By.id("carousel-scrollable");
    private static final By SELECTOR_TAG_IMG = By.tagName("img");
    private static final By SELECTOR_TAG_LI = By.tagName("li");

    private static StarbucksGiftCard starbucksGiftCard;

    private StarbucksGiftCard() {
    }

    public static StarbucksGiftCard instance() {
        if (starbucksGiftCard == null) {
            starbucksGiftCard = new StarbucksGiftCard();
        }
        return starbucksGiftCard;
    }

    public StarbucksGiftCard selectGiftCardType(String giftCardType) {
        SeleniumUtil.addExplicitWait(5, SELECTOR_ID_EGIFT_CATEGORIES);

        Driver
                .getDriver()
                .findElement(SELECTOR_ID_EGIFT_CATEGORIES)
                .findElements(SELECTOR_TAG_LI)
                .stream()
                .filter(we -> we.getText().equals(giftCardType))
                .findFirst()
                .get()
                .click();

        return instance();
    }

    public StarbucksGiftCard addRecipientName(String name) {
        fillById(SELECTOR_ID_RECIPIENT_NAME, name);
        return instance();
    }


    public StarbucksGiftCard addMessage(String message) {
        fillById(SELECTOR_ID_MESSAGE, message);
        return instance();
    }

    public StarbucksGiftCard chooseAmount(int amount) {
        Driver
                .getDriver()
                .findElement(SELECTOR_ID_PRE_RANGE)
                .findElements(SELECTOR_TAG_LABEL)
                .stream()
                .filter(we -> we.getAttribute("for").equals("defined-amount_" + amount))
                .findFirst()
                .get()
                .click();
        return instance();
    }

    public StarbucksGiftCard purchaserName(String name) {
        fillById(SELECTOR_ID_SENDER_NAME, name);
        return instance();
    }

    public StarbucksGiftCard purchaserEmail(String email) {
        fillById(SELECTOR_ID_SENDER_EMAIL, email);
        return instance();
    }

    public StarbucksGiftCard recipientEmail(String email) {
        fillById(SELECTOR_ID_RECIPIENT_EMAIL, email);
        return instance();
    }

    public StarbucksGiftCard addDeliverGiftOn(String date) {

        WebDriver driver = Driver.getDriver();
        driver.findElement(SELECTOR_CLASS_UI_DATEPICKER_TRIGGER).click();

        LocalDate ldt = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        for (int i = 0; i < (long) (ldt.getMonthValue() - LocalDate.now().getMonthValue()); i++) {
            driver
                    .findElement(SELECTOR_CLASS_UI_DATEPICKER_DIV)
                    .findElement(SELECTOR_CLASS_UI_DATEPICKER_NEXT)
                    .click();
        }

        driver.findElement(SELECTOR_CLASS_UI_DATEPICKER_CALENDAR)
                .findElements(SELECTOR_TAG_TD)
                .stream()
                .filter(we -> !we.getAttribute("class").contains("ui-state-disabled"))
                .filter(we -> String.valueOf(ldt.getDayOfMonth()).equals(we.getText()))
                .findFirst()
                .get()
                .click();

        return instance();
    }

    public StarbucksCart addToCart() {
        Driver
                .getDriver()
                .findElement(SELECTOR_CLASS_ACTION)
                .click();

        SeleniumUtil.addExplicitWait(5, SELECTOR_CLASS_CART_TABLE);

        return StarbucksCart.instance();
    }

    public StarbucksGiftCard selectGiftCard(String giftCardName) {
        Driver
                .getDriver()
                .findElement(SELECTOR_ID_CAROUSEL_SCROLLABLE)
                .findElements(SELECTOR_TAG_IMG)
                .stream()
                .filter(we -> giftCardName.equals(we.getAttribute("alt")))
                .findFirst()
                .get()
                .click();

        return instance();
    }

    private void fillById(By id, String name) {
        WebElement webElement = Driver
                .getDriver()
                .findElement(id);
        webElement.clear();
        webElement.sendKeys(name);
    }

}
