package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class HomePage extends PagesFactory {
    public WebDriver driver;
    public WebDriverWait wait;
    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public String checkLogo() {
        return brandTitle.getText();
    }
    synchronized void search(String bookName) {
        WebElement searchBox = driver.findElement(By.xpath("//input[@placeholder='Search books or authors']"));
        searchBox.clear();
        Actions actions = new Actions(driver);
        actions.click(searchBox).pause(Duration.ofSeconds(1)).sendKeys(searchBox, bookName).build().perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }
    public Object invalidSearch(String bookName) {
        search(bookName);
        Object searchDropdown;
        try {
            searchDropdown = wait.until(ExpectedConditions
                    .visibilityOfElementLocated(By
                            .xpath("//mat-option[contains(@id,'mat-option')]")));
            System.out.println(searchDropdown);
        }
        catch(Exception e) {
            searchDropdown = "No Result";
            System.out.println("Failed so badly....");
        }
        return searchDropdown;
    }
    public WebElement validSearch(String bookName) {
        search(bookName);
        return wait.until(ExpectedConditions
                .visibilityOfElementLocated(By
                        .xpath("//mat-option[contains(@id,'mat-option')]")));
    }


    public WebElement filter() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        return wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[contains(@class,'card-deck-container')]")));
    }
    public WebElement filterAllCategory() {
        allCategories.click();
        return filter();
    }

    public WebElement filterByBiography() {
        biography.click();
        return filter();
    }

    public WebElement filterByRomance() {
        romance.click();
        return filter();
    }

    public WebElement filterByFiction() {
        fiction.click();
        return filter();
    }

    public WebElement filterByMystery() {
        mystery.click();
        return filter();
    }
    public WebElement filterByFantasy() {
        fantasy.click();
        return filter();
    }
    public Map<String, String> themeActions() {
        Map<String, String> themeProp = new HashMap<>();
        String cardBtnColor = driver
                .findElement(By.xpath("//button[contains(@class,'mat-raised-button')]"))
                .getCssValue("background-color");
        themeProp.put("cardBtnColor", cardBtnColor);
        String navBarColor = driver
                .findElement(By.xpath("//mat-toolbar[contains(@class,'docs-navbar-header')]"))
                .getCssValue("background-color");
        themeProp.put("navBarColor", navBarColor);
        String activeCatColor = driver
                .findElement(By.xpath("//mat-list-item[contains(@class,'active-category')]"))
                .getCssValue("background-color");
        themeProp.put("activeCatColor", activeCatColor);
        String priceFilterColor = driver
                .findElement(By.xpath("//div[contains(@class,'docs-example-viewer-title mat-elevation-z2')]"))
                .getCssValue("background-color");
        themeProp.put("priceFilterColor", priceFilterColor);
        return themeProp;
    }
    public Map<String, String> changeTheme(String themeOption) throws Exception {
        theme.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mat-menu-content.ng-tns-c61-0")));
        //change to deep purple and amber
        if(themeOption.equalsIgnoreCase("Deep Purple & Amber")) {
            driver.findElement(By.xpath("//button/span[text()='Deep Purple & Amber']")).click();
        }
        else if(themeOption.equalsIgnoreCase("Indigo & Pink")) {
            driver.findElement(By.xpath("//button/span[text()='Indigo & Pink']")).click();
        }
        else if(themeOption.equalsIgnoreCase("Pink & Blue-grey")) {
            driver.findElement(By.xpath("//button/span[text()='Pink & Blue-grey']")).click();
        }
        else if(themeOption.equalsIgnoreCase("Purple & Green")) {
            driver.findElement(By.xpath("//button/span[text()='Purple & Green']")).click();
        }
        else {
            throw new Exception("theme option provided, doesn't exist.");
        }
        return themeActions();
        //Deep Purple & Amber
        //navber = #673ab7, color = #fff, activeCategory = #fb641b!important, priceFilter = #69f0ae, cardBtn = #9c27b0


        //Indigo & Pink
        //navber = #3f51b5, color = #fff, activeCategory = #fb641b!important, priceFilter = #ff4081, cardBtn = #3f51b5

        //Pink & Blue-grey
        //navber = #e91e63, color = #fff, activeCategory = #fb641b!important, priceFilter = #b0bec5, cardBtn = #e91e63

        //Purple & Green
        //navber = #9c27b0, color = #fff, activeCategory = #fb641b!important, priceFilter = #69f0ae, cardBtn = #9c27b0
    }

    public void navigateToLoginView() {
        loginBtn.click();
    }

    public void navigateToCartView() {
        cart.click();
    }
}
