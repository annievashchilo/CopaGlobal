package base.elements;

import base.utils.Utils;
import logger.Logger;
import logger.LoggerFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import tests.TestData;

public class SearchForm
{
    public static final String DATE = "a[onclick*='calendar.setDay({year:[YEAR],month:[MONTH],day:[DAY]});return false']";
    private static Logger logger = LoggerFactory.getLogger();
    @FindBy(xpath = "//input[@id='outboundOption.originLocationName']")
    private WebElement route_from;
    @FindBy(xpath = "//input[@id='outboundOption.destinationLocationName']")
    private WebElement route_to;
    @FindBy(xpath = "//input[@id='tripTypeRT']")
    private WebElement roundTrip;
    @FindBy(xpath = "//input[@id='tripTypeOW']")
    private WebElement oneWay;
    @FindBy(xpath = "//input[@id='tripTypeMC']")
    private WebElement multiCity;
    @FindBy(xpath = "//select[@id='guestTypes[0].amount']")
    private Select adults;
    @FindBy(xpath = "//select[@id='guestTypes[1].amount']")
    private Select children;
    @FindBy(xpath = "//select[@id='guestTypes[3].amount']")
    private Select infants;
    @FindBy(xpath = "//input[@id='departureDate1']")
    private WebElement dateDepartOn;
    @FindBy(xpath = "//input[@id='departureDate2']")
    private WebElement dateReturnOn;
    @FindBy(xpath = "//*[contains(@id,'drilldownItem')]")
    private Select routeDropDown;
    @FindBy(xpath = "//*[contains(@class,'botButtonSearch')]")
    private WebElement search;
    @FindBy(css = "td.calendarArea > div > a > img")
    private WebElement departCalendarIcon;
    @FindBy(css = "css=#returnBlockDate > table > tbody > tr > td.calendarArea > div > a > img")
    private WebElement returnCalendarIcon;
    @FindBy(xpath = "//a[contains(text(),'Close')]")
    private WebElement closeCalendar;

    public SearchForm()
    {
        PageFactory.initElements(Utils.getHandler(), this);
    }


    public void fillSearchForm(String from, String to)
    {
        String numberOfAdults = "2";
        setDestinations(from, to);

        setDates(TestData.Dates.YEAR, TestData.Dates.DEPARTURE_MONTH, TestData.Dates.DEPARTURE_DAY,
                TestData.Dates.YEAR, TestData.Dates.ARRIVAL_MONTH, TestData.Dates.ARRIVAL_DAY);
        adults.selectByValue(numberOfAdults);
    }

    public void search(String from, String to)
    {
        fillSearchForm(from, to);

        search.click();          // proceed with search data

        Utils.getHandler().waitForNextPageToLoad(5000);
    }

    public void setDates(String departureYear, String departureMonth, String departureDay,
                         String arrivalYear, String arrivalMonth, String arrivalDay) throws NoSuchElementException
    {
        try
        {
            departCalendarIcon.click();

            String s = "a onclick=\"calendar.setDay({year:2015,month:5,day:25});return false";

            String departDate = DATE
                    .replace("[YEAR]", departureYear)
                    .replace("[MONTH]", departureMonth)
                    .replace("[DAY]", departureDay);

            String returnDate = DATE
                    .replace("[YEAR]", arrivalYear)
                    .replace("[MONTH]", arrivalMonth)
                    .replace("[DAY]", arrivalDay);

//            dateDepartOn.click();
            Utils.getHandler().findElement(By.xpath(departDate)).click();

            returnCalendarIcon.click();

//            dateReturnOn.click();
            Utils.getHandler().findElement(By.xpath(returnDate)).click();

            if (closeCalendar.isDisplayed())
            {
                closeCalendar.click();
            }
        } catch (NoSuchElementException e)
        {
            logger.debug("Date element was not found", e);
        }
    }

    /**
     * @param from YVR
     * @param to   YYZ
     */
    public void setDestinations(String from, String to) throws NoSuchElementException
    {
        try
        {
            // set origin location
            Utils.getHandler().m_driver.executeScript("arguments[0].setAttribute('value', arguments[1])",
                    route_from, "Vancouver (YVR)");
            route_from.sendKeys(Keys.RETURN);

            // set destination
            Utils.getHandler().m_driver.executeScript("arguments[0].setAttribute('value', arguments[1])",
                    route_to, "Toronto (YYZ)");
            route_from.sendKeys(Keys.RETURN);
            //            route_to.sendKeys(to);

        } catch (NoSuchElementException e)
        {
            logger.debug("Route element was not found", e);
        }
    }
}
