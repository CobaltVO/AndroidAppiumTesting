package ru.falseteam;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.falseteam.ui.App;
import ru.falseteam.ui.MainScreen;
import ru.falseteam.ui.Search;

import java.util.List;

public class AndroidBasicInteractionTest {
    private AppiumDriver<WebElement> driver = Env.getDriver();

    @Test()
    public void sendKeysTest() {
        // user launches the app
        Commands.launchApp();

        // user goes to 'App'
        List<WebElement> list = Commands.findElementsById("android", "text1");
        Assert.assertNotNull(list, "No such element");
        list.get(MainScreen.List.App.ordinal()).click();

        // user goes to 'Search'
        list = Commands.findElementsById("android", "text1");
        Assert.assertNotNull(list, "No such element");
        list.get(App.List.Search.ordinal()).click();

        // user goes to 'Invoke search'
        list = Commands.findElementsById("android", "text1");
        Assert.assertNotNull(list, "No such element");
        list.get(Search.List.InvokeSearch.ordinal()).click();

        WebElement searchBoxEl = driver.findElementById("txt_query_prefill");
        searchBoxEl.sendKeys("Hello world!");
        WebElement onSearchRequestedBtn = driver.findElementById("btn_start_search");
        onSearchRequestedBtn.click();
        WebElement searchText = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/search_src_text")));
        String searchTextValue = searchText.getText();
        Assert.assertEquals(searchTextValue, "Hello world!");
    }

    @Test
    public void openAlertTest() {
        // user launches the app
        Commands.launchApp();

        // user goes to 'App'
        List<WebElement> list = Commands.findElementsById("android", "text1");
        Assert.assertNotNull(list, "No such element");
        list.get(MainScreen.List.App.ordinal()).click();

        // user goes to 'Alert Dialogs'
        list = Commands.findElementsById("android", "text1");
        Assert.assertNotNull(list, "No such element");
        list.get(App.List.AlertDialogs.ordinal()).click();

        // user opens 'ok cancel dialog with a message'
        Commands.clickOnElementById(Constants.PACKAGE_NAME, "two_buttons", 5);

        // user checks the dialog is there
        // and the text is correct
        String alertText = Commands.getTextFromElementById("android", "alertTitle", 5);
        Assert.assertEquals(alertText, "Lorem ipsum dolor sit aie consectetur adipiscing\nPlloaso mako nuto siwuf cakso dodtos anr koop.");

        // Close the dialog
        Commands.clickOnElementById("android", "button1", 5);
    }
}
