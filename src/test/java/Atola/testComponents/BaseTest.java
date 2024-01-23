package Atola.testComponents;

import Atola.tests.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LandingPage landingPage;
    public WebDriver initializeDriver() throws IOException {
        //properties class
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Atola\\resources\\GlobalData.properties");
        properties.load(fileInputStream);
        String browserName = (String) properties.get("browser");
        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            //code for Firefox
        } else if (browserName.equalsIgnoreCase("edge")) {
            //code for Edge
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }
@BeforeMethod (alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver = initializeDriver();
        this.landingPage =new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }
    @AfterMethod(alwaysRun = true)
    public void shutDown(){
        driver.quit();
    }
}