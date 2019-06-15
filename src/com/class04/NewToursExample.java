package com.class04;

import org.openqa.selenium.By;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.CommonMethods;

public class NewToursExample extends CommonMethods{

	 @BeforeGroups({"Smoke","Regression"})
	//@BeforeGroups("Regression")
	@BeforeMethod(alwaysRun =true, groups ="Regression")
	public void setUP() {
		setUpDriver("chrome", "http://newtours.demoaut.com/");
		driver.findElement(By.xpath("//a[text()='REGISTER']")).click();
	}
		
    public void contactInformation() {
		sendText(driver.findElement(By.cssSelector("input[name='phone']")), "phoneTest");
		sendText(driver.findElement(By.cssSelector("input[name='userName']")), "userTest");
		driver.findElement(By.xpath("//input[@name='register']")).click();
		//driver.findElement(By.xpath("//a[text()='REGISTER']")).click();
	}

	
	@Test(groups ="Regression",dependsOnMethods ="contactInformation")
	public void mailingInformation() throws InterruptedException {
		sendText(driver.findElement(By.cssSelector("input[name='address']")), "addressTest");	
		sendText(driver.findElement(By.cssSelector("input[name='address1']")), "addressTest");	
		sendText(driver.findElement(By.cssSelector("input[name='city']")), "cityTest");
		sendText(driver.findElement(By.cssSelector("input[name='state']")), "stateTest");
		sendText(driver.findElement(By.cssSelector("input[name='postalCode']")), "postalTest");
		driver.findElement(By.xpath("//input[@name='register']")).click();
	}

	@AfterGroups({"Smoke","Regression"})
	//@AfterGroups( "Regression")
	@AfterMethod(alwaysRun = true, groups ="Regression")
	public void tearDown() {
		driver.close();
	}
}
