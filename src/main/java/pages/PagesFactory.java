package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;


public class PagesFactory {
    @FindBy(xpath = "//div[@class='brand-title']/button")
    WebElement brandTitle;

    @FindBy(xpath = "//mat-icon[text()='shopping_cart']")
    WebElement cart;

    @FindBy(xpath = "//button/span[text()='Login']")
    WebElement loginBtn;

    @FindBy(xpath = "//button[@mattooltip='Select a theme for the site']")
    WebElement theme;

    @FindBy(xpath = "//a[text()='All Categories']")
    @CacheLookup
    WebElement allCategories;

    @FindBy(xpath = "//a[text()='Biography']")
    @CacheLookup
    WebElement biography;

    @FindBy(xpath = "//a[text()='Fiction']")
    @CacheLookup
    WebElement fiction;

    @FindBy(xpath = "//a[text()='Mystery']")
    @CacheLookup
    WebElement mystery;

    @FindBy(xpath = "//a[text()='Fantasy']")
    @CacheLookup
    WebElement fantasy;

    @FindBy(xpath = "//a[text()='Romance']")
    @CacheLookup
    WebElement romance;
}
