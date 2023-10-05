package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserRegistrationPage extends PagesFactory {
    WebDriver driver;
    WebDriverWait wait;

    public UserRegistrationPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }
    synchronized void register(String firstName, String lastName, String userName, String passWord, String gender) {
        Actions actions = new Actions(driver);
        WebElement firstname = driver.findElement(By.xpath("//input[@formcontrolname='firstname']"));
        firstname.clear();
        actions.click(firstname).sendKeys(firstname, firstName).perform();
        WebElement lastname = driver.findElement(By.xpath("//input[@formcontrolname='lastname']"));
        lastname.clear();
        actions.click(lastname).sendKeys(lastname, lastName).perform();
        WebElement username = driver.findElement(By.xpath("//input[@formcontrolname='username']"));
        username.clear();
        actions.click(username).sendKeys(username, userName).perform();
        WebElement password = driver.findElement(By.xpath("//input[@formcontrolname='password']"));
        password.clear();
        actions.click(password).sendKeys(password, passWord).perform();
        WebElement confirmPassword = driver.findElement(By.xpath("//input[@formcontrolname='confirmPassword']"));
        confirmPassword.clear();
        actions.click(confirmPassword).sendKeys(confirmPassword, passWord).perform();
        WebElement genderRadioBtn;
        if(gender.equalsIgnoreCase("female")) {
            genderRadioBtn = driver.findElement(By.xpath("//mat-radio-button[@value='"+gender+"']"));
            genderRadioBtn.click();
        }
        else if(gender.equalsIgnoreCase("male")) {
            genderRadioBtn = driver.findElement(By.xpath("//mat-radio-button[@value='"+gender+"']"));
            genderRadioBtn.click();
        }
        driver.findElement(By.xpath("//button/span[text()='Register']")).click();
    }
    public void loginView() {
        loginBtn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button/span[text()='Register']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/h3[text()='User Registration']")));
    }
    public String createUserError(String firstName, String lastName, String userName, String passWord, String gender) {
        driver.navigate().refresh();
        register(firstName, lastName, userName, passWord, gender);
        return driver.findElement(By.xpath("//mat-error")).getText();
    }
    public String createUser(String firstName, String lastName, String userName, String passWord, String gender) {
        register(firstName, lastName, userName, passWord, gender);
       return driver.findElement(By
               .xpath("//button[contains(@class,'mat-button-base')][3]/span[@class='mat-button-wrapper']")).getText().trim();
    }
}
