import java.lang.Thread;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import raccy.seleniumutils.SeleniumUtils;

public class TestSeleniumUtils {
    private static WebDriver driver;

    @BeforeClass
    public static void setUpClass() {
        driver = new ChromeDriver();
    }

    @AfterClass
    public static void tearDownClass() {
        driver.quit();
    }

    @Test
    public void testFindElement() throws Exception {
        Stream.out.info("Testing testFindElement ...");
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        SeleniumUtils.findElementByXpath(driver, "//input[@id='my-text-id']");
        SeleniumUtils.findElementByCSS(driver, "input#my-text-id");
        SeleniumUtils.findElementByID(driver, "my-text-id");
        SeleniumUtils.findElementByLinkText(driver, "Return to index");
        SeleniumUtils.manualEntry(driver, "//input[@id='my-text-id']", "Text Input");
        SeleniumUtils.enter(driver, "//input[@name='my-password']", "Passowrd");
        Thread.sleep(2000);
        SeleniumUtils.driverOrJsClick(driver, "//button[@type='submit']");
        SeleniumUtils.findElementByXpath(driver, "//h1[contains(text(), 'Form submitted')]");
        Thread.sleep(1000);
        Stream.out.success("testFindElement passed!");
    }
}
