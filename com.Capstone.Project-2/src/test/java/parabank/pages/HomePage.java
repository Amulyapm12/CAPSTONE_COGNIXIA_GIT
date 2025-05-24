package parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    private By accountOverviewTitle = By.xpath("//h1[text()='Accounts Overview']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAccountOverviewDisplayed() {
        return driver.findElement(accountOverviewTitle).isDisplayed();
    }
}