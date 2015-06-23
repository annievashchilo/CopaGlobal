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


public class SearchForm extends FormDecorator
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
    protected final String pageName = "Search a flight";
    public String DATE = "//a[@onclick='calendar.setDay({year:[YEAR],month:[MONTH],day:[DAY]});return false']";


    public SearchForm(Form decoratedForm)
    {
        super(decoratedForm);
        PageFactory.initElements(Utils.getHandler(), this);
    }


    public void fill(String from, String to, String isOW, String tripType)
    {
        logger.info("Fill the page:\n" + getPageName());
        String numberOfAdults = "1";

        setTripType(tripType);

        setDestinations(from, to);

        setDates(TestData.Dates.YEAR, TestData.Dates.DEPARTURE_MONTH, TestData.Dates.DEPARTURE_DAY,
                TestData.Dates.YEAR, TestData.Dates.ARRIVAL_MONTH, TestData.Dates.ARRIVAL_DAY,
                tripType);

        setTravellers("ADT", numberOfAdults);

    }

    public void setTripType(String tripType) {
        if (tripType.equalsIgnoreCase("RT")) {
            roundTrip.click();
            logger.info("Select Rount Trip");
        } else if (tripType.equalsIgnoreCase("OW")) {
            oneWay.click();
            logger.info("Select One Way");
        } else if (tripType.equalsIgnoreCase("MC")) {
            multiCity.click();
            logger.info("Select Multi City");
        } else {
            logger.error("Unknown provided trip type");
        }
    }

    public void setTravellers(String type, String amount) {
        if (type.equalsIgnoreCase("ADT")) {
            Select dropdownADT = new Select(adults);
            dropdownADT.selectByValue(amount);
            logger.info("Select " + type + " traveller, amount: " + amount);
        } else if (type.equalsIgnoreCase("CH")) {
            Select dropdownCH = new Select(children);
            dropdownCH.selectByValue(amount);
            logger.info("Select " + type + " traveller, amount: " + amount);
        } else if (type.equalsIgnoreCase("INF")) {
            Select dropdownINF = new Select(infants);
            dropdownINF.selectByValue(amount);
            logger.info("Select " + type + " traveller, amount: " + amount);
        } else {
            logger.error("Unknown provided travellers type");
        }
    }

    @Deprecated
    @Override
    public void fill() {

    }

    public void searchFlights(String from, String to, String isOW)
    {
        fill(from, to, isOW);
        search.click();
        Utils.getHandler().waitForPageToLoad();
    }

    public void setDates(String departureYear, String departureMonth, String departureDay,
                         String arrivalYear, String arrivalMonth, String arrivalDay, String tripType)
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
            logger.info("Set departure day");

            if (!tripType.equalsIgnoreCase("OW"))
            {
                returnCalendarIcon.click();
                Utils.getHandler().findElement(By.xpath(returnDate)).click();
                logger.info("Set return day");
            }

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
    public void setDestinations(String from, String to)
    {
        try
        {
            // set origin location
            route_from.sendKeys(from);
            Thread.sleep(1000);
            route_from.sendKeys(Keys.RETURN);

            // set destination
            route_to.sendKeys(to);
            Thread.sleep(1000);
            route_to.sendKeys(Keys.RETURN);
            //            route_to.sendKeys(to);

        } catch (NoSuchElementException e)
        {
            logger.error("Route element was not found.", e);
        } catch (InterruptedException e)
        {
            logger.error(e);
        }
    }


    @Override
    public String getPageName() {
        return pageName;
    }
}