package base.utils;

import logger.Logger;
import logger.LoggerFactory;
import main.Runner;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Handler implements WebDriver {

    private static Logger logger = LoggerFactory.getLogger();

    private static volatile Handler instance;

    private WebDriver m_driver;

    public Handler(WebDriver driver) {
        m_driver = driver;
    }

    public Handler() {
    }

    /**
     * create only 1 instance of DBUtils, to open only 1 connection to database
     * lazy initialization
     * high performance
     *
     * @return localInstance
     */
    public static Handler getInstance() {
        Handler localInstance = instance;
        if (localInstance == null) {
            synchronized (Handler.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Handler();
                }
            }
        }
        return localInstance;
    }

    public String takeScreenshot(String fileName) {
        StringBuilder msg = new StringBuilder("Trying perform takeScreenshot action with name: ").append(fileName);
        StringBuilder screenshotPath = new StringBuilder(Utils.getPathToScreenshots()).append(System.currentTimeMillis());

        File scrFile = ((TakesScreenshot) m_driver).getScreenshotAs(OutputType.FILE);
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
        } finally {
            return b;
        }
    }


    public void waitForPageToLoad(int timeout) {
        long end = System.currentTimeMillis() + timeout;
        while (System.currentTimeMillis() < end) {
            WebElement loadingPage = findElement(By.partialLinkText("Do not click the refresh, back or stop button."));

            if (!loadingPage.isDisplayed()) {
                break;
            }
        }
    }

    public void verifyURLNotProduction() {
        Assert.assertFalse(isTextPresent("//*[contains(text, 'pointed to Production')]"));
    }

    public void open(String URL) {
        get(URL);
        verifyURLNotProduction();
        logger.debug(String.format("Page at %s is opened", URL), null);
    }

    public void start(String host, String port, String browserType, String URL) {
        new Runner().run(host, port, browserType, URL);
    }


    @Override
    public void get(String url) {
        logger.debug("Opening url..." + url);
        m_driver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        String currentUrl = m_driver.getCurrentUrl();
        logger.debug("Current URL: " + currentUrl);
        return currentUrl;
    }

    @Override
    public String getTitle() {
        String title = m_driver.getTitle();
        logger.debug("Page title: " + title);
        return title;
    }

    @Override
    public List<WebElement> findElements(By by) {
        return m_driver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return m_driver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return m_driver.getPageSource();
    }

    @Override
    public void close() {
        logger.debug("Close the current window, quitting the browser if it's the last window currently open.");
        m_driver.close();
    }

    @Override
    public void quit() {
        logger.debug("Quits this driver, closing every associated window.");
        m_driver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return m_driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return m_driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        logger.debug("Invoking swithTo");
        return m_driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        logger.debug("Invoking navigation");
        return m_driver.navigate();
    }

    @Override
    public Options manage() {
        return m_driver.manage();
    }
}
