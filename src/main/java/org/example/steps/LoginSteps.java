package org.example.steps;

import org.example.pages.CartPage;
import org.example.pages.LoginPage;
import org.example.pages.ProductsPage;
import org.example.utils.PropertiesLoader;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class LoginSteps {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String STANDARD_USER_PROPERTIES = "standard_user.properties";
    public static final String PROBLEM_USER_PROPERTIES = "problem_user.properties";
    public static final String PERFORMANCE_GLITCH_USER_PROPERTIES = "performance_glitch_user.properties";
    public static final String LOCKED_OUT_USER_PROPERTIES = "locked_out_user.properties";

    WebDriver webDriver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;

    public LoginSteps(WebDriver webDriver) {
        this.webDriver = webDriver;
        loginPage = new LoginPage(webDriver);
        productsPage = new ProductsPage(webDriver);
        cartPage = new CartPage(webDriver);
    }

    public void login(String user, String password) {
        loginPage.open();
        loginPage.fillInUserName(user);
        loginPage.fillInPassword(password);
        loginPage.submitForm();
    }

    public void goToCartWithPurchases() {
        productsPage.addToCart("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)");
        cartPage.open();
    }

    public void loginAsStandardUser() {
        Properties properties = PropertiesLoader.loadProperties(STANDARD_USER_PROPERTIES);
        login(properties.getProperty(USERNAME), properties.getProperty(PASSWORD));
    }

    public void loginAsProblemUser() {
        Properties properties = PropertiesLoader.loadProperties(PROBLEM_USER_PROPERTIES);
        login(properties.getProperty(USERNAME), properties.getProperty(PASSWORD));
    }

    public void loginAsPerformanceGlitchUser() {
        Properties properties = PropertiesLoader.loadProperties(PERFORMANCE_GLITCH_USER_PROPERTIES);
        login(properties.getProperty(USERNAME), properties.getProperty(PASSWORD));
    }

    public void loginAsLockedOutUser() {
        Properties properties = PropertiesLoader.loadProperties(LOCKED_OUT_USER_PROPERTIES);
        login(properties.getProperty(USERNAME), properties.getProperty(PASSWORD));
    }

    public void loginAsDefaultUser() {
        Properties properties = PropertiesLoader.loadProperties();
        login(properties.getProperty(USERNAME), properties.getProperty(PASSWORD));
    }

    public void loginAndGoToCartWithPurchasesAsDefaultUser() {
        Properties properties = PropertiesLoader.loadProperties();
        login(properties.getProperty(USERNAME), properties.getProperty(PASSWORD));
        goToCartWithPurchases();
    }
}
