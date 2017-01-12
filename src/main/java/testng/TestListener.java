package testng;

import base.PageBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by Andrei.Ostrovski on 12.01.2017.
 */
public class TestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String methodName = iTestResult.getName();
        if(!iTestResult.isSuccess()){
            File screenshotFile = ((TakesScreenshot) PageBase.getDriver()).getScreenshotAs(OutputType.FILE);
            try {
                String reportDirectory = new File(System.getProperty("user.dir")) + "\\target\\surefire-reports";
                File resultFile = new File((String) reportDirectory+"\\failure_screenshots\\" + methodName +".png");
                FileUtils.copyFile(screenshotFile, resultFile);
                Reporter.log("<a href='"+ resultFile + "'> <img src='"+ resultFile + "' height='200' width='200'/> </a>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    }
}
