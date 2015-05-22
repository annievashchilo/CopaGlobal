package tests;

import base.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;


public class TestFlights_RT extends BaseTest {


    @Parameters({"host", "port", "browserType", "URL"})
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("") String host, @Optional("") String port,
                      @Optional("") String browserType, @Optional("") String URL) {
        // The Firefox driver supports javascript

        handler.start(host, port, browserType, URL);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        // close the browser instance
        handler.quit();
    }


    @Test
    public void test() {
    }

    public void setDates(String departureYear, String departureMonth, String departureDay,
                         String arrivalYear, String arrivalMonth, String arrivalDay) {

        WebElement calendarDepart = handler.findElement(By.xpath(Locators.CALENDAR_DEPART_ON));
        calendarDepart.click();

        WebElement departureDate = handler.findElement(By.xpath(Locators.DATE
                .replace("[YEAR]", departureYear)
                .replace("[MONTH]", departureMonth)
                .replace("[DAY]", departureDay)));
        departureDate.click();

        WebElement calendarReturn = handler.findElement(By.xpath(Locators.CALENDAR_RETURN_ON));
        calendarReturn.click();

        WebElement arrivalDate = handler.findElement(By.xpath(Locators.DATE
                .replace("[YEAR]", arrivalYear)
                .replace("[MONTH]", arrivalMonth)
                .replace("[DAY]", arrivalDay)));
        arrivalDate.click();

    }

    /**
     * @param from YVR
     * @param to   YYZ
     */
    public void setDestinations(String from, String to) {
        WebElement elementFrom = handler.findElement(By.xpath(Locators.LOCATION_FROM));
        elementFrom.sendKeys(from); // set origin location

        WebElement elementTo = handler.findElement(By.xpath(Locators.LOCATION_TO));
        elementTo.sendKeys(to);     // set destination
    }

    public void fillSearchPage() {

        setDestinations(TestData.Destinations.DESTINATION_FROM, TestData.Destinations.DESTINATION_TO);

        setDates(TestData.Dates.YEAR, TestData.Dates.DEPARTURE_MONTH, TestData.Dates.DEPARTURE_DAY,
                TestData.Dates.YEAR, TestData.Dates.ARRIVAL_MONTH, TestData.Dates.ARRIVAL_DAY);

        WebElement searchBtn = handler.findElement(By.xpath(Locators.SEARCH));
        searchBtn.click();          // proceed with search data

        Utils.getHandler().waitForPageToLoad(5000);
    }

}
