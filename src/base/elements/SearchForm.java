package base.elements;

import base.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import tests.TestData;

public class SearchForm
{

    public static final String DATE = "//a[@onclick='calendar.setDay({year:[YEAR],month:[MONTH],day:[DAY]});return false']";
    @FindBy(id = "outboundOption.originLocationName")
    private WebElement route_from;
    @FindBy(id = "outboundOption.destinationLocationName")
    private WebElement route_to;
    @FindBy(id = "tripTypeRT")
    private WebElement roundTrip;
    @FindBy(id = "tripTypeOW")
    private WebElement oneWay;
    @FindBy(id = "tripTypeMC")
    private WebElement multiCity;
    @FindBy(id = "guestTypes[0].amount")
    private Select adults;
    @FindBy(id = "guestTypes[1].amount")
    private Select children;
    @FindBy(id = "guestTypes[2].amount")
    private Select infants;
    @FindBy(id = "departureDate1")
    private WebElement dateDepartOn;
    @FindBy(id = "departureDate2")
    private WebElement dateReturnOn;

    @FindBy(xpath = "//*[contains(@class,'botButtonSearch')]")
    private WebElement search;


    public void fillForm(String from, String to)
    {
        String numberOfAdults = "2";
        setDestinations(from, to);

        setDates(TestData.Dates.YEAR, TestData.Dates.DEPARTURE_MONTH, TestData.Dates.DEPARTURE_DAY,
                TestData.Dates.YEAR, TestData.Dates.ARRIVAL_MONTH, TestData.Dates.ARRIVAL_DAY);
        adults.selectByValue(numberOfAdults);
    }

    public void search(String from, String to)
    {
        fillForm(from, to);

        search.click();          // proceed with search data

        Utils.getHandler().waitForPageToLoad(5000);
    }

    public void setDates(String departureYear, String departureMonth, String departureDay,
                         String arrivalYear, String arrivalMonth, String arrivalDay)
    {

        String departDate = DATE
                .replace("[YEAR]", departureYear)
                .replace("[MONTH]", departureMonth)
                .replace("[DAY]", departureDay);

        String returnDate = DATE
                .replace("[YEAR]", arrivalYear)
                .replace("[MONTH]", arrivalMonth)
                .replace("[DAY]", arrivalDay);

        dateDepartOn.click();
        Utils.getHandler().findElement(By.xpath(departDate)).click();

        dateReturnOn.click();
        Utils.getHandler().findElement(By.xpath(returnDate)).click();
    }

    /**
     * @param from YVR
     * @param to   YYZ
     */
    public void setDestinations(String from, String to)
    {
        route_from.sendKeys(from); // set origin location
        route_to.sendKeys(to);     // set destination
    }
}
