package tests;

import org.testng.annotations.*;


public class TestFlights_RT extends BaseTest
{


    @Parameters({"host", "port", "browserType", "URL"})
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("") String host, @Optional("") String port,
                      @Optional("") String browserType, @Optional("") String URL)
    {
        // The Firefox driver supports javascript

        handler.start(host, port, browserType, URL);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass()
    {
        // close the browser instance
        handler.quit();
    }


    @Test
    public void test()
    {

        logger.info("Starting testcase TestCopaRoundTrip");
    }


}
