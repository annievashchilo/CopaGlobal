package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class FlightsTest {

    private final String URL = "http://pstwestjet.datalex.com/InternetBooking/AirSearchExternalForward.do?pos=WESTJET";

    protected WebElement element;
    protected WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        // The Firefox driver supports javascript
        driver = new FirefoxDriver();

        // Go to the Google Suggest home page
        driver.get(URL);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        // close the browser instance
        driver.quit();
    }

    private void waitPageForLoad(int timeout) {
        long end = System.currentTimeMillis() + timeout;
        while (System.currentTimeMillis() < end) {
            WebElement loadingPage = driver.findElement(By.partialLinkText("Do not click the refresh, back or stop button."));

            // If results have been returned, the results are displayed in a drop down.
            if (!loadingPage.isDisplayed()) {
                break;
            }
        }
    }

    public void fillSearchPage(String from, String to) {

        WebElement elementFrom = driver.findElement(By.name("outboundOption.originLocationName"));
        elementFrom.sendKeys(from);

        WebElement elementTo = driver.findElement(By.name("outboundOption.destinationLocationName"));
        elementTo.sendKeys(to);

        WebElement searchBtn = driver.findElement(By.xpath("//*[contains(@class,'botButtonSearch')]"));
        searchBtn.click();


        waitPageForLoad(5000);
    }
}
