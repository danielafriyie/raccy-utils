package raccy.seleniumutils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class SeleniumUtils {

    public static void windowScrollTo(WebDriver driver, Number location) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript(String.format("window.scrollTo(0, %s);", location));
    }

    public static void scrollIntoView(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
    }

//    private static WebElement driverWait(WebDriver driver, String locator, By by, int timeout, ExpectedCondition condition)
}
