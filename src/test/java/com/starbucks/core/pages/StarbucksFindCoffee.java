package com.starbucks.core.pages;

import com.starbucks.core.Driver;
import com.starbucks.core.SeleniumUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StarbucksFindCoffee {
    private static final By SELECTOR_TAG_BUTTON = By.tagName("Button");
    private static final By SELECTOR_CLASS_COLLAPSED_SELECTION = By.className("collapsed-selection");
    private static final By SELECTOR_ID_FIND_MY_COFFEE = By.id("find-my-coffee");
    private static final By SELECTOR_ID_FEATURED_COFFEE_STACK = By.id("featured-coffees-stack");

    private static StarbucksFindCoffee starbucksFindCoffee;

    private StarbucksFindCoffee() {
    }

    public static StarbucksFindCoffee instance() {
        if (starbucksFindCoffee == null) {
            starbucksFindCoffee = new StarbucksFindCoffee();
        }
        return starbucksFindCoffee;
    }

    public StarbucksFindCoffee answer(String... answers) {
        for (int i = 0; i < answers.length; i++) {
            By questionId = By.id(String.format("question%s", i + 1));
            SeleniumUtil.addExplicitWait(3, questionId);
            selectQuestionCoffeeOption(questionId, answers[i]);
        }
        return instance();
    }

    private void selectQuestionCoffeeOption(By byQuestion, String dataEventValue) {
        Driver
                .getDriver()
                .findElement(byQuestion)
                .findElements(SELECTOR_TAG_BUTTON)
                .stream()
                .filter(e -> e.getAttribute("data-event").equals(dataEventValue))
                .filter(WebElement::isDisplayed)
                .findFirst()
                .get()
                .click();
    }

    public String getSelectedAnswers() {
        return Stream.of(
                findAnswerText("question1"),
                findAnswerText("question2"),
                findAnswerText("question3"),
                findAnswerText("question4")
        )
                .collect(Collectors.joining(","));
    }

    private String findAnswerText(String question) {
        return Driver
                .getDriver()
                .findElement(By.className(question))
                .findElements(SELECTOR_CLASS_COLLAPSED_SELECTION)
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .get()
                .getText();
    }

    public StarbucksFindCoffee findMyCoffee() {
        Driver
                .getDriver()
                .findElement(SELECTOR_ID_FIND_MY_COFFEE)
                .click();
        return instance();
    }

    public WebElement getRecommendation() {
        SeleniumUtil.addExplicitWait(2, SELECTOR_ID_FEATURED_COFFEE_STACK);
        return Driver
                .getDriver()
                .findElement(SELECTOR_ID_FEATURED_COFFEE_STACK);
    }
}
