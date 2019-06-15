package com.class05;
/*
 * Identify Priority of Test Cases

		http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Process.aspx
		TC 1: Saucedemo Username1(Data Provider - Quantity:, Customer name:,Street: City:, State: ,Zip:,Card:,Card Nr:, Expire date )
		Step 1: Open browser and navigate to smartbearsoftware
		Step 2: Enter valid username, password and click login button
		Step 3: Verify user successfully logged in
		Step 4: Click on Order
		Step 5: Make an order for two user,enter all the information for both users(Quantity:, Customer name:,Street: City:, State: ,Zip:,Card:,Card Nr:, Expire date)
		Step 6: Take ScreenShot before submitting each user
 */

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utils.CommonMethods;

public class WebOrdersTask extends CommonMethods{
	@BeforeMethod 
	public void setUp() {
	setUpDriver("chrome", "http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Process.aspx");	
	driver.findElement(By.xpath("//input[contains(@id,'username')]")).sendKeys("Tester");
	driver.findElement(By.xpath("//input[contains(@id,'password')]")).sendKeys("test");
	driver.findElement(By.xpath("//input[contains(@id,'login_button')]")).click();

	}

	@DataProvider(name ="setOfData")

	public Object [][] setUpData() {
		
		Object [] [] data = new Object[2][9]	;
		
		data[0][0]="100";
		data[0][1]="John Smith";	
		data[0][2]="123 street ";
		data[0][3]="Ashburn";	
		data[0][4]="Va";
		data[0][5]="20147";	
		data[0][6]="Visa";
		data[0][7]="0000";	
		data[0][8]="00/00";	
		
		
		data[1][0]="200";
		data[1][1]="Mary Smith";	
		data[1][2]="123 street ";
		data[1][3]="Ashburn";	
		data[1][4]="Va";
		data[1][5]="20147";	
		data[1][6]="MasterCard";
		data[1][7]="0001";	
		data[1][8]="01/00";	
		
				
	    return data;
		
	}

	@Test(dataProvider = "setOfData")
	public void  testUsers(String quantity, String customerName, String street, String city, String state, String zip, String card , String cardNr, String date) throws IOException {
	driver.findElement(By.xpath("//a[text()='Order']")).click();
	driver.findElement(By.xpath("//input[contains(@id,'Quantity')]")).sendKeys(quantity);
	driver.findElement(By.xpath("//input[contains(@id,'Name')]")).sendKeys(customerName);
	driver.findElement(By.xpath("//input[contains(@id,'TextBox2')]")).sendKeys(street);
	driver.findElement(By.xpath("//input[contains(@id,'TextBox3')]")).sendKeys(city);
	driver.findElement(By.xpath("//input[contains(@id,'TextBox4')]")).sendKeys(state);
	driver.findElement(By.xpath("//input[contains(@id,'TextBox5')]")).sendKeys(zip);
	driver.findElement(By.xpath("//input[contains(@value,'"+card+"')]")).click();
	driver.findElement(By.xpath("//input[contains(@id,'TextBox6')]")).sendKeys(cardNr);
	driver.findElement(By.xpath("//input[contains(@name,'TextBox1')]")).sendKeys(date);
	if(customerName.contains("John Smith")) {
	takeScreenshot("taskOne", "smartbearsoftwareUserOne");
	}
	else  {
		takeScreenshot("taskOne", "smartbearsoftwareUserTwo");
	}
	driver.findElement(By.xpath("//a[contains(@id,'InsertButton')]")).click();
		
		
		
	}

	@AfterMethod 
	public void tearDown() {
	driver.close();

	}
}

