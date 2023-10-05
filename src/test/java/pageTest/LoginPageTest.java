package pageTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.Base;

public class LoginPageTest extends Base {
    @Test
    public void loginNoPasswordTest() {
        LoginPage loginPage = new LoginPage(getDriver(),wait);
        String message = loginPage.loginError("qatest", "");
        Assert.assertEquals(message, "Password is required", "No Password Test Failed");
    }
    @Test
    public void loginInvalidUsernameTest() {
        LoginPage loginPage = new LoginPage(getDriver(), wait);
        String message = loginPage.loginError("qates", "Newuser11@");
        Assert.assertEquals(message, "Username or Password is incorrect.", "Invalid Username Test Failed");
    }
    @Test
    public void loginInvalidPasswordTest() {
        LoginPage loginPage = new LoginPage(getDriver(), wait);
        String message = loginPage.loginError("qatest", "Newuser33@");
        Assert.assertEquals(message, "Username or Password is incorrect.", "Invalid Password Test Failed");
    }
    @Test
    public void loginValidCredentialsTest() {
        LoginPage loginPage = new LoginPage(getDriver(), wait);
        String message = loginPage.loginValidCredentials("qatest", "Newuser11@");
    }
}
