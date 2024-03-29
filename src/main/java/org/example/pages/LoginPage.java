package org.example.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class LoginPage extends BasePage {

    private static final String LOGIN_BUTTON_ID = "login-button";

    @FindBy(id = "user-name")
    private WebElement userNameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = LOGIN_BUTTON_ID)
    private WebElement loginButton;

    @FindBy(css = "[data-test=error]")
    private WebElement error;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(getLoginButtonLocator()));
            return true;
        } catch (TimeoutException exception) {
            log.error("The page {} was not opened, because of error {}", "Login Page", exception.getCause());
            return false;
        }
    }

    @Override
    public LoginPage open() {
        driver.get(baseUrl);
        return this;
    }

    public By getLoginButtonLocator() {
        return By.id(LOGIN_BUTTON_ID);
    }

    public void fillInUserName(String userName) {
        userNameInput.sendKeys(userName);
    }

    public void fillInPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void submitForm() {
        loginButton.submit();
    }

    public boolean isLoginButtonDisplayed() {
        return loginButton.isDisplayed();
    }

    public String getError() {
        return error.getText();
    }
}
