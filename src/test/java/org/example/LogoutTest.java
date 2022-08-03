package org.example;

import io.qameta.allure.TmsLink;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LogoutTest extends BaseTest {

    @TmsLink("L2-20")
    @Test
    public void logoutInBurgerMenuShouldLogoutUser() {
        loginSteps.loginAsStandardUser();
        headerPage.openBurgerMenu();
        headerPage.logout();
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getLoginButtonLocator()));
        assertTrue(loginPage.isLoginButtonDisplayed(), "User was not logged out");
    }
}
