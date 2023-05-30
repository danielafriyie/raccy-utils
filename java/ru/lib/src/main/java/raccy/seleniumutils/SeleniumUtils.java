package raccy.seleniumutils;

import java.util.List;
import java.lang.Thread;
import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SeleniumUtils {

    private SeleniumUtils() {
    }

    public static void windowScrollTo(WebDriver driver, Number location) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript(String.format("window.scrollTo(0, %s);", location));
    }

    public static void scrollIntoView(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static WebElement driverWait(WebDriver driver,
                                        By by, int timeout,
                                        Function<By, ExpectedCondition<WebElement>> condition) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(condition.apply(by));
    }

    public static WebElement findElementByXpath(WebDriver driver,
                                                String xpath,
                                                int timeout,
                                                Function<By, ExpectedCondition<WebElement>> condition) {
        return driverWait(driver, By.xpath(xpath), timeout, condition);
    }

    public static WebElement findElementByXpath(WebDriver driver, String xpath) {
        return findElementByXpath(driver, xpath, 10, ExpectedConditions::elementToBeClickable);
    }

    public static WebElement findElementByCSS(WebDriver driver,
                                              String css,
                                              int timeout,
                                              Function<By, ExpectedCondition<WebElement>> condition) {
        return driverWait(driver, By.cssSelector(css), timeout, condition);
    }

    public static WebElement findElementByCSS(WebDriver driver, String css) {
        return findElementByCSS(driver, css, 10, ExpectedConditions::elementToBeClickable);
    }

    public static WebElement findElementByID(WebDriver driver,
                                             String id,
                                             int timeout,
                                             Function<By, ExpectedCondition<WebElement>> condition) {
        return driverWait(driver, By.id(id), timeout, condition);
    }

    public static WebElement findElementByID(WebDriver driver, String id) {
        return findElementByID(driver, id, 10, ExpectedConditions::elementToBeClickable);
    }

    public static WebElement findElementByLinkText(WebDriver driver,
                                                   String text,
                                                   int timeout,
                                                   Function<By, ExpectedCondition<WebElement>> condition) {
        return driverWait(driver, By.linkText(text), timeout, condition);
    }

    public static WebElement findElementByLinkText(WebDriver driver, String text) {
        return findElementByLinkText(driver, text, 10, ExpectedConditions::elementToBeClickable);
    }

    public static void driverOrJsClick(WebDriver driver,
                                       String xpath,
                                       int timeout,
                                       Function<By, ExpectedCondition<WebElement>> condition) {
        try {
            WebElement elm = findElementByXpath(driver, xpath, timeout, condition);
            new Actions(driver).moveToElement(elm).click().perform();
        } catch (WebDriverException ignored) {
            WebElement elm = driver.findElement(By.xpath(xpath));
            try {
                new Actions(driver).moveToElement(elm).click().perform();
            } catch (WebDriverException ignored1) {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", elm);
            }
        }
    }

    public static void manualEntry(WebDriver driver,
                                   String xpath,
                                   String text,
                                   int timeout,
                                   Function<By, ExpectedCondition<WebElement>> condition,
                                   long sleepTime) throws InterruptedException {
        WebElement elm = findElementByXpath(driver, xpath, timeout, condition);
        new Actions(driver).moveToElement(elm).perform();
        elm.clear();

        for (int i = 0; i < text.length(); i++) {
            elm.sendKeys(String.valueOf(text.charAt(i)));
            Thread.sleep(sleepTime);
        }
    }

    public static void manualEntry(WebDriver driver, String xpath, String text) throws InterruptedException {
        manualEntry(driver, xpath, text, 10, ExpectedConditions::elementToBeClickable, 500);
    }

    public static void enter(WebDriver driver,
                             String xpath,
                             String text,
                             int timeout,
                             Function<By, ExpectedCondition<WebElement>> condition) {
        WebElement elm = findElementByXpath(driver, xpath, timeout, condition);
        new Actions(driver).moveToElement(elm).perform();
        elm.clear();
        elm.sendKeys(text);
    }

    public static void enter(WebDriver driver, String xpath, String text) {
        enter(driver, xpath, text, 10, ExpectedConditions::elementToBeClickable);
    }

    public static List<WebElement> driverWaits(WebDriver driver, By by, int timeout, Function<By, ExpectedCondition<List<WebElement>>> condition) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(condition.apply(by));
    }

    public static List<WebElement> findElementsByXpath(WebDriver driver,
                                                String xpath,
                                                int timeout,
                                                Function<By, ExpectedCondition<List<WebElement>>> condition) {
        return driverWaits(driver, By.xpath(xpath), timeout, condition);
    }

    public static List<WebElement> findElemenstByXpath(WebDriver driver, String xpath) {
        return findElementsByXpath(driver, xpath, 10, ExpectedConditions::presenceOfAllElementsLocatedBy);
    }
}
