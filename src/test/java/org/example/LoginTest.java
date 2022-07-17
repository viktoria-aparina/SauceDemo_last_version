package org.example;

import org.example.utils.PropertiesLoader;
import org.testng.annotations.Test;

import java.util.Properties;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test(groups = "standard user tests")
    public void userShouldLoginWithValidCredentials() {
        loginSteps.loginAsStandardUser();
        assertTrue(productsPage.getTitle().isDisplayed(), "User was not logged in");
    }

    @Test(groups = "standard user tests")
    public void passwordShouldBeRequiredForLogin() {
        loginSteps.login("standard_user", "");
        String expected = "Epic sadface: Password is required";
        assertEquals(loginPage.getError(), expected, "The error is incorrect");
    }

    @Test(groups = "standard user tests")
    public void userNameShouldBeRequiredForLogin() {
        loginSteps.login("", "12345");
        String expected = "Epic sadface: Username is required";
        assertEquals(loginPage.getError(), expected, "The error is incorrect");
    }

    @Test(groups = "standard user tests")
    public void userShouldNotBeLoggedInBeWithInvalidPassword() {
        loginSteps.login("standard_user", "12345");
        String expected = "Epic sadface: Username and password do not match any user in this service";
        assertEquals(loginPage.getError(), expected, "The error is incorrect");
    }

    @Test(groups = "standard user tests")
    public void userShouldNotBeLoggedInBeWithInvalidUserName() {
        loginSteps.login("standard_user1", "secret_sauce");
        String expected = "Epic sadface: Username and password do not match any user in this service";
        assertEquals(loginPage.getError(), expected, "The error is incorrect");
    }

    @Test(groups = "locked out user tests")
    public void lockedOutUserShouldNotBeLoggedIn() {
        Properties properties = PropertiesLoader.loadProperties("locked_out_user.properties");
        loginSteps.login(properties.getProperty("username"), properties.getProperty("password"));
        String expected = "Epic sadface: Sorry, this user has been locked out.";
        assertEquals(loginPage.getError(), expected, "The error is incorrect");
    }

    @Test(groups = "problem user tests")
    public void problemUserShouldBeLoggedInWithValidCredential() {
        loginSteps.loginAsProblemUser();
        assertTrue(productsPage.getTitle().isDisplayed(), "User was not logged in");
    }

    @Test(groups = "performance glitch user tests")
    public void performanceGlitchUserShouldBeLoggedInWithValidCredential() {
        loginSteps.loginAsPerformanceGlitchUser();
        assertTrue(productsPage.getTitle().isDisplayed(), "User was not logged in");
    }
}
