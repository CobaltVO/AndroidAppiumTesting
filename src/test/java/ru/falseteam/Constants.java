package ru.falseteam;

import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;

final class Constants {
    final static String PROJECT_PATH = new File(System.getProperty("user.dir")).getAbsolutePath();
    final static String APK_DIR = new File(PROJECT_PATH, "src/test/resources/").getAbsolutePath();
    final static String APK_NAME = "ApiDemos-debug.apk";
    final static String APK_PATH = new File(APK_DIR, APK_NAME).getAbsolutePath();
    final static String PACKAGE_NAME = "io.appium.android.apis";
    final static String MAIN_ACTIVITY_NAME = PACKAGE_NAME + ".ApiDemos";
    final static String LOG_LEVEL = LogLevels.DEBUG;

    final static int APPIUM_PORT = AppiumServiceBuilder.DEFAULT_APPIUM_PORT;
}
