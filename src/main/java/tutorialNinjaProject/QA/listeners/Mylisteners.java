package tutorialNinjaProject.QA.listeners;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import tutorialNinjaProject.QA.Utlis.MyExtentReporter;

public class Mylisteners implements ITestListener{

	ExtentReports extentreport;
	ExtentTest extentTest;
	@Override
	public void onStart(ITestContext context) 
	{
		try {
			extentreport=MyExtentReporter.ExtentReporter();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void onTestStart(ITestResult result) {
		String testName =result.getName();
		extentTest = extentreport.createTest(testName);
		extentTest.log(Status.INFO, testName+"startedExecuting");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName =result.getTestName();
		extentTest.log(Status.PASS, testName+"got successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName =result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName+"got failed");
		System.out.println(testName+"Test got failed");
		System.out.println("Screenshot taken");
	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testname =result.getName();
		extentTest.log(Status.SKIP, testname+"got skipped");
		extentTest.log(Status.SKIP, result.getThrowable());
		System.out.println(result.getThrowable());
	}


	@Override
	public void onFinish(ITestContext context) {
		extentreport.flush();
	}

}
