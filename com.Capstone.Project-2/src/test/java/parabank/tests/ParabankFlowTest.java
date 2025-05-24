package parabank.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import parabank.base.BaseTest;
import parabank.pages.LoginPage;

import java.time.Duration;
import java.util.List;

public class ParabankFlowTest extends BaseTest {

    @Test
    public void loginAndTransferFundsTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Login
        LoginPage loginPage = new LoginPage(driver);
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        log.info("Attempting login with username: " + username);
        loginPage.login(username, password);

        if (loginPage.isLoginErrorPresent()) {
            log.error("Login error message displayed - login failed");
            Assert.fail("Login failed: The username and password could not be verified.");
        }

        boolean loggedIn = driver.getPageSource().contains("Log Out") || driver.getTitle().contains("ParaBank");
        Assert.assertTrue(loggedIn, "Login failed: The username and password could not be verified.");
        log.info("Login successful for user: " + username);

        // Navigate to Transfer Funds page
        WebElement transferFundsLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Transfer Funds")));
        transferFundsLink.click();
        log.info("Navigated to Transfer Funds page");

        // Enter transfer amount
        WebElement amountField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("amount")));
        amountField.clear();
        amountField.sendKeys("250");
        log.info("Entered transfer amount");

        // Log available accounts and select first one for both from and to account
        Select fromAccountSelect = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fromAccountId"))));
        List<WebElement> fromOptions = fromAccountSelect.getOptions();
        log.info("Available From Accounts:");
        for (WebElement option : fromOptions) {
            log.info(option.getText());
        }
        fromAccountSelect.selectByIndex(0); // Select first available account

        Select toAccountSelect = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toAccountId"))));
        List<WebElement> toOptions = toAccountSelect.getOptions();
        log.info("Available To Accounts:");
        for (WebElement option : toOptions) {
            log.info(option.getText());
        }
        toAccountSelect.selectByIndex(0); // Select first available account

        // Click Transfer
        WebElement transferButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Transfer']")));
        transferButton.click();

        // Verify transfer success
        WebElement confirmationMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Transfer Complete')]")));
        Assert.assertTrue(confirmationMsg.isDisplayed(), "Transfer was not successful.");
        log.info("Transfer successful");
    }
}
