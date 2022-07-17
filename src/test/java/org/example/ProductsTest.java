package org.example;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class ProductsTest extends BaseTest {

    public static final String SAUCE_LABS_BACKPACK = "Sauce Labs Backpack";
    public static final String SAUCE_LABS_BOLT_T_SHIRT = "Sauce Labs Bolt T-Shirt";
    public static final String SAUCE_LABS_BIKE_LIGHT = "Sauce Labs Bike Light";
    public static final String SAUCE_LABS_FLEECE_JACKET = "Sauce Labs Fleece Jacket";
    public static final String SAUCE_LABS_ONESIE = "Sauce Labs Onesie";
    public static final String TEST_ALL_THINGS = "Test.allTheThings() T-Shirt (Red)";

    @Test(groups = "standard user tests")
    public void productShouldBeAddedToCart() {
        loginSteps.loginAsDefaultUser();
        productsPage.addToCart(SAUCE_LABS_BACKPACK);
        headerPage.openCart();
        List<WebElement> allProductsInCart = cartPage.getAllProductsInCart();

        assertEquals(allProductsInCart.size(), 1,
                "One product should be in the cart");
        assertEquals(allProductsInCart.get(0).getText(), SAUCE_LABS_BACKPACK,
                "\"" + SAUCE_LABS_BACKPACK + "\" product should be in the cart");
    }

    @Test(groups = "standard user tests")
    public void productShouldBeRemovedFromCart() {
        loginSteps.loginAsDefaultUser();
        productsPage.addToCart(SAUCE_LABS_BACKPACK, SAUCE_LABS_BOLT_T_SHIRT);
        headerPage.openCart();
        List<WebElement> allProductsInCartBeforeRemove = cartPage.getAllProductsInCart();
        assertEquals(allProductsInCartBeforeRemove.size(), 2,
                "Two products should be in the cart");

        cartPage.removeProductFromCart(SAUCE_LABS_BOLT_T_SHIRT);
        List<WebElement> allProductsInCartAfterRemove = cartPage.getAllProductsInCart();

        assertEquals(allProductsInCartAfterRemove.size(), 1,
                "Only one product should left in the cart after removing");
        assertEquals(allProductsInCartAfterRemove.get(0).getText(), SAUCE_LABS_BACKPACK,
                "\"" + SAUCE_LABS_BACKPACK + "\" product should be in the cart after removing");
    }

        @DataProvider(name = "Data Provider for problem_user")
        public static Object[][] problemUserTests() {
            return new Object[][]
                    {
                            {SAUCE_LABS_BACKPACK, "REMOVE"},
                            {SAUCE_LABS_BOLT_T_SHIRT, "REMOVE"},
                            {SAUCE_LABS_BIKE_LIGHT, "REMOVE"},
                            {SAUCE_LABS_FLEECE_JACKET, "REMOVE"},
                            {SAUCE_LABS_ONESIE, "REMOVE"},
                            {TEST_ALL_THINGS, "REMOVE"}
                    };
        }

        @Test(dataProvider = "Data Provider for problem_user", groups = "problem user tests")
        public void productShouldBeAddedToCartForProblemUser(String productName, String expectedButton) {
            loginSteps.loginAsProblemUser();
            productsPage.addToCart(productName);
            Assert.assertEquals(productsPage.findRemoveButtonOnProductPage(productName), expectedButton, "The product wasn't add to cart");
        }

    @Test(groups = "problem user tests")
    public void productShouldBeRemovedFromCartForProblemUser() {
        loginSteps.loginAsProblemUser();
        productsPage.addToCart(SAUCE_LABS_BACKPACK, SAUCE_LABS_BIKE_LIGHT);
        headerPage.openCart();
        List<WebElement> allProductsInCartBeforeRemove = cartPage.getAllProductsInCart();
        assertEquals(allProductsInCartBeforeRemove.size(), 2,
                "Two products should be in the cart");

        cartPage.removeProductFromCart(SAUCE_LABS_BACKPACK);
        List<WebElement> allProductsInCartAfterRemove = cartPage.getAllProductsInCart();

        assertEquals(allProductsInCartAfterRemove.size(), 1,
                "Only one product should left in the cart after removing");
        assertEquals(allProductsInCartAfterRemove.get(0).getText(), SAUCE_LABS_BIKE_LIGHT,
                "\"" + SAUCE_LABS_BACKPACK + "\" product shouldn't be in the cart after removing");
    }

    @DataProvider(name = "Data Provider for performance_glitch_user")
    public static Object[][] performanceGlitchUserTests() {
        return new Object[][]
                {
                        {SAUCE_LABS_BACKPACK, "REMOVE"},
                        {SAUCE_LABS_BOLT_T_SHIRT, "REMOVE"},
                        {SAUCE_LABS_BIKE_LIGHT, "REMOVE"},
                        {SAUCE_LABS_FLEECE_JACKET, "REMOVE"},
                        {SAUCE_LABS_ONESIE, "REMOVE"},
                        {TEST_ALL_THINGS, "REMOVE"}
                };
    }

    @Test(dataProvider = "Data Provider for performance_glitch_user", groups = "performance glitch user tests")
    public void productShouldBeAddedToCartForPerformanceGlitchUser(String productName, String expectedButton) {
        loginSteps.loginAsPerformanceGlitchUser();
        wait.until(ExpectedConditions.visibilityOfElementLocated(productsPage.getTitleLocator()));
        productsPage.addToCart(productName);
        Assert.assertEquals(productsPage.findRemoveButtonOnProductPage(productName), expectedButton, "The product wasn't add to cart");
    }

}
