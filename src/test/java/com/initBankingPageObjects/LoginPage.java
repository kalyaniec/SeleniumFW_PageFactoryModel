package com.initBankingPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
public WebDriver ldriver;
	
	@FindBy(name="uid")
	WebElement userID;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(name="btnLogin")
	WebElement submitbutton;
	
	@FindBy(xpath="//a[text()='Log out']")
	WebElement logout;
	
	
	
	public LoginPage(WebDriver rdriver) {
		// TODO Auto-generated constructor stub
		
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
		
	}




	public void setUserId1(String username)
	{
		userID.sendKeys(username);
	}
	

	public void setPassword(String pwd)
	{
		password.sendKeys(pwd);
	}
	
	public void clickButton()
	{
		submitbutton.click();
	}
	
	public String getTitle()
	{
		return(ldriver.getTitle());
	}
	
	public String alertText()
	{
		return ldriver.switchTo().alert().getText();
	}
	public void logout()
	{
		 logout.click();
	}

	public boolean isAlertPresent()
	{
		try {
		ldriver.switchTo().alert();
		return true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

}
