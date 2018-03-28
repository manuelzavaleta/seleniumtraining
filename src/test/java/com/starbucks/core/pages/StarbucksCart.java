package com.starbucks.core.pages;

import com.starbucks.core.Driver;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StarbucksCart extends Page {
    private static StarbucksCart starbucksCart;

    private StarbucksCart() {
    }

    public static StarbucksCart instance() {
        if (starbucksCart == null) {
            starbucksCart = new StarbucksCart();
        }
        return starbucksCart;
    }

    private String findCartElementFromFirstItem(String dataThAttribute) {
        return Driver
                .getDriver()
                .findElements(SELECTOR_TAG_TBODY)
                .stream()
                .filter(we1 -> !"".equals(we1.getAttribute("id")))
                .findFirst()
                .get()
                .findElements(SELECTOR_TAG_TD)
                .stream()
                .filter(we -> {
                    String attribute = we.getAttribute("data-th");
                    return attribute != null && dataThAttribute.equals(attribute.trim());
                })
                .findFirst()
                .get()
                .getText();
    }

    private int parsePriceToInt(String amount) {
        return Double.valueOf(amount.substring(1))
                .intValue();
    }

    private String getFirstElementInCartSummary(By tagName) {
        return Driver
                .getDriver()
                .findElement(SELECTOR_ID_CART_SUMMARY)
                .findElement(SELECTOR_TAD_TBODY)
                .findElements(SELECTOR_TAG_TR)
                .stream()
                .findFirst()
                .get()
                .findElement(tagName)
                .getText();

    }

    public String getRecipientEmail() {
        return findCartElementFromFirstItem("Recipient");
    }

    public int getGiftAmount() {
        String amount = findCartElementFromFirstItem("Amount");
        return parsePriceToInt(amount);
    }

    public String getDeliveryDate() {
        String deliveryDate = findCartElementFromFirstItem("Delivery Date");
        LocalDate ldt = LocalDate.parse(deliveryDate, DateTimeFormatter.ofPattern("M/d/yyyy"));
        return ldt.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public String getRecipientNameAndMessage() {
        return findCartElementFromFirstItem("Message");
    }

    public String getFirstElementInCartDescription() {
        return getFirstElementInCartSummary(SELECTOR_TAG_TH);
    }

    public int getFirstElementInCartPrice() {
        return parsePriceToInt(getFirstElementInCartSummary(SELECTOR_TAG_TD));
    }
}
