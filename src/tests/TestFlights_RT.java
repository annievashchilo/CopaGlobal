package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TestFlights_RT extends BaseTest {

    protected WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        // The Firefox driver supports javascript
        driver = new FirefoxDriver();

        verifyURLNotProduction();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        // close the browser instance
        driver.quit();
    }


    @Test
    public void test() {
    }

    public void setDates(String departureYear, String departureMonth, String departureDay,
                         String arrivalYear, String arrivalMonth, String arrivalDay) {

        WebElement calendarDepart = driver.findElement(By.xpath(Locators.CALENDAR_DEPART_ON));
        calendarDepart.click();

        WebElement departureDate = driver.findElement(By.xpath(Locators.DATE
                .replace("[YEAR]", TestData.Dates.YEAR)
                .replace("[MONTH]", TestData.Dates.DEPARTURE_MONTH)
                .replace("[DAY]", TestData.Dates.DEPARTURE_DAY)));
        departureDate.click();

        WebElement calendarReturn = driver.findElement(By.xpath(Locators.CALENDAR_RETURN_ON));
        calendarReturn.click();

        WebElement arrivalDate = driver.findElement(By.xpath(Locators.DATE
                .replace("[YEAR]", TestData.Dates.YEAR)
                .replace("[MONTH]", TestData.Dates.ARRIVAL_MONTH)
                .replace("[DAY]", TestData.Dates.ARRIVAL_DAY)));
        arrivalDate.click();

    }

    /**
     * @param from YVR
     * @param to   YYZ
     */
    public void setDestinations(String from, String to) {
        WebElement elementFrom = driver.findElement(By.xpath(Locators.LOCATION_FROM));
        elementFrom.sendKeys(from); // set origin location

        WebElement elementTo = driver.findElement(By.xpath(Locators.LOCATION_TO));
        elementTo.sendKeys(to);     // set destination
    }


    public void fillSearchPage() {

        setDestinations("YVR", "YYZ");

        setDates(TestData.Dates.YEAR, TestData.Dates.DEPARTURE_MONTH, TestData.Dates.DEPARTURE_DAY,
                TestData.Dates.YEAR, TestData.Dates.ARRIVAL_MONTH, TestData.Dates.ARRIVAL_DAY);

        WebElement searchBtn = driver.findElement(By.xpath(Locators.SEARCH));
        searchBtn.click();          // proceed with search data

        waitForPageForLoad(5000);
    }

}
