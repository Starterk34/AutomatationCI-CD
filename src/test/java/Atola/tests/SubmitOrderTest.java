package Atola.tests;

import Atola.testComponents.BaseTest;
import Atola.tests.PageObjects.CartPage;
import Atola.tests.PageObjects.CheckOutPage;
import Atola.tests.PageObjects.OrdersPage;
import Atola.tests.PageObjects.ProductCatalogue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName = "ADIDAS ORIGINAL";
    @Test
    public void submitOrder() throws IOException {

        ProductCatalogue productCatalogue = landingPage.loginApp("terk@ukr.net", "ZZZzzz111");
        List<WebElement> allGoods = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCart();
        Assert.assertTrue(cartPage.isSelectedInCart(productName));
        CheckOutPage orderPage = cartPage.goToOrderPage();
        orderPage.placeOrder("united", "United Kingdom");
        Assert.assertTrue(driver.findElement(By.tagName("h1")).getText().equalsIgnoreCase("THANKYOU FOR THE ORDER."));

    }
    //verify ADIDAS ORIGINAL is displayed on order page
    @Test (dependsOnMethods = {"submitOrder"})
    public void OrderHistoryTest()
    {
        ProductCatalogue productCatalogue = landingPage.loginApp("terk@ukr.net", "ZZZzzz111");
       Assert.assertTrue(productCatalogue.goToOrders().isDisplayedInOrders(productName));

    }
}