package tests;

import base.pages.SearchPage;
import base.utils.Utils;
import org.testng.annotations.*;


public class TestFlights_RT extends BaseTest
{

    @Parameters({"host", "port", "browserType", "URL"})
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("") String host, @Optional("") String port,
                      @Optional("") String browserType, @Optional("") String URL)
    {
        // The Firefox driver supports javascript

        handler.start(host, port, browserType, Utils.URL);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass()
    {
        // close the browser instance
        handler.close();
    }


    @Test
    public void test()
    {
        new SearchPage().search(TestData.Destinations.DESTINATION_FROM, TestData.Destinations.DESTINATION_TO);
        logger.info("Starting testcase TestCopaRoundTrip");
    }


}
