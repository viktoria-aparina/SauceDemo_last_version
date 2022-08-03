package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutOverviewPage extends BasePage {

    @FindBy(css = ".title")
    private WebElement title;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(id = "back-to-products")
    private WebElement backHomeButton;

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".title")));
            return true;
        } catch (TimeoutException exception) {
            return false;
        }
    }

    @Override
    public CheckoutOverviewPage open() {
        driver.get(baseUrl + "checkout-step-two.html");
        return this;
    }

    public WebElement getTitle() {
        return title;
    }

    public WebElement getFinishButton() {
        return finishButton;
    }

    public WebElement getBackHomeButton() { return backHomeButton; }
}
