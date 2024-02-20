package Atola.tests;

import Atola.tests.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandaloneTest {
    public static void main(String[]args) {
		
//test comment
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        String productName = "ADIDAS ORIGINAL";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");
        LandingPage landingPage =new LandingPage(driver);
        driver.findElement(By.id("userEmail")).sendKeys("terk@ukr.net");
        driver.findElement(By.id("userPassword")).sendKeys("ZZZzzz111");
        driver.findElement(By.id("login")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.card-body")));
        List<WebElement> allGoods = driver.findElements(By.cssSelector("div.card-body"));
       WebElement prod =  allGoods.stream().filter(product -> product.findElement(By.cssSelector("b"))
        .getText().equalsIgnoreCase("ADIDAS ORIGINAL")).findFirst().orElse(null);
       prod.findElement(By.cssSelector("button.btn.w-10.rounded")).click();
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
       wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
       driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
       List<WebElement> goodsInCart = driver.findElements(By.cssSelector("ul.cartWrap.ng-star-inserted"));
       Boolean match = goodsInCart.stream().anyMatch(p->p.findElement(By.tagName("h3")).getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);
       driver.findElement(By.cssSelector(".totalRow button")).click();
       driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("united");
       //alternative method to send keys with Action class
        /*Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.cssSelector("input[placeholder='Select Country']"))).sendKeys("united").build().perform();
         */
       List<WebElement> countries = driver.findElements(By.cssSelector("button[type='button']"));
       WebElement UK = countries.stream().filter(country->country.findElement(By.cssSelector("span.ng-star-inserted")).getText()
               .equalsIgnoreCase("United Kingdom")).findFirst().orElse(null);
       UK.click();

       driver.findElement(By.cssSelector("a.btnn.action__submit.ng-star-inserted")).click();
      Assert.assertTrue(driver.findElement(By.tagName("h1")).getText().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
      driver.quit();
    }
    }
