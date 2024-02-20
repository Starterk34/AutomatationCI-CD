package Atola.StepDefinitions;

import Atola.testComponents.BaseTest;
import Atola.tests.PageObjects.CartPage;
import Atola.tests.PageObjects.CheckoutPage;
import Atola.tests.PageObjects.LandingPage;
import Atola.tests.PageObjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImplementation extends BaseTest {
    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    @Given("I landed on Ecommerce page")
    public void I_landed_on_Ecommerce_page () throws IOException {
        landingPage = launchApplication();
    }
    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_with_username_and_password(String username, String password){
        productCatalogue = landingPage.loginApp(username,password);
    }
    @When("^I add product (.+) to Cart$")
    public void I_add_product_to_Cart(String productName) throws InterruptedException{
        List<WebElement> allGoods = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
    }
    @When("^Checkout (.+) and submit the order$")
    public void Checkout_and_submit_the_order(String productName){
        CartPage cartPage = productCatalogue.goToCart();
        Assert.assertTrue(cartPage.isSelectedInCart(productName));
        CheckoutPage orderPage = cartPage.goToOrderPage();
        orderPage.placeOrder("united", "United Kingdom");
    }
    @Then("{string} message is displayed on ConfirmationPage")
    public void message_is_displayed_on_ConfirmationPage(String string){
        Assert.assertTrue(driver.findElement(By.tagName("h1")).getText().equalsIgnoreCase(string));
        driver.close();
    }
    @Then("{string} message is displayed")
    public void message_is_displayed (String string) throws Throwable{
        Assert.assertEquals(string, landingPage.getErrorMessage());
        driver.close();
    }



}
