package ru.falseteam;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

class Env {
    private static AppiumDriver<WebElement> driver = null;
    private static DesiredCapabilities capabilities = null;

    void setCapabilities(DesiredCapabilities capabilities) {
        Env.capabilities = capabilities;
    }

    void setDriver(AndroidDriver<WebElement> driver) {
        Env.driver = driver;
    }

    static DesiredCapabilities getCapabilities() {
        if (capabilities == null) throw new NullPointerException("Capabilities were not initialized; you should call setUp method before executing tests");
        return capabilities;
    }

    static AppiumDriver<WebElement> getDriver() {
        if (driver == null) throw new NullPointerException("Driver wasn't initialized; you should call setUp method before executing tests");
        return driver;
    }
}
