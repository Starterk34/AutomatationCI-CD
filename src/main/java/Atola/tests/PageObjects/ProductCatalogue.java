package Atola.tests.PageObjects;

import Atola.tests.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {
    WebDriver driver;
    public ProductCatalogue(WebDriver driver)
    {
        super(driver);
        //initialization
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = "div.card-body")
    List<WebElement> allGoods;
    By productsBy = By.cssSelector("div.card-body");
    By addToCart = By.cssSelector("button.btn.w-10.rounded");
    By spinner = By.cssSelector("#toast-container");
    @FindBy(css = ".ng-animating")
    WebElement successMessage;


    public List<WebElement> getProductList()
        {
            waitForElementToAppear(productsBy);
            return allGoods;
        }

        public WebElement getProductByName(String productName)
    {
        WebElement product = getProductList().stream().filter(prod -> prod.findElement(By.cssSelector("b"))
                .getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
        return product;
    }
    public void addProductToCart(String productName)
    {
        WebElement product = getProductByName(productName);
        product.findElement(addToCart).click();
        waitForElementToAppear(spinner);
        waitForElementToDisappear(successMessage);
    }




}
