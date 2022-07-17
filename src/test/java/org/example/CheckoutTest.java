package org.example;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutTest extends BaseTest {


    @Test
    public void checkoutOverviewShouldBeDisplayedAfterCheckout() {
        loginSteps.loginAndGoToCartWithPurchasesAsDefaultUser();
        cartPage.checkout();
        checkoutPage.fillInCheckoutInfo("First Name", "Last Name", "Postal Code");
        assertEquals(checkoutOverviewPage.getTitle().getText(), "CHECKOUT: OVERVIEW",
                "Checkout overview is not displayed after user clicks on \"Finish\" button");
    }

    @Test
    public void checkoutCompleteShouldBeDisplayedAfterFinish() {
        loginSteps.loginAndGoToCartWithPurchasesAsDefaultUser();
        cartPage.checkout();
        checkoutPage.fillInCheckoutInfo("First Name", "Last Name", "Postal Code");
        checkoutOverviewPage.getFinishButton().click();
        Assert.assertEquals(checkoutOverviewPage.getTitle().getText(), "CHECKOUT: COMPLETE!", "The order wasn't complete");
    }

    @Test
    public void backHomeButtonShouldReturnToProductPage() {
        loginSteps.loginAndGoToCartWithPurchasesAsDefaultUser();
        cartPage.checkout();
        checkoutPage.fillInCheckoutInfo("First Name", "Last Name", "Postal Code");
        checkoutOverviewPage.getFinishButton().click();
        checkoutOverviewPage.getBackHomeButton().click();
        Assert.assertTrue(productsPage.getTitle().isDisplayed(), "The button \"BackHome\" doesn't allow to go to product page");
    }
}
