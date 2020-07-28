package com.initBankingUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ReadConfigProperties {

	Properties prop;
	
	public ReadConfigProperties() { 
	File file=new File("C:\\Users\\Kalyani\\eclipse-workspace\\InitBakingV01\\Configuration\\config.properties");
	try {
		FileInputStream Fs=new FileInputStream(file);
		prop=new Properties();
		prop.load(Fs);
	}
	catch(Exception e) {
	System.out.println("Exception while loading config file :"+e.getMessage());	
	}
	}
	
	
	
	public String getAppUrl() {
		String surl=prop.getProperty("url");
		return surl;
		
	}
	public String getUsername() {
		String uid=prop.getProperty("username");
		return uid;
		
	}

	
	public String getPassword() {
		String password=prop.getProperty("password");
		return password;
		
	}

}
