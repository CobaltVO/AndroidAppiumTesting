package ru.falseteam.env;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Commands {
    private final static String DELIMITER_ID = ":" + "id/";

    public static void launchApp() {
        String response = executeAdbShellCommand("am start -n " + Constants.PACKAGE_NAME + "/" + Constants.MAIN_ACTIVITY_NAME);
        Assert.assertTrue(!response.contains("Error"));
    }

    public static void wait(int sec) {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(sec));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
            element = Driver.getDriver().findElement(By.id(packageName + DELIMITER_ID + id));
        } catch (NoSuchElementException e) {
            element = null;
        }
        return element;
    }

    public static List<WebElement> findElementsById(String packageName, String id) {
        List<WebElement> element;
        try {
            element = Driver.getDriver().findElements(By.id(packageName + DELIMITER_ID + id));
        } catch (NoSuchElementException e) {
            return null;
        }
        if (element.isEmpty()) element = null;
        return element;
    }

    public static void clickOnElementById(String packageName, String id, int timeoutOfElementToBeVisibleSec) {
        WebElement element;
        int t = 0;
        while (t < timeoutOfElementToBeVisibleSec) {
            element = findElementById(packageName, id);
            if (element != null) {
                element.click();
                return;
            }
            wait(1);
            t++;
        }
        Assert.fail("There is no such an element and it can't be clicked");
    }

    public static String getTextFromElementById(String packageName, String id, int timeoutOfElementToBeVisibleSec) {
        WebElement element;
        int t = 0;
        while (t < timeoutOfElementToBeVisibleSec) {
            element = findElementById(packageName, id);
            if (element != null) {
                return element.getText();
            }
            wait(1);
            t++;
        }
        Assert.fail("There is no such an element and text can't be received from it");
        return null;
    }

    public static WebElement waitElementAppears(String packageName, String id, int timeoutSeconds) {
        return new WebDriverWait(Driver.getDriver(), timeoutSeconds)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(packageName + DELIMITER_ID + id)));
    }
}
