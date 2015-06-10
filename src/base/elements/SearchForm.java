package base.elements;

import base.utils.Utils;
import org.apache.log4j.Logger;
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

    private static Logger logger = Logger.getLogger(SearchForm.class.getName());
    @FindBy(xpath = "//input[@id='outboundOption.originLocationName']")
    private static WebElement route_from;
    @FindBy(xpath = "//input[@id='outboundOption.destinationLocationName']")
    private static WebElement route_to;
    @FindBy(xpath = "//input[@id='tripTypeRT']")
    private static WebElement roundTrip;
    @FindBy(xpath = "//input[@id='tripTypeOW']")
    private static WebElement oneWay;
    @FindBy(xpath = "//input[@id='tripTypeMC']")
    private static WebElement multiCity;
    @FindBy(xpath = "//select[@id='guestTypes[0].amount']")
    private static WebElement adults;
    @FindBy(xpath = "//select[@id='guestTypes[1].amount']")
    private static WebElement children;
    @FindBy(xpath = "//select[@id='guestTypes[3].amount']")
    private static WebElement infants;
    @FindBy(xpath = "//input[@id='departureDate1']")
    private static WebElement dateDepartOn;
    @FindBy(xpath = "//input[@id='departureDate2']")
    private static WebElement dateReturnOn;
    @FindBy(xpath = "//*[contains(@id,'drilldownItem')]")
    private static WebElement routeDropDown;
    @FindBy(xpath = "//a[@onclick='checkReservationStatus();return false;']")
    private static WebElement search;
    @FindBy(css = "td.calendarArea > div > a > img")
    private static WebElement departCalendarIcon;
    @FindBy(css = "#returnBlockDate > table > tbody > tr > td.calendarArea > div > a > img")
    private static WebElement returnCalendarIcon;
    @FindBy(xpath = "//a[contains(text(),'Close')]")
    private static WebElement closeCalendar;
    public String DATE = "//a[@onclick='calendar.setDay({year:[YEAR],month:[MONTH],day:[DAY]});return false']";


    public SearchForm()
    {
        PageFactory.initElements(Utils.getHandler(), this);
    }


    public void fillSearchForm(String from, String to)
    {
        String numberOfAdults = "2";

        roundTrip.click();
        setDestinations(from, to);

        setDates(TestData.Dates.YEAR, TestData.Dates.DEPARTURE_MONTH, TestData.Dates.DEPARTURE_DAY,
                TestData.Dates.YEAR, TestData.Dates.ARRIVAL_MONTH, TestData.Dates.ARRIVAL_DAY);

        Select dropdownADT = new Select(adults);
        dropdownADT.selectByValue(numberOfAdults);
    }

    public void search(String from, String to)
    {
        fillSearchForm(from, to);

        search.click();
    }

    public void setDates(String departureYear, String departureMonth, String departureDay,
                         String arrivalYear, String arrivalMonth, String arrivalDay)
            throws NoSuchElementException
    {
        logger.info("Setting dates");
        try
        {

            String departDate = DATE
                    .replace("[YEAR]", departureYear)
                    .replace("[MONTH]", departureMonth)
                    .replace("[DAY]", departureDay);

            String returnDate = DATE
                    .replace("[YEAR]", arrivalYear)
                    .replace("[MONTH]", arrivalMonth)
                    .replace("[DAY]", arrivalDay);


            departCalendarIcon.click();
            Utils.getHandler().findElement(By.xpath(departDate)).click();

            returnCalendarIcon.click();
            Utils.getHandler().findElement(By.xpath(returnDate)).click();

            if (closeCalendar.isDisplayed())
            {
                closeCalendar.click();
            }
        } catch (NoSuchElementException e)
        {
            logger.trace(e);
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
                    route_from, from);
            route_from.sendKeys(Keys.RETURN);

            // set destination
            Utils.getHandler().m_driver.executeScript("arguments[0].setAttribute('value', arguments[1])",
                    route_to, to);
            route_from.sendKeys(Keys.RETURN);
            //            route_to.sendKeys(to);

        } catch (NoSuchElementException e)
        {
            logger.error("Route element was not found.", e);
        }
    }
}