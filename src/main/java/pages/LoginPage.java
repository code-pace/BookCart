package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends PagesFactory {
    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    synchronized void login(String userName, String passWord) {
        Actions actions = new Actions(driver);
        WebElement username = driver.findElement(By.xpath("//input[@formcontrolname='username']"));
        username.clear();
        actions.click(username).sendKeys(username, userName).perform();
        WebElement password = driver.findElement(By.xpath("//input[@formcontrolname='password']"));
        password.clear();
        actions.click(password).sendKeys(password, passWord).perform();
    }

    public String loginError(String username, String password) {
        loginBtn.click();
        login(username, password);
        driver.findElement(By.xpath("//mat-card-actions/button/span[text()='Login']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        return driver.findElement(By.xpath("//mat-error")).getText();
    }


    public String loginValidCredentials(String username, String password) {
        loginBtn.click();
        login(username, password);
        driver.findElement(By.xpath("//mat-card-actions/button/span[text()='Login']")).click();
        return driver.findElement(By
                .xpath("//button[contains(@class,'mat-button-base')][3]/span[@class='mat-button-wrapper']")).getText().trim();
    }
}
