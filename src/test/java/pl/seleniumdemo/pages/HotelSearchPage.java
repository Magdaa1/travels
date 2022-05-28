package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class HotelSearchPage {
        WebDriver driver;

        @FindBy(xpath = "//span[text()='Search by Hotel or City Name']")
        //@FindBy(xpath="//div[@id='s2id_autogen8']")
        private WebElement searchHotelSpan;

        @FindBy(xpath = "//div[@id='select2-drop']//input")
        private WebElement searchHotelInput;

        @FindBy(xpath = "//span[@class='select2-match' and text()='Dubai']")
        private WebElement hotelMatch;

        @FindBy(name = "checkin")
        private WebElement checkInput;

        @FindBy(name = "checkout")
        private WebElement checkOutInput;

        @FindBy(id = "travellersInput")
        private WebElement travellersInput;

        @FindBy(id ="adultPlusBtn")
        private WebElement  adultPlusBtn;

        @FindBy(id = "childPlusBtn")
        private WebElement childPlusBtn;

        @FindBy(xpath = "//button[text()=' Search']")
        private WebElement seatchButton;

        public HotelSearchPage(WebDriver driver){
                PageFactory.initElements(driver,this);
        }

        public void setCity(String cityName) {
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        hotelMatch.click();
        }

        public void setDates(String checkin, String checkout) {
            checkInput.sendKeys(checkin);
            checkOutInput.sendKeys(checkout);
        }

        public void setTravellers(int adultsToAdd, int childToAdd) {
        travellersInput.click();
        for (int i=0; i < adultsToAdd; i++){
                adultPlusBtn.click();
        }
        for (int i=0; i < childToAdd; i++){
                childPlusBtn.click();
        }
        }

        public void performSearch() {
        seatchButton.click();
        }
    }

