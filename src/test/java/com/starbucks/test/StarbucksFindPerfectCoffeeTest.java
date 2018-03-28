package com.starbucks.test;

import com.starbucks.core.StarbucksTest;
import com.starbucks.core.pages.StarbucksFindCoffee;
import com.starbucks.core.pages.StarbucksHome;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StarbucksFindPerfectCoffeeTest extends StarbucksTest {

    @DataProvider(name = "PerfectCoffeeAnswers")
    public static Object[][] perfectCoffeeAnswers() {
        return new Object[][]{
                {new String[]{"Lighthearted and sunny", "A group of friends", "Toasted nuts", "I like things simple"},
                        "Lighthearted and sunny,A group of friends,Toasted nuts,I like things simple"},

                {new String[]{"Balanced and easy-going", "A quiet moment", "Citrus", "I can mix it up sometimes"},
                        "Balanced and easy-going,A quiet moment,Citrus,I can mix it up sometimes"},

                {new String[]{"Bold and complex", "A busy day", "Nuts and spices", "I love to try something new and different"},
                        "Bold and complex,A busy day,Nuts and spices,I love to try something new and different"},
        };
    }

    @Test(dataProvider = "PerfectCoffeeAnswers")
    public void scenario(String[] perfectCoffeeAnswers, String expectedPerfectCoffeeAnswers) {

        StarbucksFindCoffee starbucksFindCoffee = StarbucksHome
                .go()
                .findPerfectCoffee()
                .answer(perfectCoffeeAnswers);

        Assert.assertEquals(starbucksFindCoffee.getSelectedAnswers(), expectedPerfectCoffeeAnswers);

        WebElement recommendationElement = starbucksFindCoffee
                .findMyCoffee()
                .getRecommendation();

        Assert.assertEquals(recommendationElement.getAttribute("style"), "display: block;");
        Assert.assertTrue(recommendationElement.isDisplayed());
    }
}
