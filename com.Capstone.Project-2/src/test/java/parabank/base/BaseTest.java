package parabank.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    protected WebDriver driver;
    protected Properties prop;
    protected Logger log = LogManager.getLogger(this.getClass());

    @BeforeMethod
    public void setUp() {
        try {
            prop = new Properties();
            InputStream input = new FileInputStream("src/main/resources/config.properties");
            prop.load(input);
        } catch (Exception e) {
            log.error("Failed to load config.properties", e);
            throw new RuntimeException(e);
        }

        log.info("Initializing WebDriver");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String url = prop.getProperty("url");
        log.info("Opening URL: " + url);
        driver.get(url);
    }

    @AfterMethod
    public void tearDown() {
        log.info("Closing the browser");
        if (driver != null) {
            driver.quit();
        }
    }
}
