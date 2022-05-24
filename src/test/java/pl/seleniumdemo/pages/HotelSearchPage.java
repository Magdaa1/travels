package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class HotelSearchPage {
        @FindBy(xpath = "//span[text()='Search by Hotel or City Name']")
        private WebElement searchHotelSpan;

        @FindBy(xpath = "//div[@id='select2-drop']//input[1]")
        private WebElement searchHotelInput;

        @FindBy(xpath = "//span[@class='select2-match' and text()='Dubai']")
        private WebElement hotelMatch;

        @FindBy(name = "checkin")
        private WebElement checkInput;

        @FindBy(name = "checkout")
        private WebElement checkOut;

        @FindBy(id = "travellersInput")
        private WebElement travellersInput;

        @FindBy(id ="adultPlusBtn")
        private WebElement  adultPlusBtn;

        @FindBy(id = "childPlusBtn")
        private WebElement childPlusBtn;

        @FindBy(xpath = "//button[@class='btn btn-lg btn-block btn-primary pfb0 loader' and text()=' Search']")
        private WebElement buttonSearch;

        public void setCity(String cityName) {
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        hotelMatch.click();
        }

        public void setDates(String checkin, String checkout) {
            checkInput.sendKeys(checkin);
            checkOut.sendKeys(checkout);
        }

        public void 
        driver.findElement(By.xpath()).click();
        driver.findElement(By.xpath()).sendKeys("Dubai");
        driver.findElement(By.xpath()).click();
        //driver.findElement(By.xpath("//input[@class='form input-lg dpd1']")).sendKeys("17/04/2021");
        driver.findElement(By.name()).sendKeys("17/04/2021");
        // driver.findElement(By.name("checkout")).sendKeys("20/04/2021");
        driver.findElement(By.name()).click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='30']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElement(By.id()).click();
        driver.findElement(By.id()).click();
        driver.findElement(By.id()).click();
        driver.findElement(By.xpath()).click();
        List<String> hotelNames = driver.findElements(By.xpath("//h4[contains(@class,'list_title')]//b"))
                .stream()
                .map(element -> element.getAttribute("textContent"))
                .collect(Collectors.toList());

    }
}
