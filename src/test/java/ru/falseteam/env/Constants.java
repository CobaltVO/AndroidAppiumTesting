package ru.falseteam.env;

import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;

public final class Constants {
    public final static String PROJECT_PATH = new File(System.getProperty("user.dir")).getAbsolutePath();
    public final static String APK_DIR = new File(PROJECT_PATH, "src/test/resources/").getAbsolutePath();
    public final static String APK_NAME = "ApiDemos-debug.apk";
    public final static String APK_PATH = new File(APK_DIR, APK_NAME).getAbsolutePath();
    public final static String PACKAGE_NAME = "io.appium.android.apis";
    public final static String MAIN_ACTIVITY_NAME = PACKAGE_NAME + ".ApiDemos";
    public final static String LOG_LEVEL = LogLevels.DEBUG;
    public final static String FULL_RESET = "false";
    public final static String NO_RESET = "true";

    final static int APPIUM_PORT = AppiumServiceBuilder.DEFAULT_APPIUM_PORT;
}
