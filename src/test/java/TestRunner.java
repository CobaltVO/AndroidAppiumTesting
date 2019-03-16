import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestRunner extends BaseTest {
    private Env env;

    @BeforeClass
    public void setUp() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "D6603"); // required
        capabilities.setCapability(MobileCapabilityType.APP, Constants.APK_PATH); // required, or APP_PACKAGE or BROWSER_NAME
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");

        env = new Env();
        env.setCapabilities(capabilities);
        env.setDriver(new AndroidDriver<>(getServiceUrl(), capabilities));
    }

    @AfterClass
    public void tearDown() {
        Env.getDriver().quit();
    }

    @Test
    public void test() {
        AndroidBasicInteractionTest a = new AndroidBasicInteractionTest();
        a.testOpensAlert();
        a.testSendKeys();
    }
}