package com.crm.GenericLibrary;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementationClass implements ITestListener {
	
	ExtentReports report;
	ExtentTest test;
	
	public void onTestStart(ITestResult result)
	{
		String MethodName = result.getMethod().getMethodName();
		test = report.createTest(MethodName);
	}

	public void onTestSuccess(ITestResult result)
	{
		String MethodName = result.getMethod().getMethodName();
		test.log(Status.PASS, MethodName+"----->passed");
	}

	public void onTestFailure(ITestResult result) {
		
		String path = null;
		String MethodName = result.getMethod().getMethodName()+"-";
				
		// step 1: configure screenshot name
		String screeshotName = MethodName+new JavaUtility().getSystemDateFormat();
		System.out.println(screeshotName);
		
		// step 2: using screenshot method from webdriver utility
		
		try 
		{
			path = new WebDriverUtility().getScreenShot(BaseClass.sDriver, screeshotName);
			
			/*EventFiringWebDriver eDriver= new EventFiringWebDriver(BaseClass.sDriver);
			File src = eDriver.getScreenshotAs(OutputType.FILE);
			String pa = System.getProperty("user.dir")+"/Screeshots/"+screeshotName+".png";
			String path="./Screeshots/"+screeshotName+".png";
			File dst=new File(path);
			Files.copy(src, dst);*/
		} 
		catch (Throwable e) 
		{
		
			e.printStackTrace();
		}
		
		test.log(Status.FAIL, MethodName+"----->failed");
		// it will capture the exception and log it in the report
		test.log(Status.FAIL, result.getThrowable());
		test.addScreenCaptureFromPath(path);
		
	}

	public void onTestSkipped(ITestResult result) 
	{
		String MethodName = result.getMethod().getMethodName();
		test.log(Status.SKIP, MethodName+"--->skipped");
		//it will capture the exception and log it in the report
		test.log(Status.SKIP, result.getThrowable());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		
	}

	public void onTestFailedWithTimeout(ITestResult result)
	{
		
	}

	public void onStart(ITestContext context) 
	{
		//Execution will start here
		// configure the report
		ExtentSparkReporter htmlReport = new ExtentSparkReporter("./ExtentReports/Report"+new JavaUtility().getSystemDateFormat()+".html");
		htmlReport.config().setDocumentTitle("SDET-30 Execution report");
		htmlReport.config().setTheme(Theme.DARK);
		htmlReport.config().setReportName("Selenium Execution report");
		
		report = new ExtentReports();
		report.attachReporter(htmlReport);
		report.setSystemInfo("Base-Browser", "Chrome");
		report.setSystemInfo("OS", "Windows");
		report.setSystemInfo("base-url", "http://localhost:8888/");
		report.setSystemInfo("Reporter Name", "Adarsha");
		
	}

	public void onFinish(ITestContext context) 
	{
		//consolidate all the parameters and generate the report
		report.flush();
	}

}
