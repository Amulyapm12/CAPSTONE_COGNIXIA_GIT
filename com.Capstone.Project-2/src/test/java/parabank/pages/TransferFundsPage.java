package parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TransferFundsPage {

    private WebDriver driver;

    private By transferFundsLink = By.linkText("Transfer Funds");
    private By amountField = By.id("amount");
    private By fromAccountDropdown = By.id("fromAccountId");
    private By toAccountDropdown = By.id("toAccountId");
    private By transferButton = By.xpath("//input[@value='Transfer']");
    private By successMessage = By.xpath("//h1[contains(text(), 'Transfer Complete')]");

    public TransferFundsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToTransferFunds() {
        driver.findElement(transferFundsLink).click();
    }

    public void enterAmount(String amount) {
        driver.findElement(amountField).clear();
        driver.findElement(amountField).sendKeys(amount);
    }

    public void selectFromAccount(String value) {
        org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(driver.findElement(fromAccountDropdown));
        select.selectByVisibleText(value);
    }

    public void selectToAccount(String value) {
        org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(driver.findElement(toAccountDropdown));
        select.selectByVisibleText(value);
    }

    public void clickTransfer() {
        driver.findElement(transferButton).click();
    }

    public boolean isTransferSuccessVisible() {
        return driver.findElement(successMessage).isDisplayed();
    }
}
