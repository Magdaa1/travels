package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

import java.util.List;

public class SignUpTest extends BasicTest {

    BasicTest basicTest;

    @Test
    public void SignUpTest() {

        String lastName = "Kowalski";
        int randomNumber = (int) (Math.random() * 10000);

        LoggedUserPage loggedUserPage =  new HotelSearchPage(driver)
                .openSignUpForm()
                .setFirstName("Jan")
                .setLastName(lastName)
                .setPhone("123456789")
                .setEmail("jankowalsski" + randomNumber + "@gmial.com")
                .setPassword("Test12345")
                .confirmPassword("Test12345")
                .signUp();

        Assert.assertTrue(loggedUserPage.getHeadingText().contains(lastName));
        Assert.assertTrue(loggedUserPage.getHeadingText().contains("Jan"));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Jan Kowalski");
    }

    @Test
    public void SignUpTestWithoutDataTest() {
        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()= '  Sign Up']")).get(1).click();
        driver.findElement(By.xpath("//button[@type='submit' and text()=' Sign Up']")).click();
        WebElement email = driver.findElement(By.xpath("//p[text()='The Email field is required.']"));
        WebElement password1 = driver.findElement(By.xpath("//p[text()='The Password field is required.'][1]"));
        WebElement password2 = driver.findElement(By.xpath("//p[text()='The Password field is required.'][2]"));
        WebElement firstname = driver.findElement(By.xpath("//p[text()='The First name field is required.']"));
        WebElement lastname = driver.findElement(By.xpath("//p[text()='The Last Name field is required.']"));

        Assert.assertEquals(email.getText(), "The Email field is required.");
        Assert.assertEquals(password1.getText(), "The Password field is required.");
        Assert.assertEquals(password2.getText(), "The Password field is required.");
        Assert.assertEquals(firstname.getText(), "The First name field is required.");
        Assert.assertEquals(lastname.getText(), "The Last Name field is required.");
    }

    @Test
    public void signUpEmptyFormTest() {
        SignUpPage signUpPage = new HotelSearchPage(driver).openSignUpForm();
        signUpPage.signUp();

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

        SignUpPage signUpPage  = new HotelSearchPage(driver)
                .openSignUpForm()
                .setFirstName("Jan")
                .setLastName("Kowalski")
                .setPhone("11111111")
                .setEmail("email")
                .setPassword("Test123")
                .confirmPassword("Test123");
        signUpPage.signUp();

        Assert.assertTrue(signUpPage.getAlert().contains("The Email field must contain a valid email address."));
    }
}

