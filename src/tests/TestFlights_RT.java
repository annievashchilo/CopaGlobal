package tests;

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
    public void testRoundTripFlight()
    {
        logger.info("Starting testcase TestWestJetFlight_RoundTrip");
        sp.search(TestData.Destinations.ROUTE_FROM, TestData.Destinations.ROUTE_TO);

    }


}
