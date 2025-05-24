package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    private By productTitle = By.className("title");
    private By productItems = By.className("inventory_item");
    private By addToCartButton = By.xpath("(//button[text()='Add to cart'])[1]");
    private By cartBadge = By.className("shopping_cart_badge");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getHomePageTitle() {
        return driver.findElement(productTitle).getText();
    }

    public boolean areProductsDisplayed() {
        return !driver.findElements(productItems).isEmpty();
    }

    public void addFirstProductToCart() {
        driver.findElement(addToCartButton).click();
    }

    public int getCartBadgeCount() {
        try {
            String count = driver.findElement(cartBadge).getText();
            return Integer.parseInt(count);
        } catch (Exception e) {
            return 0;
        }
    }
}
