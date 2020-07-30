package com.initBankingListeners;

import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

public class Reporter implements IReporter{

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		// TODO Auto-generated method stub
		for(ISuite suit :suites) {
			String suiteName=suit.getName();
			Map<String,ISuiteResult>suitResult=suit.getResults();
			for(ISuiteResult sr: suitResult.values())
			{
				ITestContext tc=sr.getTestContext();
				System.out.println("Passed tests for suite '" + suiteName +
			               "' is:" + tc.getPassedTests().getAllResults().size());
			            System.out.println("Failed tests for suite '" + suiteName +
			               "' is:" + tc.getFailedTests().getAllResults().size());
			            System.out.println("Skipped tests for suite '" + suiteName +
			               "' is:" + tc.getSkippedTests().getAllResults().size());
			}
			
		}
	}
	

}
