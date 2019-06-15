package com.class01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverInfo;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.CommonMethods;

public class Task extends CommonMethods{
	/*TC 1: Swag Lab Title and Login Verification

	@BeforeMethod should contain navigation to the URL and title verification
	
	@Test should contain steps to login and “Product” word verification
	
	@AfterMethod should logOut from the application and close the browser
	 * 
	 */

	@BeforeClass
	public void setUP(){
		setUpDriver("chrome", "https://www.saucedemo.com/");
		String title = driver.getTitle();
		System.out.println(title);
		
		boolean swagLabs = driver.findElement(By.cssSelector("div.login_logo")).isDisplayed();
		if(swagLabs==true) {
			System.out.println("this is the right title");
		}else {
			System.out.println("this is not the right title");
		}
	}
	
	@Test
	public void login() throws InterruptedException {
		sendText(driver.findElement(By.cssSelector("input#user-name")),"standard_user");
		Thread.sleep(2000);
		sendText(driver.findElement(By.cssSelector("input#password")), "secret_sauce");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input.btn_action")).click();
		String text = driver.findElement(By.xpath("//div[text()='Products']")).getText();
		if(text.equals("Products")) {
			System.out.println("Product label is verified");
		}else {
			System.out.println("Product label is not verified");
		}
	}
	
	@AfterClass
	public void logout() {
		driver.findElement(By.xpath("//button[text()='Open Menu']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Logout']")));
		driver.findElement(By.linkText("Logout")).click();
		driver.close();
	}
}
