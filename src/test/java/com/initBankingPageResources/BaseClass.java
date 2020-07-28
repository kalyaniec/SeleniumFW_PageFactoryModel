package com.initBankingPageResources;

import org.testng.annotations.Test;

import com.initBankingUtilities.ReadConfigProperties;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;

public class BaseClass {
	ReadConfigProperties readconfig=new ReadConfigProperties();
	public String url = readconfig.getAppUrl();
	public String username = readconfig.getUsername();
	public String password = readconfig.getPassword();
	public File fs;
	public static WebDriver driver;

	public static Logger logger;

	@Parameters("browser")
	@BeforeClass 
	public void setupMethod(String browser) {
		switch(browser){

		case "Chrome":
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		break;
		
		case "Firefox" :
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;
			
		case "IE":
		WebDriverManager.iedriver().setup();
		driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
		break;
		
		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			
		}
		PropertyConfigurator.configure("log4j.properties");
		logger = Logger.getLogger("devpinoyLogger");
		
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
public void takeScreenshot(String tname,WebDriver driver) throws IOException {
	
	fs=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	File target=new File(System.getProperty("user.dir")+"//ScreenShots/"+tname+".png");
FileUtils.copyFile(fs, target);
System.out.println("ScreenShot Taken");
}
}
