package com.starbucks.test;

import com.starbucks.core.StarbucksTest;
import com.starbucks.core.pages.StarbucksHome;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class StarbucksMenuTest extends StarbucksTest {
    public void scenario() {

        //Assert.assertEquals(StarbucksHome.go().findMenuElements().size(), 8);
        Assert.assertEquals(StarbucksHome.go().findMenuElementsToString(), "COFFE,TEA,MENU,COFFEEHOUSE,SOCIAL IMPACT,STARBUCKS REWARDS,BLOG,GIFT CARDS");

    }
}
