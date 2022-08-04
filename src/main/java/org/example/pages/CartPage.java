package org.example.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Log4j2
public class CartPage extends BasePage {

    @FindBy(css = ".title")
    private WebElement title;

    @FindBy(id = "checkout")
    private WebElement checkout;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    private List<WebElement> allProductsInCart;


    private final String productInCart = "//div[text()='%s']//ancestor::div[@class='cart_item']";
    private final String removeButton = "//div[text()='%s']//ancestor::div[@class='cart_item']//button[text()='Remove']";
    private final String product = "//div[text()='%s']//ancestor::div[@class='cart_item']";
    private final String countOfProductsForm = "//div[text()='1']/ancestor::div[@class='cart_item']/div[@class='cart_quantity']";

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".title")));
            return true;
        } catch (TimeoutException exception) {
            log.error("The page {} was not opened, because of error {}", "Cart Page", exception.getCause());
            return false;
        }
    }

    @Override
    public CartPage open() {
        driver.get(baseUrl + "cart.html");
        return this;
    }

    public List<WebElement> getAllProductsInCart() {
        return allProductsInCart;
    }

    public void checkout() {
        checkout.click();
    }

    public void removeProductFromCart(String productName) {
        driver.findElement(By.xpath(String.format(removeButton, productName))).click();
    }

    public WebElement getTitle() {
        return title;
    }

    public void continueShopping() {
        continueShoppingButton.click();
    }

    public String getCountOfProducts() {
        return driver.findElement(By.xpath(countOfProductsForm)).getText();
    }

    public void clickRemove(String productName) {
        By fullLocatorForRemoveButton = By.xpath(String.format(removeButton, productName));
        driver.findElement(fullLocatorForRemoveButton).click();
    }

    public void changeCountOfProductsInCart(String productName, String countProducts) {
        By fullLocatorForCountOfProducts = By.xpath(String.format(countOfProductsForm, productName));
        driver.findElement(fullLocatorForCountOfProducts).clear();
        log.info("The element {} was cleared successfully", fullLocatorForCountOfProducts);
        driver.findElement(fullLocatorForCountOfProducts).sendKeys(countProducts);
        log.info("The count of products was successfully changed");
    }
}