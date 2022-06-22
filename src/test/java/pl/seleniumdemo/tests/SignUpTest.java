package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;
import pl.seleniumdemo.tests.BasicTest;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.util.List;
import java.util.stream.Collectors;

public class SignUpTest extends BasicTest {

    BasicTest basicTest;

    @Test
    public void signUpTest() {

        String firstName = "Jan";
        String lastName = "Kowalski";
        int randomNumber = (int) (Math.random() * 10000);
        String email = "jankowalsski" + randomNumber + "@gmial.com";

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();
        //System.out.println(driver.findElement(By.xpath("//input[@name='firstname']")));

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName(firstName);
        signUpPage.setLastName(lastName);
        signUpPage.setPhone("123456789");
        signUpPage.setEmail(email);
        signUpPage.setPassword("Test12345");
        signUpPage.confirmPassword("Test12345");
        signUpPage.signUp();

        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);
        Assert.assertTrue(loggedUserPage.getHeadingText().contains(lastName));
        Assert.assertTrue(loggedUserPage.getHeadingText().contains(firstName));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Jan Kowalski");
    }


    @Test
    public void signUpEmptyFormTest() {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.signUp();

        SeleniumHelper.waitForNotEmptyList(driver,By.xpath("//div[@class='alert alert-danger']//p"));
        List<String> errors = signUpPage.getAlert();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errors.contains("The Email field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The First name field is required."));
        softAssert.assertTrue(errors.contains("The Last Name field is required."));
        softAssert.assertAll();
    }

    @Test
    public void signUpInvalidEmailTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Jan");
        signUpPage.setLastName("Kowalski");
        signUpPage.setPhone("11111111");
        signUpPage.setEmail("email");
        signUpPage.setPassword("Test123");
        signUpPage.confirmPassword("Test123");
        signUpPage.signUp();
        signUpPage.getAlert();

        Assert.assertTrue(signUpPage.getAlert().contains("The Email field must contain a valid email address."));
    }
}

