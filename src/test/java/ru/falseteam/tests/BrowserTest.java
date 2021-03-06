package ru.falseteam.tests;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.falseteam.env.Commands;
import ru.falseteam.env.Driver;
import ru.falseteam.ui.ChromeBeta;

import java.util.List;

public class BrowserTest extends Driver {
    private static final int TIMEOUT = 20;

    @Test
    public static void launchBrowser() {
        AppiumDriver<WebElement> driver = Driver.getDriver();
        Commands.waitElementAppears(ChromeBeta.PACKAGE_NAME, ChromeBeta.COORDINATOR, TIMEOUT);
        Commands.waitElementAppears(ChromeBeta.PACKAGE_NAME, ChromeBeta.COMPOSITOR_VIEW_HOLDER, TIMEOUT);
        driver.get("https://appium.io/");
        Assert.assertEquals(driver.getCurrentUrl(), "https://appium.io/", "URL Mismatch");
        Assert.assertEquals(driver.getTitle(), "Appium: Mobile App Automation Made Awesome.", "Title Mismatch");
    }

    private static void acceptTheTerms() {
        Assert.assertNotNull(Commands.waitElementAppears(ChromeBeta.PACKAGE_NAME, ChromeBeta.FRE_PAGER, TIMEOUT));
        Assert.assertNotNull(Commands.waitElementAppears(ChromeBeta.PACKAGE_NAME, ChromeBeta.IMAGE, TIMEOUT));
        Assert.assertNotNull(Commands.waitElementAppears(ChromeBeta.PACKAGE_NAME, ChromeBeta.TITLE, TIMEOUT));
        Assert.assertNotNull(Commands.waitElementAppears(ChromeBeta.PACKAGE_NAME, ChromeBeta.TERMS_ACCEPT, TIMEOUT));

        WebElement element = Commands.findElementById(ChromeBeta.PACKAGE_NAME, ChromeBeta.TERMS_ACCEPT);
        Assert.assertNotNull(element, "No such element");
        element.click();
    }

    private static void selectSearchingMachine() {
        Assert.assertNotNull(Commands.waitElementAppears(ChromeBeta.PACKAGE_NAME, ChromeBeta.FRE_PAGER, TIMEOUT));
        Assert.assertNotNull(Commands.waitElementAppears(ChromeBeta.PACKAGE_NAME, ChromeBeta.DEFAULT_SEARCH_ENGINE_DIALOG_OPTIONS, TIMEOUT));
        Assert.assertNotNull(Commands.waitElementAppears(ChromeBeta.PACKAGE_NAME, ChromeBeta.BUTTON_PRIMARY, TIMEOUT));

        WebElement buttonList = Commands.findElementById(ChromeBeta.PACKAGE_NAME, ChromeBeta.DEFAULT_SEARCH_ENGINE_DIALOG_OPTIONS);
        List<WebElement> radioButtons = buttonList.findElements(By.className("android.widget.RadioButton"));
        for (WebElement button : radioButtons) {
            String searchingEngineName = button.getText();
            if (searchingEngineName.toLowerCase().replaceAll("\\W", "").equals("google")) {
                button.click();
            }
        }

        WebElement element = Commands.findElementById(ChromeBeta.PACKAGE_NAME, ChromeBeta.BUTTON_PRIMARY);
        Assert.assertNotNull(element, "No such element");
        element.click();
    }

    private static void skipSignInScreen() {
        Assert.assertNotNull(Commands.waitElementAppears(ChromeBeta.PACKAGE_NAME, ChromeBeta.FRE_PAGER, TIMEOUT));
        Assert.assertNotNull(Commands.waitElementAppears(ChromeBeta.PACKAGE_NAME, ChromeBeta.ACCOUNT_SIGN_IN_CHOOSE_VIEW, TIMEOUT));
        Assert.assertNotNull(Commands.waitElementAppears(ChromeBeta.PACKAGE_NAME, ChromeBeta.SIGN_IN_CHOICE_DESCRIPTION, TIMEOUT));
        Assert.assertNotNull(Commands.waitElementAppears(ChromeBeta.PACKAGE_NAME, ChromeBeta.NEGATIVE_BUTTON, TIMEOUT));
        Assert.assertNotNull(Commands.waitElementAppears(ChromeBeta.PACKAGE_NAME, ChromeBeta.POSITIVE_BUTTON, TIMEOUT));

        WebElement element = Commands.findElementById(ChromeBeta.PACKAGE_NAME, ChromeBeta.NEGATIVE_BUTTON);
        Assert.assertNotNull(element, "No such element");
        element.click();
    }
}
