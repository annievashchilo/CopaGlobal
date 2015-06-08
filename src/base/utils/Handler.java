package base.utils;

import logger.Logger;
import logger.LoggerFactory;
import main.Runner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class Handler extends RemoteWebDriver
{

    private static Logger logger = LoggerFactory.getLogger();

    private static Handler instance;

    public RemoteWebDriver m_driver;

    public Handler(RemoteWebDriver driver)
    {
        m_driver = driver;
    }


    /**
     * create only 1 instance of webdriver
     * lazy initialization
     * high performance
     *
     * @return localInstance
     */
    public static Handler getInstance()
    {
        if (instance == null)
        {
            instance = new Handler(WebDriverProvider.getWebDriver());
        }
        return instance;
    }


    public Boolean isElementPresent(By xpath)
    {
        return m_driver.findElements(xpath).size() > 0;
    }


    public boolean isTextPresent(String txtValue)
    {
        boolean b = false;
        try
        {
            b = m_driver.getPageSource().contains(txtValue);
            return b;
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        } finally
        {
            return b;
        }
    }


    public void waitForNextPageToLoad(int timeout)
    {
        long end = System.currentTimeMillis() + timeout;
        while (System.currentTimeMillis() < end)
        {
            WebElement loadingPage = m_driver.findElementByPartialLinkText(
                    "Do not click the refresh, back or stop button.");
            if (!loadingPage.isDisplayed())
            {
                break;
            }
        }
    }

    public void verifyURLNotProduction()
    {
        Assert.assertFalse(isTextPresent("//*[contains(text, 'pointed to Production')]"));
        logger.info("Opened UI is not production");
    }

    public void open(String URL)
    {
        m_driver.get(URL);
        verifyURLNotProduction();
        logger.debug(String.format("Page at %s is opened", URL), null);
    }

    public void start(String host, String port, String browserType, String URL)
    {
        new Runner().run(host, port, browserType, URL);
    }


}
