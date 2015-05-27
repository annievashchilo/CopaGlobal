package base.utils.elements;

import base.utils.Utils;
import base.utils.elements.containers.AbstractContainer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import tests.TestData;

public class SearchForm extends AbstractContainer
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


    public void fillForm()
    {
        String numberOfAdults = "2";
        setDestinations(TestData.Destinations.DESTINATION_FROM, TestData.Destinations.DESTINATION_TO);

        setDates(TestData.Dates.YEAR, TestData.Dates.DEPARTURE_MONTH, TestData.Dates.DEPARTURE_DAY,
                TestData.Dates.YEAR, TestData.Dates.ARRIVAL_MONTH, TestData.Dates.ARRIVAL_DAY);
        adults.selectByValue(numberOfAdults);

        search.click();          // proceed with search data

        Utils.getHandler().waitForPageToLoad(5000);
    }

    public void setDates(String departureYear, String departureMonth, String departureDay,
                         String arrivalYear, String arrivalMonth, String arrivalDay)
    {

        String datePath = "div[id='calendar1Dialog'] * a[onclick*='calendar.setDay({year:[YEAR],month:[MONTH],day:[DAY]})']";

        String departDate = datePath
                .replace("[YEAR]", departureYear)
                .replace("[MONTH]", departureMonth)
                .replace("[DAY]", departureDay);

        String returnDate = datePath
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
