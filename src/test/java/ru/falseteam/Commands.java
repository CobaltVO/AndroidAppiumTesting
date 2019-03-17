package ru.falseteam;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

class Commands {
    private final static String DELIMITER_ID = ":" + "id/";

    static void launchApp() {
        String response = executeAdbShellCommand("am start -n " + Constants.PACKAGE_NAME + "/" + Constants.MAIN_ACTIVITY_NAME);
        Assert.assertTrue(!response.contains("Error"));
    }

    static void wait(int sec) {
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
    static String executeAdbShellCommand(String command) {
        Map<String, Object> args = new HashMap<>();
        args.put("command", command);
        return (String) Env.getDriver().executeScript("mobile:shell", args);
    }

    static WebElement findElementById(String packageName, String id) {
        WebElement element;
        try {
            element = Env.getDriver().findElement(By.id(packageName + DELIMITER_ID + id));
        } catch (NoSuchElementException e) {
            element = null;
        }
        return element;
    }

    static List<WebElement> findElementsById(String packageName, String id) {
        List<WebElement> element;
        try {
            element = Env.getDriver().findElements(By.id(packageName + DELIMITER_ID + id));
        } catch (NoSuchElementException e) {
            return null;
        }
        if (element.isEmpty()) element = null;
        return element;
    }

    static void clickOnElementById(String packageName, String id, int timeoutOfElementToBeVisibleSec) {
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

    static String getTextFromElementById(String packageName, String id, int timeoutOfElementToBeVisibleSec) {
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
}
