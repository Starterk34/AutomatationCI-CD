package Atola.testComponents;

import Atola.tests.PageObjects.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LandingPage landingPage;
    public WebDriver initializeDriver() throws IOException {
        //properties class
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Atola\\resources\\GlobalData.properties");
        properties.load(fileInputStream);
        String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :(String) properties.get("browser");
        if (browserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            if (browserName.contains("headless")) {
                options.addArguments("headless");
            }
                driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }
    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png"));
        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
    }
    public List<HashMap<String,String>> getJsonToMap(String filePath) throws IOException
    {
        //read json to String
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        //convert String to HashMap   Jackson Databind
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
        return data;

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