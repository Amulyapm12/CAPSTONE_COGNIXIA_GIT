package parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountsOverviewPage {

    private WebDriver driver;
    private By accountsOverviewTitle = By.xpath("//h1[contains(text(), 'Accounts Overview')]");

    public AccountsOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyAccountsOverviewVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountsOverviewTitle));
    }
}
