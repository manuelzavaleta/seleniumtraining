package com.starbucks.test;

import com.starbucks.core.StarbucksTest;
import com.starbucks.core.pages.StarbucksHome;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class StarbucksMenuTest extends StarbucksTest {
    public void scenario() {
        String expectedMenuElements = "COFFEE,TEA,MENU,COFFEEHOUSE,SOCIAL IMPACT,STARBUCKS REWARDS,BLOG,GIFT CARDS";
        String actualMenuElements = StarbucksHome.go().findMenuElementsToString();

        Assert.assertEquals(actualMenuElements, expectedMenuElements);
    }
}
