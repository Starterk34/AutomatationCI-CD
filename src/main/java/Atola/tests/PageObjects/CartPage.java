package Atola.tests.PageObjects;

import Atola.tests.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;
    public CartPage (WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = "ul.cartWrap.ng-star-inserted")
    List<WebElement> goodsInCart;
    @FindBy(css = ".totalRow button")
    WebElement checkOutButton;
    public boolean isSelectedInCart(String productName)
    {
        Boolean match = goodsInCart.stream().anyMatch(p->p.findElement(By.tagName("h3")).getText().equalsIgnoreCase(productName));
       return match;
    }
    public CheckoutPage goToOrderPage()
    {
    checkOutButton.click();
    CheckoutPage orderPage = new CheckoutPage(driver);
    return orderPage;

    }
}
