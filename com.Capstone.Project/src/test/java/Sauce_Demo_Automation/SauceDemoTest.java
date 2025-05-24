package Sauce_Demo_Automation;

import base.BaseTest;
import org.testng.annotations.*;
import org.apache.logging.log4j.Logger;
import utils.LoggerUtil;
import utils.ConfigReader;
import pages.LoginPage;
import pages.HomePage;

import static org.testng.Assert.*;

public class SauceDemoTest extends BaseTest {

    private Logger log = LoggerUtil.getLogger(SauceDemoTest.class);
    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeClass
    public void setupClass() throws InterruptedException {
        setup();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

        log.info("Starting login");
        loginPage.enterUsername(ConfigReader.get("username"));
        Thread.sleep(3000);

        loginPage.enterPassword(ConfigReader.get("password"));
        Thread.sleep(3000);

        loginPage.clickLogin();
        Thread.sleep(3000);
    }


    @AfterClass
    public void tearDown() throws InterruptedException {
        log.info("Test finished, closing browser");
        if (driver != null) {
            driver.quit();
        }
        Thread.sleep(3000);
    }

    @Test(priority = 1)
    public void verifyHomePageTitle() throws InterruptedException {
        log.info("Verifying title");
        String title = homePage.getHomePageTitle();
        log.info("Title is: " + title);
        Thread.sleep(3000);
        assertEquals(title, "Products", "Title mismatch");
        Thread.sleep(3000);
    }

    @Test(priority = 2)
    public void verifyProductItemsDisplayed() throws InterruptedException {
        log.info("Checking product visibility");
        assertTrue(homePage.areProductsDisplayed(), "Products not visible");
        Thread.sleep(3000);
    }

    @Test(priority = 3)
    public void addProductToCartAndVerify() throws InterruptedException {
        log.info("Adding product to cart");
        homePage.addFirstProductToCart();
        Thread.sleep(3000);
        int count = homePage.getCartBadgeCount();
        log.info("Cart count: " + count);
        assertEquals(count, 1, "Cart count incorrect");
        Thread.sleep(3000);
    }
}
