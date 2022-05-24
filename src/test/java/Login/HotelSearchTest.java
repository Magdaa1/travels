package Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class HotelSearchTest extends BasicTest {
    @Test
    public void searchHotelTest() {


        hotelNames.forEach(System.out::println);

        Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));
    }

    @Test
    public void searchHotelWithoutNameTest() {
        driver.findElement(By.name("checkin")).sendKeys("17/04/2021");
        driver.findElement(By.name("checkout")).click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='30']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.xpath("//button[@class='btn btn-lg btn-block btn-primary pfb0 loader' and text()=' Search']")).click();
        WebElement noResultHeader = driver.findElement(By.xpath("//h2[@class='text-center' and text()='No Results Found']"));

        Assert.assertTrue(noResultHeader.isDisplayed());
        Assert.assertEquals(noResultHeader.getText(), "No Results Found");
    }
}
