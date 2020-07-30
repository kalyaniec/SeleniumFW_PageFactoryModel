package com.initBankingTestCases;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.initBankingListeners.TestListener;
import com.initBankingPageObjects.LoginPage;
import com.initBankingPageResources.BaseClass;
import com.initBankingUtilities.Excel_Utils;

import junit.framework.Assert;

public class TestCase02 extends BaseClass {
	// TestMethod

	@Test(dataProvider="LoginData")
	public void loginDDT(String us,String pwd) throws InterruptedException
	{
		System.out.println("Enetered");
		driver.get(url);
		LoginPage lp=new LoginPage(driver);
		lp.setUserId1(us);
		logger.info("user name provided");
		lp.setPassword(pwd);
		logger.info("password provided");
		lp.clickButton();
		
		Thread.sleep(3000);
		
		if(isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login failed");
		}
		else
		{
			Assert.assertTrue(true);
			logger.info("Login passed");
			lp.logout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();//close logout alert
			driver.switchTo().defaultContent();
			
		}
		
		
	}
	
	
	public boolean isAlertPresent() //user defined method created to check alert is presetn or not
	{
		try
		{
		driver.switchTo().alert();
		return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
		
	}
	// DataProvider Method
	@DataProvider(name = "LoginData")
	public Object[][] getData() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/initBankingTestData/LoginData.xlsx";
		
		
		int rownum=Excel_Utils.getRowCount(path, "Sheet1");
		int colcount=Excel_Utils.getCellCount(path,"Sheet1",1);
		
		String logindata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=Excel_Utils.getCellData(path,"Sheet1", i,j);//1 0
			}
				
		}
	return logindata;
	}
	
	
	

}
