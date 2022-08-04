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
public class HeaderPage extends BasePage {

    private final static String BURGER_MENU_LOCATOR = "//nav[@class='bm-item-list']";

    @FindBy(xpath = "//div[@id='shopping_cart_container']/a")
    private WebElement cartLink;

    @FindBy(xpath = BURGER_MENU_LOCATOR)
    private WebElement burgerMenu;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement burgerMenuButton;

    @FindBy(xpath = "//div[@class='bm-menu']//a[text()='Logout']")
    private WebElement burgerMenuLogoutItem;

    public HeaderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BURGER_MENU_LOCATOR)));
            return true;
        } catch (TimeoutException exception) {
            log.error("The page {} was not opened, because of error {}", "Header Page", exception.getCause());
            return false;
        }
    }

    @Override
    public HeaderPage open() {
        driver.get(baseUrl + "inventory.html");
        return this;
    }

    public void openCart() {
        cartLink.click();
    }

    public void openBurgerMenu() {
        burgerMenuButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BURGER_MENU_LOCATOR)));
    }

    public void logout() {
        burgerMenuLogoutItem.click();
    }
}
