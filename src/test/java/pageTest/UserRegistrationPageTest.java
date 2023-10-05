package pageTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.UserRegistrationPage;
import utils.Base;


public class UserRegistrationPageTest extends Base {
    @Test
    public void loginViewTest() {
        UserRegistrationPage userRegPage = new UserRegistrationPage(getDriver(), wait);
        userRegPage.loginView();
    }
    @Test
    public void createUserNoPasswordTest() {
        UserRegistrationPage userRegPage = new UserRegistrationPage(getDriver(), wait);
        String message = userRegPage.createUserError("philip","uzoh","philUzoh","","Male");
        Assert.assertEquals(message, "Password is required", "No Password Allowed");
    }
    @Test
    public void createUserNoUsernameTest() {
        UserRegistrationPage userRegPage = new UserRegistrationPage(getDriver(), wait);
        String message = userRegPage.createUserError("philip","uzoh","","Newuser11@","Male");
        Assert.assertEquals(message, "User Name is required", "No Username Allowed");
    }
    @Test
    public void createUserNoFirstnameTest() {
        UserRegistrationPage userRegPage = new UserRegistrationPage(getDriver(), wait);
        String message = userRegPage.createUserError("","uzoh","philUzoh","Newuser11@","Male");
        Assert.assertEquals(message, "First Name is required", "No Firstname Allowed");
    }
    @Test
    public void createUserNoLastnameTest() {
        UserRegistrationPage userRegPage = new UserRegistrationPage(getDriver(), wait);
        String message = userRegPage.createUserError("philip","","philUzoh","Newuser11@","Male");
        Assert.assertEquals(message, "Last Name is required", "No Lastname Allowed");
    }
    @Test
    public void createUserNoGenderTest() {
        UserRegistrationPage userRegPage = new UserRegistrationPage(getDriver(), wait);
        String message = userRegPage.createUserError("philip","uzoh","philUzoh","Newuser11@","");
        System.out.println("createUserNoGenderTest: " + message);
    }
    @Test
    public void createUserTest() {
        UserRegistrationPage userRegPage = new UserRegistrationPage(getDriver(), wait);
        String firstname = "philip";
        String message = userRegPage.createUser(firstname,"uzoh","philUzoh","Newuser11@","Female");
        Assert.assertEquals(message, firstname, "New User Creation Failed");
    }
}
