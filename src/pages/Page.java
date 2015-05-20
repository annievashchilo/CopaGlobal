package pages;

import org.openqa.selenium.WebDriver;


public class Page {

    public static final String URL = "http://pstwestjet.datalex.com/InternetBooking/AirSearchExternalForward.do?pos=WESTJET";

    protected final WebDriver m_driver;

    public Page(WebDriver driver) {
        m_driver = driver;
    }

    public WebDriver getDriver() {
        return m_driver;
    }

    public void open() {
        getDriver().get(URL);
    }


}
