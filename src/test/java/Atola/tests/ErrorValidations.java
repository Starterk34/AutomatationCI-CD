package Atola.tests;

import Atola.testComponents.BaseTest;
import Atola.testComponents.Retry;
import Atola.tests.PageObjects.CartPage;
import Atola.tests.PageObjects.ProductCatalogue;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;

public class ErrorValidations extends BaseTest {
    @Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
    public void LoginErrorValidation() throws IOException, InterruptedException {

        landingPage.loginApp("terk123@ukr.net", "ZZZzzz111");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

    }
    @Test
    public void ProductErrorValidation() throws IOException {

        String productName = "ADIDAS ORIGINAL";
        ProductCatalogue productCatalogue = landingPage.loginApp("terk34@ukr.net", "XXXxxx222");
        List<WebElement> allGoods = productCatalogue.getProductList();
        productCatalogue.addProductToCart("ADIDAS ORIGINAL");
        CartPage cartPage = productCatalogue.goToCart();
        Assert.assertFalse(cartPage.isSelectedInCart("ABIBAS ORIJINAL"));
    }
}