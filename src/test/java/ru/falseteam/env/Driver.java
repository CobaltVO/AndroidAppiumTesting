package ru.falseteam.env;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.URL;

public class Driver {
    private static AppiumDriverLocalService service;
    private static AndroidDriver<WebElement> driver;

    @BeforeSuite
    public static void globalSetup() {
        startAppium();
        setDriver(Capabilities.getCapabilities());
    }

    @AfterSuite
    public static void globalTearDown() {
        service.stop();
        driver.quit();
    }

    private static void startAppium() {
        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
        appiumServiceBuilder
                .usingPort(Constants.APPIUM_PORT)
                .withArgument(() -> "--log-level", Constants.LOG_LEVEL)
                .withArgument(() -> "--relaxed-security");
        service = AppiumDriverLocalService.buildService(appiumServiceBuilder);
        service.start();
    }

    public static AndroidDriver<WebElement> setDriver(DesiredCapabilities dc) {
        return (driver = new AndroidDriver<>(service, dc));
    }

    public static AndroidDriver<WebElement> getDriver() {
        return driver;
    }

    public URL getServiceUrl() {
        return service.getUrl();
    }
}
