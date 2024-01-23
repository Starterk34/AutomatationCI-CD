package Atola.tests.PageObjects;

import Atola.tests.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckOutPage extends AbstractComponent {
    WebDriver driver;
    public CheckOutPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = "input[placeholder='Select Country']")
    WebElement selectCountry;
    @FindBy(css = "button[type='button']")
    List<WebElement> countries;
    @FindBy(css = "a.btnn.action__submit.ng-star-inserted")
    WebElement placeOrder;

    public void placeOrder (String type, String country)
    {
       selectCountry.sendKeys(type);
       WebElement UK = countries.stream().filter(country1->country1.findElement(By.cssSelector("span.ng-star-inserted")).getText()
              .equalsIgnoreCase(country)).findFirst().orElse(null);
       UK.click();
       placeOrder.click();

    }


}
