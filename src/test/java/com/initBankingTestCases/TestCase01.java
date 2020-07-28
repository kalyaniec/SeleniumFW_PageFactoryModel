package com.initBankingTestCases;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.initBankingPageObjects.LoginPage;
import com.initBankingPageResources.BaseClass;
import com.initBankingUtilities.TestListener;

import junit.framework.Assert;

public class TestCase01 extends BaseClass{
	
	@Test
	public void loginTest(Method method) throws IOException {
		driver.get(url);
		LoginPage Login=new LoginPage(driver);
		TestListener tl=new TestListener();
		Login.setUserId1(username);
		
		logger.debug("Valid User_Name");
		
		Login.setPassword(password);
		logger.debug("valid Password");
		
		Login.clickButton();
		if (Login.getTitle().contains("Guru99"))
				{
		
			logger.debug("Successfully logged in");
			takeScreenshot(method.getName(), driver);
		}
		else {
	
			logger.error("Invalid username and password throws no exception");
			takeScreenshot(method.getName(), driver);
		}
		
	}

}
