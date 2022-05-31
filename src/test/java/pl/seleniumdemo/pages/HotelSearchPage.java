package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelSearchPage {

        @FindBy(xpath = "//span[text()='Search by Hotel or City Name']")
        private WebElement searchHotelSpan;

        @FindBy(xpath = "//div[@id='select2-drop']//input")
        private WebElement searchHotelInput;

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

        private WebDriver driver;
        public HotelSearchPage(WebDriver driver){
                PageFactory.initElements(driver,this);
                this.driver = driver;
        }


        public void setCity(String cityName) {
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        String xpath = String.format("//span[@class='select2-match' and text()='%s']",cityName);
        driver.findElement(By.xpath(xpath)).click();
        }

        public void setDates(String checkin, String checkout) {
            checkInput.sendKeys(checkin);
            checkOutInput.sendKeys(checkout);
        }

        public void setTravellers(int adultsToAdd, int childToAdd) {
        travellersInput.click();
        addTraveler(adultPlusBtn,adultsToAdd);
        addTraveler(childPlusBtn,childToAdd);
        }

        private void addTraveler (WebElement travelerBtn, int numberOfTravelers){
                for(int i=0; i< numberOfTravelers; i++){
                        travelerBtn.click();
                }
        }

        public void performSearch() {
        seatchButton.click();
        }
    }

