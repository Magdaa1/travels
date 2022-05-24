package Login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SignUpTest extends BasicTest {

    BasicTest basicTest;

    @Test
    public void SignUpTest() {

        String firstName = "Jan";
        String lastName = "Kowalski";
        int randomNumber = (int) (Math.random() * 1000);
        String email = "jankowalski" + randomNumber + "@gmial.com";
        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()= '  Sign Up']")).get(1).click();
        //System.out.println(driver.findElement(By.xpath("//input[@name='firstname']")));
        driver.findElement(By.name("firstname")).sendKeys(firstName);
        driver.findElement(By.name("lastname")).sendKeys(lastName);
        driver.findElement(By.name("phone")).sendKeys("1111111");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("Test123");
        driver.findElement(By.name("confirmpassword")).sendKeys("Test123");
        driver.findElement(By.xpath("//button[@type='submit' and text()=' Sign Up']")).click();


        WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));
        Assert.assertTrue(heading.getText().contains(lastName));
        Assert.assertTrue(heading.getText().contains(firstName));
        Assert.assertEquals(heading.getText(), "Hi, Jan Kowalski");
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
    public void signUpEmptyForceTest() {
        String firstName = "Jan";
        String lastName = "Kowalski";
        int randomNumber = (int) (Math.random() * 1000);
        String email = "jankowalski" + randomNumber + "@gmial.com";
        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()= '  Sign Up']")).get(1).click();
        driver.findElement(By.xpath("//button[@type='submit' and text()=' Sign Up']")).click();
        List<String> errors =
                driver.findElements(By.xpath("//div[@class='alert alert-danger']//p"))
                        .stream()
                        .map(WebElement::getText)
                        .collect(Collectors.toList());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errors.contains("The Email field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The First name field is required."));
        softAssert.assertTrue(errors.contains("The Last Name field is required."));
        softAssert.assertAll();
    }

    @Test
    public void signUpInvalidEmialTest() {
        String firstName = "Jan";
        String lastName = "Kowalski";
        int randomNumber = (int) (Math.random() * 1000);
        String email = "jankowalski" + randomNumber;
        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()= '  Sign Up']")).get(1).click();
        //System.out.println(driver.findElement(By.xpath("//input[@name='firstname']")));
        driver.findElement(By.name("firstname")).sendKeys(firstName);
        driver.findElement(By.name("lastname")).sendKeys(lastName);
        driver.findElement(By.name("phone")).sendKeys("1111111");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("Test123");
        driver.findElement(By.name("confirmpassword")).sendKeys("Test123");
        driver.findElement(By.xpath("//button[@type='submit' and text()=' Sign Up']")).click();


        List<String> errors = driver.findElements(By.xpath("//div[@class='alert alert-danger']//p"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        Assert.assertTrue(errors.contains("The Email field must contain a valid email address."));
    }
}

