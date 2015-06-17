package base.utils;

import main.Runner;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Handler extends RemoteWebDriver {

    private static Logger logger = Logger.getLogger(Handler.class.getName());

    public RemoteWebDriver m_driver;


    public Handler() {
        m_driver = WebDriverProvider.getWebDriver();
    }

    /**
     * create only 1 instance of webdriver
     * lazy initialization
     * very high performance
     *
     * @return instance of Handler class
     */
    public static Handler getInstance() {
        return HandlerHolder.HOLDER_INSTANCE;
    }

    /**
     * create only 1 instance of webdriver
     * lazy initialization
     * high performance
     *
     * @return localInstance
     */


    public String takeScreenshot(String fileName) {
        logger.debug("Trying perform takeScreenshot action with name: " + fileName);
        StringBuilder screenshotPath = new StringBuilder(Utils.getPathToScreenshots()).append(System.currentTimeMillis());

        File scrFile = m_driver.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(screenshotPath.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return screenshotPath.toString();
    }

    public Boolean isElementPresent(By xpath) {
        return findElements(xpath).size() > 0;
    }

    public boolean isTextPresent(String txtValue) {
        boolean b = false;
        try {
            b = getPageSource().contains(txtValue);
            return b;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
            return b;
    }

    public void waitForPageToLoad()
    {
        sleep(30000);
    }

    public void sleep(int millis)
    {
        try
        {
            Thread.sleep(millis);
        } catch (InterruptedException e)
        {
            logger.trace(e);
        }
    }

    public void verifyURLNotProduction() {
        Assert.assertFalse(isTextPresent("//*[contains(text, 'pointed to Production')]"));
        logger.info("Opened UI is not for production");
    }

    public void open(String URL) {
        get(URL);
        waitForPageToLoad();
        verifyURLNotProduction();
        logger.info(String.format("Page at %s is opened", URL), null);
    }

    public void start(String browser)
    {
        new Runner().run(browser);
    }

    @Override
    public void get(String url) {
        logger.info("Opening url..." + url);
        m_driver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        String currentUrl = m_driver.getCurrentUrl();
        logger.info("Current URL: " + currentUrl);
        return currentUrl;
    }

    @Override
    public String getTitle() {
        String title = m_driver.getTitle();
        logger.info("Page title: " + title);
        return title;
    }

    @Override
    public List<WebElement> findElements(By by) {
        return m_driver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        logger.info("Looking for element: " + by);
        return m_driver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return m_driver.getPageSource();
    }

    @Override
    public void close() {
        logger.info("Close the current window, quitting the browser if it's the last window currently open.");
        m_driver.close();
    }

    @Override
    public void quit() {
        logger.info("Quits this driver, closing every associated window.");
        m_driver.quit();
    }

    public void highlightElement(RemoteWebDriver driver, WebElement element)
    {
        String bg = element.getCssValue("backgroundColor");
        JavascriptExecutor js = driver;
        js.executeScript("arguments[0].style.backgroundColor = '" + "red" + "'", element);
        sleep(2000);
        js.executeScript("arguments[0].style.backgroundColor = '" + bg + "'", element);
    }

    public static class HandlerHolder {
        public static final Handler HOLDER_INSTANCE = new Handler();
    }
}