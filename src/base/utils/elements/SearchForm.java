package base.utils.elements;

import base.utils.elements.containers.AbstractContainer;
import org.openqa.selenium.support.FindBy;

public class SearchForm extends AbstractContainer {

    public static final String DATE = "//a[@onclick='calendar.setDay({year:[YEAR],month:[MONTH],day:[DAY]});return false']";
    @FindBy(id = "outboundOption.originLocationName")
    private TextInput route_from;
    @FindBy(id = "outboundOption.destinationLocationName")
    private TextInput route_to;
    @FindBy(id = "tripTypeRT")
    private RadioButton roundTrip;
    @FindBy(id = "tripTypeOW")
    private RadioButton oneWay;
    @FindBy(id = "tripTypeMC")
    private RadioButton multiCity;
    @FindBy(id = "guestTypes[0].amount")
    private Selector adults;
    @FindBy(id = "guestTypes[1].amount")
    private Selector children;
    @FindBy(id = "guestTypes[2].amount")
    private Selector infants;
    @FindBy(id = "departureDate1")
    private Button dateDepartOn;
    @FindBy(id = "departureDate2")
    private Button dateReturnOn;
    @FindBy(xpath = "//*[contains(@class,'botButtonSearch')]")
    private Button search;

    public void fillForm() {
        String numberOfAdults = "2";
        adults.select(numberOfAdults);
    }
}
