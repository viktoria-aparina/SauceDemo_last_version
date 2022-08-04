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
            log.error("The page {} was not opened, because of error {}", "CheckoutOverview Page", exception.getCause());
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

    public WebElement getBackHomeButton() {
        return backHomeButton;
    }
}
