package org.example.pages;

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

    public void chooseAToZSort() {
        sort.click();
        WebElement AToZSortElement = driver.findElement(By.xpath("//option[contains(text(),'Name (A to Z)')]"));
        AToZSortElement.click();
    }

    public List<WebElement> getAllProducts() {
        return allProducts;
    }

    public List <WebElement> getSortedList() {
        List<WebElement> sortedList = allProducts.stream().sorted().toList();
        return sortedList;
    }

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    private List<WebElement> allProducts;

}
