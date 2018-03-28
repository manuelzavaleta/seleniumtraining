package com.starbucks.core.pages;

import com.starbucks.core.Driver;
import com.starbucks.core.SeleniumUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StarbucksGiftCard {
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
        SeleniumUtil.addExplicitWait(5, By.id("egift-categories"));
        Driver
                .getDriver()
                .findElement(By.id("egift-categories"))
                .findElements(By.tagName("li"))
                .stream()
                .filter(we -> we.getText().equals(giftCardType))
                .findFirst()
                .get()
                .click();

        return instance();
    }

    public StarbucksGiftCard addRecipientName(String name) {
        fillById("recipient_name", name);
        return instance();
    }

    private void fillById(String id, String name) {
        WebElement webElement = Driver
                .getDriver()
                .findElement(By.id(id));
        webElement.clear();
        webElement.sendKeys(name);
    }

    public StarbucksGiftCard addMessage(String message) {
        fillById("message", message);
        return instance();
    }

    public StarbucksGiftCard chooseAmount(int amount) {
        Driver
                .getDriver()
                .findElement(By.id("pre_range"))
                .findElements(By.tagName("label"))
                .stream()
                .filter(we -> we.getAttribute("for").equals("defined-amount_" + amount))
                .findFirst()
                .get()
                .click();
        return instance();
    }

    public StarbucksGiftCard purchaserName(String name) {
        fillById("sender_name", name);
        return instance();
    }

    public StarbucksGiftCard purchaserEmail(String email) {
        fillById("sender_email", email);
        return instance();
    }

    public StarbucksGiftCard recipientEmail(String email) {
        fillById("recipient_email", email);
        return instance();
    }

    public StarbucksGiftCard addDeliverGiftOn(String date) {

        WebDriver driver = Driver.getDriver();
        driver.findElement(By.className("ui-datepicker-trigger")).click();

        LocalDate ldt = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        for (int i = 0; i < (long) (ldt.getMonthValue() - LocalDate.now().getMonthValue()); i++) {
            driver
                    .findElement(By.id("ui-datepicker-div"))
                    .findElement(By.className("ui-datepicker-next"))
                    .click();
        }

        driver.findElement(By.className("ui-datepicker-calendar"))
                .findElements(By.tagName("td"))
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
                .findElement(By.className("action"))
                .click();

        SeleniumUtil.addExplicitWait(5, By.className("cart_table"));

        return StarbucksCart.instance();
    }

    public StarbucksGiftCard selectGiftCard(String giftCardName) {
        Driver
                .getDriver()
                .findElement(By.id("carousel-scrollable"))
                .findElements(By.tagName("img"))
                .stream()
                .filter(we -> giftCardName.equals(we.getAttribute("alt")))
                .findFirst()
                .get()
                .click();

        return instance();
    }
}
