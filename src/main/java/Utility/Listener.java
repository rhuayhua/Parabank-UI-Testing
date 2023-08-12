package Utility;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.util.HashMap;

public class Listener implements ITestListener {

    private static ThreadLocal<HashMap<String,String>> myMap = ThreadLocal.withInitial(() -> new HashMap<>());

    public static String getMyMap(String key) {
        return myMap.get().get(key);
    }

    public void setMyMap(String key, String value) {
        myMap.get().put(key,value);
    }

    @Override
    public void onTestStart(ITestResult result) {
        printDebugMessage(result.getMethod().getTestClass() + "-" + result.getMethod().getDescription() + " - onTestStart, SessionID: " + ((RemoteWebDriver) BasePage.getDriver()).getSessionId());
        setMyMap("TCName",result.getMethod().getDescription());
        if (getMyMap("TCName")!=result.getMethod().getDescription()) {
            printDebugMessage("ERROR getting data - multithreading execution");
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        printDebugMessage(result.getMethod().getTestClass() + "-" + result.getMethod().getDescription() + " - onTestSuccess, SessionID: " + ((RemoteWebDriver) BasePage.getDriver()).getSessionId());
        BasePage.captureScreenshot();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        printDebugMessage(result.getMethod().getTestClass() + "-" + result.getMethod().getDescription() + " - onTestFailure, SessionID: " + ((RemoteWebDriver) BasePage.getDriver()).getSessionId());
        BasePage.captureScreenshot();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        printDebugMessage("onTestSkipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        printDebugMessage("onTestFailedButWithinSuccessPercentage");
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        printDebugMessage("onTestFailedWithTimeout");
    }

    @Override
    public void onStart(ITestContext context) {
        printDebugMessage("onStart");
    }

    @Override
    public void onFinish(ITestContext context) {
        printDebugMessage("onFinish");
    }

    public void printDebugMessage (String msg) {
        System.out.println("Thread" + Thread.currentThread().getId() + " " + msg);
    }
}
