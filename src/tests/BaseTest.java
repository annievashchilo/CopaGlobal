package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;


public class BaseTest {

    public WebDriver driver = new FirefoxDriver();

    public void waitForPageForLoad(int timeout) {
        long end = System.currentTimeMillis() + timeout;
        while (System.currentTimeMillis() < end) {
            WebElement loadingPage = driver.findElement(By.partialLinkText("Do not click the refresh, back or stop button."));

            if (!loadingPage.isDisplayed()) {
                break;
            }
        }
    }

    public void verifyURLNotProduction() {
        Assert.assertFalse(isTextPresent("//*[contains(text, 'pointed to Production')]"));
    }

    public boolean isTextPresent(String txtValue) {
        boolean b = false;
        try {
            b = driver.getPageSource().contains(txtValue);
            return b;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            return b;
        }
    }
}
