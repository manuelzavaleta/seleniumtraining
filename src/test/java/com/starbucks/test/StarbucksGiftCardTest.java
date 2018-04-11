package com.starbucks.test;

import com.starbucks.core.StarbucksTest;
import com.starbucks.core.pages.StarbucksCart;
import com.starbucks.core.pages.StarbucksHome;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class StarbucksGiftCardTest extends StarbucksTest {
    public void scenario() {

        String giftCardType = "Anytime";
        String giftCardName = "Military Appreciation FY18";
        String recipientName = "YOU";
        String message = "You Are Awesome";
        int amount = 100;
        String purchaserName = "ME";
        String purchaserEmail = "me@globant.com";
        String recipientEmail = "you@globant.com";
        String deliveryDate = "2018-07-13";

        StarbucksCart starbucksCart = StarbucksHome
                .go()
                .giftCard()
                .selectGiftCardType(giftCardType)
                .selectGiftCard(giftCardName)
                .addRecipientName(recipientName)
                .addMessage(message)
                .chooseAmount(amount)
                .purchaserName(purchaserName)
                .purchaserEmail(purchaserEmail)
                .recipientEmail(recipientEmail)
                .addDeliverGiftOn(deliveryDate)
                .addToCart();

        Assert.assertEquals(starbucksCart.getRecipientEmail(), recipientEmail);
        Assert.assertEquals(starbucksCart.getGiftAmount(), amount);
        Assert.assertEquals(starbucksCart.getDeliveryDate(), deliveryDate);
        Assert.assertEquals(starbucksCart.getRecipientNameAndMessage(), String.format("%s\n%s", recipientName, message));

        Assert.assertEquals(starbucksCart.getFirstElementInCartDescription(), String.format("1 %s", "Military AppreciationX"));
        Assert.assertEquals(starbucksCart.getFirstElementInCartPrice(), amount);
    }
}
