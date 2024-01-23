package Atola.tests.AbstractComponents;

import Atola.tests.PageObjects.CartPage;
import Atola.tests.PageObjects.OrdersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {
    WebDriver driver;
    @FindBy(css = "button[routerlink*='cart']")
    WebElement goToCart;
    @FindBy(css = "button[routerlink*='orders']")
    WebElement goToOrders;
    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForElementToAppear(By findBy)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
    public void waitForWebelEmentToAppear(WebElement findBy)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }
    public void waitForElementToDisappear(WebElement ele)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }
    public CartPage goToCart()
    {
        goToCart.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }
    public OrdersPage goToOrders()
    {
        goToOrders.click();
        OrdersPage ordersPage = new OrdersPage(driver);
        return ordersPage;
    }

}
