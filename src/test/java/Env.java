import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Env {
    private static AndroidDriver<WebElement> driver;
    private static DesiredCapabilities capabilities;

    public void setCapabilities(DesiredCapabilities capabilities) {
        Env.capabilities = capabilities;
    }

    public void setDriver(AndroidDriver<WebElement> driver) {
        Env.driver = driver;
    }

    public static DesiredCapabilities getCapabilities() {
        return capabilities;
    }

    public static AndroidDriver<WebElement> getDriver() {
        return driver;
    }
}
