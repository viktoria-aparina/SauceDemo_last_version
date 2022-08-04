package org.example.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class ProductsPage extends BasePage {

    private static final By TITLE = By.cssSelector(".title");

    @FindBy(css = ".title")
    private WebElement title;

    @FindBy(className = "product_sort_container")
    private WebElement sort;

    private final String addToCartButton = "//div[text()='%s']/ancestor::div[@class='inventory_item']//button";

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".title")));
            return true;
        } catch (TimeoutException exception) {
            log.error("The page {} was not opened, because of error {}", "Products Page", exception.getCause());
            return false;
        }
    }

    @Override
    public ProductsPage open() {
        driver.get(baseUrl + "inventory.html");
        return this;
    }

    public By getTitleLocator() {
        return TITLE;
    }

    public void addToCart(String productName) {
        By fullLocator = By.xpath(String.format(addToCartButton, productName));
        driver.findElement(fullLocator).click();
        log.info("The product {} was added to cart", fullLocator);
    }

    public String findRemoveButtonOnProductPage(String productName) {
        By fullLocator = By.xpath(String.format(addToCartButton, productName));
        return driver.findElement(fullLocator).getText();
    }

    public void addToCart(String... productsName) {
        Arrays.asList(productsName).forEach(this::addToCart);
    }

    public WebElement getTitle() {
        return title;
    }
}
