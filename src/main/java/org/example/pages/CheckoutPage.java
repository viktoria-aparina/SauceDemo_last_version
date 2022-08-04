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
public class CheckoutPage extends BasePage {

    @FindBy(css = ".title")
    private WebElement title;

    @FindBy(xpath = "//span[@class='title' and text()='CHECKOUT: YOUR INFORMATION']")
    private WebElement checkoutTitle;

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;

    @FindBy(id = "continue")
    private WebElement continueButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".title")));
            return true;
        } catch (TimeoutException exception) {
            log.error("The page {} was not opened, because of error {}", "Checkout Page", exception.getCause());
            return false;
        }
    }

    @Override
    public CheckoutPage open() {
        driver.get(baseUrl + "checkout-step-one.html");
        return this;
    }

    public void fillInCheckoutInfo(String firstName, String lastName, String postalCode) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        postalCodeInput.sendKeys(postalCode);
        log.info("The fields {}, {}, {} were successfully filled in", firstName, lastName, postalCode);
        continueButton.submit();
    }

    public WebElement getTitle() {
        return title;
    }
}
