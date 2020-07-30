package com.initBankingListeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentXReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class TestListener implements ITestListener{
	public ExtentHtmlReporter htmlReporter ;
	public ExtentReports extent ;
	public ExtentTest extlogger;
	public ExtentTest childLogger;
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName="Test-Report-"+timestamp+".html";
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+repName);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/Configuration/Extent_config.xml");
		extent=new ExtentReports();
		 extent.attachReporter(htmlReporter);
		 extent.setSystemInfo("Host Name", "Local Host");
		 extent.setSystemInfo("Environment","QA");
		 extent.setSystemInfo("user", "Kalyani");
		 
		 htmlReporter.config().setDocumentTitle("Bankig Test Project");
		 htmlReporter.config().setReportName("Functional test report");
		 htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		 htmlReporter.config().setTheme(Theme.DARK);
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		extlogger=extent.createTest(result.getName());
		extlogger.log(Status.PASS, MarkupHelper.createLabel(result.getName(),ExtentColor.GREEN));
		
		
	}
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extlogger=extent.createTest(result.getName());
		extlogger.log(Status.FAIL, MarkupHelper.createLabel(result.getName(),ExtentColor.RED));
		String ScreenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+result.getName()+".png";
		
		File f = new File(ScreenshotPath); 
		if(f.exists())
		{
		try {
			extlogger.fail("Screenshot is below:" + extlogger.addScreenCaptureFromPath(ScreenshotPath));
			} 
		catch (IOException e) 
				{
				e.printStackTrace();
				}
		}
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		extlogger=extent.createTest(result.getName()); // create new entry in th report
		extlogger.log(Status.SKIP,MarkupHelper.createLabel(result.getName(),ExtentColor.ORANGE));
	}
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}
	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	public void createnode(String nodeName) {
		
		//logger.log(Status.PASS, MarkupHelper.createLabel(result.getName(),ExtentColor.GREEN));
		//StartBrowser.childTest=StartBrowser.parentTest.createNode("Login To Application");
		childLogger=extlogger.createNode(nodeName);
		
	}

}
