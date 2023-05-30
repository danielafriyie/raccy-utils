import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.lang.Thread;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import raccy.seleniumutils.SeleniumUtils;

public class Main {

    static void go(List<? extends Integer> lst) {
        System.out.println(lst);
    }

    public static void main(String[] args) throws Exception {
        var s = "hello";

        for (int i = 0; i < s.length(); i++) {
            System.out.println(String.valueOf(s.charAt(i)));
        }
    }
}