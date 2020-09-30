package utilities;

import bookmyshowassignment.BOOKMYSHOW;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class listenerClass implements ITestListener {
    ExtentReports reports;
    ExtentSparkReporter extentSparkReporter;
    ExtentTest extentTest;
    BOOKMYSHOW bookmyshow;

    public void onStart(ITestContext context) {
        String path= System.getProperty("user.dir");
        reports=new ExtentReports();
        extentSparkReporter=new ExtentSparkReporter(path+"\\report\\report.html");
        try{
            reports.setSystemInfo("Machine Name", InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        reports.attachReporter(extentSparkReporter);
        System.out.println("onStart method started");
    }

    public void onFinish(ITestContext context) {
        reports.flush();
        System.out.println("onFinish method started");
    }



    public void onTestStart(ITestResult iTestResult) {
        System.out.println("onTestStart method started");
    }

    public void onTestSuccess(ITestResult result) {
        extentTest=reports.createTest(result.getName());
        extentTest.log(Status.PASS, result.getName());
        try{
            extentTest.addScreenCaptureFromPath(bookmyshow.takeScreenshot());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("onTestSuccess Method" +result.getName());

    }

    public void onTestFailure(ITestResult result) {
        extentTest=reports.createTest(result.getName());
        extentTest.log(Status.FAIL, result.getName());
        try{
            extentTest.addScreenCaptureFromPath(bookmyshow.takeScreenshot());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("onTestFailure Method" +result.getName());
    }

    public void onTestSkipped(ITestResult result) {
        extentTest=reports.createTest(result.getName());
        extentTest.log(Status.SKIP, result.getName());

        System.out.println("onTestSkipped Method" +result.getName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("method"+iTestResult.getName());
    }


}
