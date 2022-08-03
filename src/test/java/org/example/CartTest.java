package org.example;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.LogManager;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CartTest extends BaseTest {

    @Issue("SHRL-18")
    @Test
    public void continueShoppingShouldReturnUserOnProductsPage() {
        loginSteps.loginAsDefaultUser();
        headerPage.openCart();
        cartPage.continueShopping();
        assertTrue(productsPage.getTitle().isDisplayed(), "The continue shopping button does not redirect to the product page");
    }

    @Issue("SHRL-14")
    @Test
    public void checkoutShouldOpenCheckoutPage() {
        loginSteps.loginAsDefaultUser();
        headerPage.openCart();
        cartPage.checkout();
        assertEquals(checkoutPage.getTitle().getText(), "CHECKOUT: YOUR INFORMATION", "The checkout button does not open checkout page");
    }

    @Issue("SHRL-15")
    @Test
    public void countOfProductsIsChangeable() {
        loginSteps.loginAndGoToCartWithPurchasesAsDefaultUser();
        cartPage.changeCountOfProductsInCart("Sauce Labs Fleece Jacket", "3");
        Assert.assertEquals(cartPage.getCountOfProducts(), "3", "Count of products wasn't change in the cart");
    }

    @Issue("SHRL-16")
    @Test
    public void removeButtonIsWorking() {
        loginSteps.loginAndGoToCartWithPurchasesAsDefaultUser();
        cartPage.getAllProductsInCart();
        cartPage.clickRemove("Sauce Labs Fleece Jacket");
        cartPage.getAllProductsInCart();
        Assert.assertEquals(cartPage.getAllProductsInCart().size(), 5, "Product wasn't remove from cart");
    }
}

