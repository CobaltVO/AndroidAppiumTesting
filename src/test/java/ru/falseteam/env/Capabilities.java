package ru.falseteam.env;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Capabilities {

    private static DesiredCapabilities capabilities;

    public static final String DEVICE_NAME = MobileCapabilityType.DEVICE_NAME; // required
    public static final String PLATFORM_NAME = MobileCapabilityType.PLATFORM_NAME; // should be used
    public static final String AUTOMATION_NAME = MobileCapabilityType.AUTOMATION_NAME; // should be used
    public static final String PLATFORM_VERSION = MobileCapabilityType.PLATFORM_VERSION; // should be used
    public static final String APP = MobileCapabilityType.APP; // required, or APP_PACKAGE or BROWSER_NAME

    public static final String NEW_COMMAND_TIMEOUT = MobileCapabilityType.NEW_COMMAND_TIMEOUT;
    public static final String APP_ACTIVITY = "appActivity";
    public static final String APP_PACKAGE = "appPackage";
    public static final String NO_RESET = MobileCapabilityType.NO_RESET;
    public static final String FULL_RESET = MobileCapabilityType.FULL_RESET;

    private static void setBaseCapabilities() {
        capabilities = new DesiredCapabilities();
        capabilities.setCapability(DEVICE_NAME, "D6603"); // required
        capabilities.setCapability(PLATFORM_NAME,"Android");
        capabilities.setCapability(PLATFORM_VERSION, "6.0");
        capabilities.setCapability(AUTOMATION_NAME,"UiAutomator2");
        capabilities.setCapability(NO_RESET,"true");
        capabilities.setCapability(FULL_RESET,"false");
        capabilities.setCapability(NEW_COMMAND_TIMEOUT, 2000);
    }

    public static DesiredCapabilities getCapabilities() {
        setBaseCapabilities();
        capabilities.setCapability(APP, Constants.APK_PATH);
        return capabilities;
    }

    public static DesiredCapabilities getCapabilities(String packageName, String appActivity) {
        setBaseCapabilities();
        capabilities.setCapability(APP_PACKAGE, packageName);
        capabilities.setCapability(APP_ACTIVITY, appActivity);
        return capabilities;
    }
}
