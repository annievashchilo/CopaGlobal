package tests;

public class Locators {


    /**
     * locators for Search page
     */
    public static final String LOCATION_FROM = "//input[@id='outboundOption.originLocationName']";
    public static final String LOCATION_TO = "//input[@id='outboundOption.destinationLocationName']";

    public static final String ROUND_TRIP = "//*[@id='tripTypeRT']";
    public static final String ONEWAY = "//*[@id='tripTypeOW']";
    public static final String MULTI_CITY = "//*[@id='tripTypeMC']";

    public static final String ADULTS = "id=guestTypes[0].amount";
    public static final String CHILDREN = "id=guestTypes[1].amount";
    public static final String INFANTS = "id=guestTypes[2].amount";

    public static final String CALENDAR_DEPART_ON = "//input[@id='departureDate1']";
    public static final String CALENDAR_RETURN_ON = "//input[@id='departureDate2']";
    public static final String DATE = "//a[@onclick='calendar.setDay({year:[YEAR],month:[MONTH],day:[DAY]});return false']";

    public static final String SEARCH = "//*[contains(@class,'botButtonSearch')]";


}
