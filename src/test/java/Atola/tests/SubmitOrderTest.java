package Atola.tests;

import Atola.testComponents.BaseTest;
import Atola.testComponents.Retry;
import Atola.tests.PageObjects.CartPage;
import Atola.tests.PageObjects.CheckoutPage;
import Atola.tests.PageObjects.ProductCatalogue;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName = "ADIDAS ORIGINAL";
    @Test (dataProvider = "getData", groups = {"Purchase"}, retryAnalyzer = Retry.class)
    public void submitOrder(HashMap<String,String> input) throws IOException {

        ProductCatalogue productCatalogue = landingPage.loginApp(input.get("email"), input.get("password"));
        List<WebElement> allGoods = productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("product"));
        CartPage cartPage = productCatalogue.goToCart();
        Assert.assertTrue(cartPage.isSelectedInCart(input.get("product")));
        CheckoutPage orderPage = cartPage.goToOrderPage();
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
    public String getScreenshot(String testCaseName) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot)driver;
       File source = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png"));
        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
    }
    @DataProvider
    public Object[][] getData() throws IOException {
        /*HashMap <String,String> map = new HashMap<String,String>();
        map.put("email", "terk@ukr.net");
        map.put("password", "ZZZzzz111");
        map.put("product","ADIDAS ORIGINAL");

        HashMap <String,String> map1 = new HashMap<String,String>();
        map1.put("email", "terk34@ukr.net");
        map1.put("password", "XXXxxx222");
        map1.put("product","ZARA COAT 3");*/
        List<HashMap<String,String>> data = getJsonToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Atola\\data\\PurchaseOrder.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }
}