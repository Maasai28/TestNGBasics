package com.class01;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.CommonMethods;

public class OrangeHrmExample extends CommonMethods{

	@BeforeMethod
	public void setUP(){
		setUpDriver("chrome", "https://opensource-demo.orangehrmlive.com/");	
	}
	
	@Test
	public void login() throws InterruptedException {
		
		FileInputStream fis = new FileInputStream(filePath);
		prop = new Properties();
		prop.load(fis);
		sendText(driver.findElement(By.cssSelector("input#txtUsername")), "Admin");
		Thread.sleep(2000);
		sendText(driver.findElement(By.cssSelector("input#txtPassword")), "admin123");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input#btnLogin")).click();
	}
	
	@AfterMethod
	public void close() {
		driver.close();
	}
}