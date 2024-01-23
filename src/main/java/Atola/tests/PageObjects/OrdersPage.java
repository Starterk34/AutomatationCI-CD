package Atola.tests.PageObjects;

import Atola.tests.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage extends AbstractComponent {
    WebDriver driver;
    public OrdersPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy (xpath = "//tbody/tr/td[2]")
    List<WebElement> goodsNameInOrders;
    public boolean isDisplayedInOrders(String productName)
    {
        Boolean match = goodsNameInOrders.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
        return match;
    }

}
