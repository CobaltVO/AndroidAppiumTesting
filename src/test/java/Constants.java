import java.io.File;

public final class Constants {
    public final static String PROJECT_PATH = new File(System.getProperty("user.dir")).getAbsolutePath();
    public final static String APK_DIR = new File(PROJECT_PATH, "src/test/resources/").getAbsolutePath();
    public final static String APK_NAME = "ApiDemos-debug.apk";
    public final static String APK_PATH = new File(APK_DIR, APK_NAME).getAbsolutePath();
    public final static String PACKAGE_NAME = "io.appium.android.apis";
}
