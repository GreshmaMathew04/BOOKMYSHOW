package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class listenerClass implements ITestListener {
    WebDriver driver;

    public void onStart(ITestContext context) {
        System.out.println("onStart method started");
    }

    public void onFinish(ITestContext context) {
        System.out.println("onFinish method started");
    }

    public void onTestStart(ITestResult result) {
        System.out.println("New Test Started" +result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("onTestSuccess Method" +result.getName());
        TakesScreenshot scrShot =((TakesScreenshot)driver);

        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

        File DestFile=new File("C:\\Users\\gresh\\IdeaProjects\\Testngsample\\screenshots");
        try {
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onTestFailure(ITestResult result) {
        System.out.println("onTestFailure Method" +result.getName());
        TakesScreenshot scrShot =((TakesScreenshot)driver);

        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

        File DestFile=new File("C:\\Users\\gresh\\IdeaProjects\\Testngsample\\screenshotFailed");
        try {
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("onTestSkipped Method" +result.getName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("onTestFailedButWithinSuccessPercentage" +result.getName());
    }

}
