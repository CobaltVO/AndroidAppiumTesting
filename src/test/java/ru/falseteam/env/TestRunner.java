package ru.falseteam.env;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.falseteam.tests.BasicInteractionTest;
import ru.falseteam.tests.BrowserTest;
import ru.falseteam.ui.ChromeBeta;

public class TestRunner extends Driver {

    @BeforeClass
    public void setUp() {
        //
    }

    @AfterClass
    public void tearDown() {
        //
    }

    @Test
    public void alertTest() {
        Driver.setDriver(Capabilities.getCapabilities());
        BasicInteractionTest.openAlertTest();
    }

    @Test
    public void sendKeysTest() {
        Driver.setDriver(Capabilities.getCapabilities());
        BasicInteractionTest.sendKeysTest();
    }

    @Test
    public void browserTest() {
        Driver.setDriver(Capabilities.getCapabilities(
                ChromeBeta.PACKAGE_NAME, "com.google.android.apps.chrome.Main"
        ));
        BrowserTest.launchBrowser();
    }
}