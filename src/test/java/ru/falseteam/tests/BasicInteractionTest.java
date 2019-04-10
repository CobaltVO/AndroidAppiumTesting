package ru.falseteam.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.falseteam.env.Commands;
import ru.falseteam.env.Constants;
import ru.falseteam.ui.App;
import ru.falseteam.ui.MainScreen;
import ru.falseteam.ui.Search;

import java.util.List;

public class BasicInteractionTest {

    @Test()
    public static void sendKeysTest() {
        WebElement element;
        List<WebElement> list;

        // user launches the app
        Commands.launchApp();

        // user goes to 'App'
        list = Commands.findElementsById("android", "text1");
        Assert.assertNotNull(list);
        list.get(MainScreen.List.App.ordinal()).click();

        // user goes to 'Search'
        list = Commands.findElementsById("android", "text1");
        Assert.assertNotNull(list);
        list.get(App.List.Search.ordinal()).click();

        // user goes to 'Invoke search'
        list = Commands.findElementsById("android", "text1");
        Assert.assertNotNull(list);
        list.get(Search.List.InvokeSearch.ordinal()).click();

        // user fills the field
        element = Commands.findElementById(Constants.PACKAGE_NAME, "txt_query_prefill");
        Assert.assertNotNull(element);
        element.sendKeys("Hello world!");

        // user clicks on search button
        element = Commands.findElementById(Constants.PACKAGE_NAME, "btn_start_search");
        Assert.assertNotNull(element);
        element.click();

        // user checks the field
        element = Commands.findElementById("android", "search_src_text");
        Assert.assertNotNull(element);
        Assert.assertEquals(element.getText(), "Hello world!");
    }

    @Test
    public static void openAlertTest() {
        WebElement element;
        List<WebElement> list;

        // user launches the app
        Commands.launchApp();

        // user goes to 'App'
        list = Commands.findElementsById("android", "text1");
        Assert.assertNotNull(list);
        list.get(MainScreen.List.App.ordinal()).click();

        // user goes to 'Alert Dialogs'
        list = Commands.findElementsById("android", "text1");
        Assert.assertNotNull(list);
        list.get(App.List.AlertDialogs.ordinal()).click();

        // user opens 'ok cancel dialog with a message'
        element = Commands.findElementById(Constants.PACKAGE_NAME, "two_buttons");
        Assert.assertNotNull(element);
        element.click();

        // user checks the dialog is displayed and the text is correct
        element = Commands.findElementById("android", "alertTitle");
        Assert.assertNotNull(element);
        Assert.assertEquals(element.getText(), "Lorem ipsum dolor sit aie consectetur adipiscing\nPlloaso mako nuto siwuf cakso dodtos anr koop.");

        // Close the dialog
        element = Commands.findElementById("android", "button1");
        Assert.assertNotNull(element);
        element.click();
    }
}
