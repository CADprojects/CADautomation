package testng;

import base.PageBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by Andrei.Ostrovski on 12.01.2017.
 */
public class TestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {
        getLogger().info(String.format("%s started", getMethodName(iTestResult)));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        getLogger().info(String.format("%s finished successfully", getMethodName(iTestResult)));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String methodName = getMethodName(iTestResult);
        if(!iTestResult.isSuccess()){
            Logger logger = getLogger();
            logger.error(String.format("%s failed", methodName));
            logger.error(iTestResult.getThrowable().toString());
            File screenshotFile = ((TakesScreenshot) PageBase.getDriver()).getScreenshotAs(OutputType.FILE);
            try {
                String reportDirectory = new File(System.getProperty("user.dir")) + "\\target\\surefire-reports";
                File resultFile = new File(reportDirectory+"\\failure_screenshots\\" + methodName +".png");
                FileUtils.copyFile(screenshotFile, resultFile);
                Reporter.log("<a href='"+ resultFile + "'> <img src='"+ resultFile + "' height='200' width='200'/> </a>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        getLogger().info(String.format("%s skipped", getMethodName(iTestResult)));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        getLogger().info(String.format("Test suite %s started", getMethodName(iTestContext)));
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        getLogger().info(String.format("Test suite %s finished", getMethodName(iTestContext)));
    }

    private Logger getLogger() {
        return LoggerFactory.getLogger(TestListener.class);
    }

    private String getMethodName(ITestResult iTestResult) {
        return iTestResult.getName();
    }

    private String getMethodName(ITestContext iTestContext) {
        return iTestContext.getName();
    }
}
