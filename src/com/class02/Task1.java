package com.class02;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.CommonMethods;

public class Task1 extends CommonMethods {
	@BeforeMethod
	public void setUp() {
		setUpDriver("chrome", "https://opensource-demo.orangehrmlive.com/");
	}

	@Test(priority = 0)
	public void getTitle() {
		String title = driver.getTitle();
		System.out.println(title);
		if (title.equals("OrangeHRM")) {
			System.out.println("This is the correct title");
		} else {
			System.out.println("This is not a correct title");
		}

	}

	@Test(priority = 1)
	public void login() {
		sendText(driver.findElement(By.cssSelector("input#txtUsername")), "Admin");
		sendText(driver.findElement(By.cssSelector("input#txtPassword")), "admin123");
		driver.findElement(By.cssSelector("input#btnLogin")).click();
	}

	@AfterMethod
	public void close() {
		driver.close();

	}

}
