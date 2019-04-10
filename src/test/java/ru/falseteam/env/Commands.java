package ru.falseteam.env;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Commands {
    private final static String DELIMITER_ID = ":" + "id/";
    private final static long STANDARD_TIMEOUT = 20L;

    public static void launchApp() {
        String response = executeAdbShellCommand("am start -n " + Constants.PACKAGE_NAME + "/" + Constants.MAIN_ACTIVITY_NAME);
        Assert.assertTrue(!response.contains("Error"));
    }

    /**
     optionalArgs is the optional argument: {
        args.put("args", Lists.newArrayList("arg1", "arg2"));
        args.put("includeStderr", "true");
        // time before exception will be thrown while command is executing
        // 20000 ms is the default value
        args.put("timeout", "20000");
     }
     */
    public static String executeAdbShellCommand(String command) {
        Map<String, Object> args = new HashMap<>();
        args.put("command", command);
        return (String) Driver.getDriver().executeScript("mobile:shell", args);
    }

    public static WebElement findElementById(String packageName, String id) {
        WebElement element;
        try {
            element = waitElementAppears(packageName, id, STANDARD_TIMEOUT);
        } catch (TimeoutException e) {
            element = null;
        }
        return element;
    }

    public static List<WebElement> findElementsById(String packageName, String id) {
        List<WebElement> elements;
        try {
            elements = waitElementsAppears(packageName, id, STANDARD_TIMEOUT);
        } catch (TimeoutException e) {
            elements = null;
        }
        return elements;
    }

    public static WebElement waitElementAppears(String packageName, String id, long timeoutSeconds) {
        return new WebDriverWait(Driver.getDriver(), timeoutSeconds)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(packageName + DELIMITER_ID + id)));
    }

    public static List<WebElement> waitElementsAppears(String packageName, String id, long timeoutSeconds) {
        return new WebDriverWait(Driver.getDriver(), timeoutSeconds)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(packageName + DELIMITER_ID + id)));
    }
}
