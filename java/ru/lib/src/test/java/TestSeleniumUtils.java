import java.lang.Thread;
import java.util.logging.Level;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import raccy.seleniumutils.SeleniumUtils;

public class TestSeleniumUtils {
    private static WebDriver driver;

    @BeforeClass
    public static void setUpClass() {
        System.setProperty("webdriver.chrome.silentOutput", "true");
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation", "enable-logging"});

        driver = new ChromeDriver(options);
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        Thread.sleep(1000);
        driver.quit();
    }

    @Test
    public void testFindElement() {
        Stream.out.info("Testing testFindElement ...");
        SeleniumUtils.findElementByXpath(driver, "//input[@id='my-text-id']");
        SeleniumUtils.findElementByCSS(driver, "input#my-text-id");
        SeleniumUtils.findElementByID(driver, "my-text-id");
        SeleniumUtils.findElementByLinkText(driver, "Return to index");
        Stream.out.success("testFindElement passed!");
    }

    @Test
    public void testEnter() throws Exception {
        Stream.out.info("Testing testEnter ...");
        SeleniumUtils.manualEntry(driver, "//input[@id='my-text-id']", "Text Input");
        SeleniumUtils.enter(driver, "//input[@name='my-password']", "Passowrd");
        SeleniumUtils.driverOrJsClick(driver, "//button[@type='submit']");
        SeleniumUtils.findElementByXpath(driver, "//h1[contains(text(), 'Form submitted')]");
        Stream.out.success("testEnter passed!");
    }
}
