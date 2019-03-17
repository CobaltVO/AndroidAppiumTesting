package ru.falseteam;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.URL;

public abstract class BaseTest {
    private static AppiumDriverLocalService service;

    @BeforeSuite
    public void globalSetup() {
        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
        appiumServiceBuilder
                .usingPort(Constants.APPIUM_PORT)
                .withArgument(() -> "--log-level", Constants.LOG_LEVEL)
                .withArgument(() -> "--relaxed-security");
        service = AppiumDriverLocalService.buildService(appiumServiceBuilder);
        service.start();
    }

    @AfterSuite
    public void globalTearDown() {
        service.stop();
    }

    public URL getServiceUrl() {
        return service.getUrl();
    }
}
