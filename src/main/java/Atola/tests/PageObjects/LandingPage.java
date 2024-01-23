package Atola.tests.PageObjects;

import Atola.tests.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {
    WebDriver driver;
    public LandingPage(WebDriver driver)
    {
        super(driver);
        //initialization
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //WebElement userEmial = driver.findElement(By.id("userEmail"));
    //PageFactory
    @FindBy(id = "userEmail")
    WebElement userEmail;
    @FindBy(id = "userPassword")
    WebElement userPassword;
    @FindBy(id="login")
    WebElement submit;
    @FindBy(css = "div.toast-message.ng-star-inserted")
    WebElement errorMessage;
    public ProductCatalogue loginApp(String email,String password)
    {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        submit.click();
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;
    }
    public String getErrorMessage()
    {
        waitForWebelEmentToAppear(errorMessage);
        String errorMessageText = errorMessage.getText();
        return errorMessageText;
    }
    public void goTo()
    {
        driver.get("https://rahulshettyacademy.com/client");
    }


}
