package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CartPage extends PagesFactory {

    WebDriverWait wait;
    WebDriver driver;
    By totalItemPrice = By.xpath("//mat-card-content/td[5]");
    By itemPrice = By.xpath("//mat-card-content/td[3]");
    By cartTotalPrice = By.xpath("//mat-card-content/th[4]");
    public CartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }
    public void increaseItemInCart() {
        driver.findElement(By.xpath("//mat-icon[text()='add_circle']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//simple-snack-bar/span[text()='One item added to cart']")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//simple-snack-bar/span[text()='One item added to cart']")));
    }
    public void decreaseItemInCart() {
        driver.findElement(By.xpath("//mat-icon[text()='remove_circle']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//simple-snack-bar/span[text()='One item removed from cart']")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//simple-snack-bar/span[text()='One item removed from cart']")));
    }
    public void navigateToCart() {
        driver.findElement(By.xpath("//mat-toolbar-row/div[3]/button[contains(@class,'mat-button-base')][1]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-card")));
    }
    public String getItemNameInCart() {
        return driver.findElement(By.xpath("//mat-card-content//a")).getText();
    }
    public String getItemPriceInCart() {
        return driver.findElement(totalItemPrice).getText();
    }
    public void removeItemInCart() {
        driver.findElement(By.xpath("//button[@mattooltip='Delete item']")).click();
    }
    public void clearItemInCart() {
        driver.findElement(By.xpath("//div[@class='ng-star-inserted']/button")).click();
    }
    public void continueShopping() {
        driver.findElement(By.xpath("//mat-card-content/button")).click();
    }
    public WebElement cardTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mat-card-title")));
    }
    public void goBack() {
        driver.findElement(By.xpath("//div[@class='brand-title']/button")).click();
    }
    public Map<String, String> getRandomItem(WebElement element) {
        int allSize = element
                .findElements(By.xpath("//div[contains(@class,'p-1 ng-star-inserted')]")).size();
        int x = (int) Math.floor(Math.random() * allSize);
        if(x == 0) {
            x++;
        }
        String title = element.findElement(By.xpath("//div[contains(@class,'p-1 ng-star-inserted')]["+x+"]//strong")).getText();
        String price = element.findElement(By.xpath("//div[contains(@class,'p-1 ng-star-inserted')]["+x+"]//p")).getText();
        element.findElement(By.xpath("//div[contains(@class,'p-1 ng-star-inserted')]["+x+"]//button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//simple-snack-bar/span[text()='One Item added to cart']")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//simple-snack-bar/span[text()='One Item added to cart']")));
        Map<String, String> item = new HashMap<>();
        item.put("title", title);
        item.put("price", price);
        return item;
    }
    public Double convertToNumber(String num) {
        String[] priceBuild = num.trim().substring(1).split(",");
        StringBuilder newPrice = new StringBuilder();
        for(int i = 0; priceBuild.length > i; i++) {
            newPrice.append(Arrays.asList(priceBuild).get(i));
        }
        return Double.parseDouble(String.valueOf(newPrice));
    }
    public Map<String, Double> getCurrentPrices() {
        String item_price = driver.findElement(itemPrice).getText();
        String total_item_price = driver.findElement(totalItemPrice).getText();
        String cart_total_price = driver.findElement(cartTotalPrice).getText();

        double item_price_num = convertToNumber(item_price);
        double total_item_price_num = convertToNumber(total_item_price);
        double cart_total_price_num = convertToNumber(cart_total_price);
        Map<String, Double> price = new HashMap<>();
        price.put("item_price", item_price_num);
        price.put("total_item_price", total_item_price_num);
        price.put("cart_total_price", cart_total_price_num);
        return price;
    }
    public Map<String, Double> calculatePrices(Map<String, Double> price, int count, String flag) {
        double item_price_num = price.get("item_price");
        double total_item_price_num = price.get("total_item_price");
        double newTotalItemPrice = 0;
        if(flag.equalsIgnoreCase("increase")) {
            newTotalItemPrice = total_item_price_num + (item_price_num * count);
        }
        else if(flag.equalsIgnoreCase("decrease")) {
            newTotalItemPrice = total_item_price_num - (item_price_num * count);
        }

        price.put("total_item_price", newTotalItemPrice);
        price.put("cart_total_price", newTotalItemPrice);
        return price;
    }
    public Map<String, String> addItemToCartFromAllCategory() {
        HomePage homePage = new HomePage(driver, wait);
        WebElement element = homePage.filterAllCategory();
        return getRandomItem(element);
    }
    public Map<String, String> addItemToCartFromBiography() {
        HomePage homePage = new HomePage(driver, wait);
        WebElement element = homePage.filterByBiography();
        return getRandomItem(element);
    }

    public Map<String, String> addItemToCartFromFiction() {
        HomePage homePage = new HomePage(driver, wait);
        WebElement element = homePage.filterByFiction();
        return getRandomItem(element);
    }

    public Map<String, String> addItemToCartFromMystery() {
        HomePage homePage = new HomePage(driver, wait);
        WebElement element = homePage.filterByMystery();
        return getRandomItem(element);
    }

    public Map<String, String> addItemToCartFromFantasy() {
        HomePage homePage = new HomePage(driver, wait);
        WebElement element = homePage.filterByFantasy();
        return getRandomItem(element);
    }

    public Map<String, String> addItemToCartFromRomance() {
        HomePage homePage = new HomePage(driver, wait);
        WebElement element = homePage.filterByRomance();
        return getRandomItem(element);
    }
}
