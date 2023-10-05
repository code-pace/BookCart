package pageTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.Base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class HomePageTest extends Base {
    List<Map<String, String>> bookName = new ArrayList<>();


    public Boolean filterAction(WebElement element) {
        List<Map<String, String>> books = new ArrayList<>();
        AtomicReference<Boolean> flag = new AtomicReference<>(true);
        int allSize = element
                .findElements(By.xpath("//div[contains(@class,'p-1 ng-star-inserted')]")).size();
        for(int x = 1; allSize >= x; x++) {
            String title = element.findElement(By.xpath("//div[contains(@class,'p-1 ng-star-inserted')]["+x+"]//strong")).getText();
            String price = element.findElement(By.xpath("//div[contains(@class,'p-1 ng-star-inserted')]["+x+"]//p")).getText();
            Map<String, String> item = new HashMap<>();
            item.put("title", title);
            item.put("price", price);
            books.add(item);
        }

        System.out.println("books: "+books);
        for (int x = 0; books.size() > x; x++) {
            if (!bookName.contains(books.get(x))) {
                flag.set(false);
            }
        }
        return flag.get();
    }
    @Test
    public void filterAllCategoryTest() {
        HomePage homePage = new HomePage(getDriver(), wait);
        WebElement element = homePage.filterAllCategory();
        int allSize = element
                .findElements(By.xpath("//div[contains(@class,'p-1 ng-star-inserted')]")).size();
        for(int x = 1; allSize >= x; x++) {
            String title = element.findElement(By.xpath("//div[contains(@class,'p-1 ng-star-inserted')]["+x+"]//strong")).getText();
            String price = element.findElement(By.xpath("//div[contains(@class,'p-1 ng-star-inserted')]["+x+"]//p")).getText();
            Map<String, String> item = new HashMap<>();
            item.put("title", title);
            item.put("price", price);
            bookName.add(item);
        }
        System.out.println("AllCategoryBooks: "+bookName);
        Assert.assertTrue(bookName.size() > 1);
    }
    @Test
    public void filterByBiographyTest() {
        HomePage homePage = new HomePage(getDriver(), wait);
        Assert.assertTrue(filterAction(homePage.filterByBiography()));
    }
    @Test
    public void filterByFictionTest() {
        HomePage homePage = new HomePage(getDriver(), wait);
        Assert.assertTrue(filterAction(homePage.filterByFiction()));
    }
    @Test
    public void filterByMysteryTest() {
        HomePage homePage = new HomePage(getDriver(), wait);
        Assert.assertTrue(filterAction(homePage.filterByMystery()));
    }
    @Test
    public void filterByFantasyTest() {
        HomePage homePage = new HomePage(getDriver(), wait);
        Assert.assertTrue(filterAction(homePage.filterByFantasy()));
    }
    @Test
    public void filterByRomanceTest() {
        HomePage homePage = new HomePage(getDriver(), wait);
        Assert.assertTrue(filterAction(homePage.filterByRomance()));
    }
    @Test
    public void searchByBookTest() {
        HomePage homePage = new HomePage(getDriver(), wait);
        // No existing book
        String name = "Selenium Automation";
        String book = (String) homePage.invalidSearch(name);
        Assert.assertEquals(book, "No Result", "No book named "+name+" found");

        // Valid book name
        name = "Rot & Ruin";
        WebElement validBook = homePage.validSearch(name);
        validBook.click();
        WebElement element = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[contains(@class,'p-1 ng-star-inserted')][1]")));
        String text = element.findElement(By.xpath("//div[@class='card-title']//strong")).getText();
        Assert.assertEquals(name, text);
    }
    @Test
    public void searchByAuthorTest() {
        HomePage homePage = new HomePage(getDriver(), wait);
        // No book by author
        String name = "John Smilga";
        String author = (String) homePage.invalidSearch(name);
        Assert.assertEquals(author, "No Result", "Book by author "+name+" found");

        // Valid book by author
        name = "Elizabeth Gilbert";
        WebElement validAuthor = homePage.validSearch(name);
        validAuthor.click();
        WebElement element = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[contains(@class,'p-1 ng-star-inserted')][1]")));
        String text = element.findElement(By.xpath("//div[@class='card-title']//strong")).getText();
        Assert.assertEquals(text, "City of Girls", text+" is not written by "+ name);
    }
    
}
