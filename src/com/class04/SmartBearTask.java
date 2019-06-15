package com.class04;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utils.CommonMethods;

	/*
	 * //Identify Priority of Test Cases
			
			http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx?ReturnUrl=%2fsamples%2fTestComplete11%2fWebOrders%2fDefault.aspx
			
			TC 1: WebOrder Verify Successful Login ( )
			Step 1: Open browser and navigate to WebOrder
			Step 2: Enter valid username, enter valid password and click on the  login button
			Step 3: Verify user successfully logged in
			
			TC 2: WebOrder Creating and verifying Users( )
			Step 1: Create Two users and fill out all the required fields
			Step 2: Verify that user one name appears within the table 
		    Step 3: Edit user one  and update user one’s State, assert the new updated State is displayed and save the changes.
			Step 4: Verify that user two name appears within the table 
			Step 5: Edit user two and update user two’s City and assert the new updated City is displayed and save the changes.
			
			TC 3: WebOrder verifying Users( )
			        Step 1 : Assert Both User one and User Two are displayed
			
			Note: Create BeforeClass and AfterClass annotations to open browser and logout from the application. 
			The creation of users two should depend on the creation of users one.
			The validation both users should depend on the creation of both users. 
			Create xml file and share a screenshot of the test.
			 */
	
	public class SmartBearTask extends CommonMethods{
		String uName1 = "John Doe";
		String uName2 = "Jeff Shaw";
		
		@Parameters ({"browser", "url"})
		@BeforeClass (alwaysRun = true)
		/**
		 * open browser
		 * */
		public void setup(String browser, String url) {
			setUpDriver(browser, url);
		}
		
		@Parameters ({"userName", "password"})
		@Test 
		public void login(String uName, String pWord) {
			/*
			 * TC 1: WebOrder Verify Successful Login ( )
				Step 1: Open browser and navigate to WebOrder
				Step 2: Enter valid username, enter valid password and click on the  login button
				Step 3: Verify user successfully logged in

			 * */
			sendText(driver.findElement(By.cssSelector("input[id$=username]")), uName);
			sendText(driver.findElement(By.cssSelector("input[id$=password]")), pWord);
			click(driver.findElement(By.cssSelector("input[id$=button]")));
			
			WebElement loginInfo = driver.findElement(By.cssSelector("div.login_info"));
			Assert.assertTrue(loginInfo.isDisplayed());
			Assert.assertEquals(loginInfo.getText(), "Welcome, "+uName+"! | Logout");
		}
		
		@DataProvider
		public Object userInfo() {
			Object data[][] = new Object [][] {
					{"ScreenSaver", "10", uName1, "1234 Main Street", "Fairfax", "VA", "22030", "MasterCard","771177117711", "12/20"},
					{"FamilyAlbum", "20", uName2, "6789 Duke Street", "Alexandria", "VA", "24102", "Visa", "258025802580", "07/21"}
					};
		
			return data;
		}
		
		@Test (priority = 1, dataProvider = "userInfo")
		public void createUser(String product, String unit, String name, String street, String city, 
									String state, String zip, String card, String cardNum, String expDay) {
			/*
			TC 2: WebOrder Creating and verifying Users( )
			Step 1: Create Two users and fill out all the required fields
			 
			 */
			click(driver.findElement(By.linkText("Order")));
			//(driver.findElement(By.linkText("Order")));

			//user1 & user2
			selectValueFromDD(driver.findElement(By.cssSelector("select[id$='Product']")), product);
			
			sendText(driver.findElement(By.cssSelector("input[id$=Quantity]")), unit);
			sendText(driver.findElement(By.cssSelector("input[id*='Name']")), name);
			sendText(driver.findElement(By.cssSelector("input[id*='Box2']")), street);
			sendText(driver.findElement(By.cssSelector("input[id*='Box3']")), city);
			sendText(driver.findElement(By.cssSelector("input[id*='Box4']")), state);
			sendText(driver.findElement(By.cssSelector("input[id*='Box5']")), zip);
			click(driver.findElement(By.cssSelector("input[value='"+card+"']")));
			sendText(driver.findElement(By.cssSelector("input[id*='Box6']")), cardNum);
			sendText(driver.findElement(By.cssSelector("input[id*='Box1']")), expDay);
			
			driver.findElement(By.cssSelector("a[id*='Button']")).click();
			System.out.println("------------------------------------------------");
		}
		
		@Parameters ({"state", "city"})
		@Test (dependsOnMethods = "createUser")
		public void updateUser(String stateU1, String cityU2) {
			driver.findElement(By.linkText("View all orders")).click();
//			Step 2: Verify that user one name appears within the table 
//			 Step 3: Edit user one  and update user one's State, assert the new updated State is displayed and save the changes.
//			 Step 4: Verify that user two name appears within the table 
//			 Step 5: Edit user two and update user two's City and assert the new updated City is displayed and save the changes.
			int rowNum= driver.findElements(By.xpath("//div/table/tbody/tr")).size();
			int colNum = driver.findElements(By.xpath("//div/table/tbody/tr/th")).size();
			for(int i=2; i<=rowNum; i++) {
				if(driver.findElement(By.xpath("//div/table/tbody/tr["+i+"]/td[2]")).getText().equals(uName1)) {
					click(driver.findElement(By.xpath("//div/table/tbody/tr["+i+"]/td["+colNum+"]")));
					sendText(driver.findElement(By.cssSelector("input[id*='Box4']")), stateU1);
					click(driver.findElement(By.cssSelector("a[id*='Button']")));
					Assert.assertEquals(driver.findElement(By.xpath("//div/table/tbody/tr["+i+"]/td[8]")).getText(), stateU1);
				}else if(driver.findElement(By.xpath("//div/table/tbody/tr["+i+"]/td[2]")).getText().equals(uName2)) {
					click(driver.findElement(By.xpath("//div/table/tbody/tr["+i+"]/td["+colNum+"]")));
					sendText(driver.findElement(By.cssSelector("input[id*='Box3']")), cityU2);
					click(driver.findElement(By.cssSelector("a[id*='Button']")));
					Assert.assertEquals(driver.findElement(By.xpath("//div/table/tbody/tr["+i+"]/td[7]")).getText(), cityU2);	
				}
			}
		}
		
		@Test (dependsOnMethods = {"createUser", "updateUser"})
		public void verifyUsers() {
				//		TC 3: WebOrder verifying Users( )
				//        Step 1 : Assert Both User one and User Two are displayed
				//       The validation both users should depend on the creation of both users
			int rowNum= driver.findElements(By.xpath("//div/table/tbody/tr")).size();

			boolean isUser1Displayed=false, isUser2Displayed=false;
			for(int i=2; i<=rowNum; i++) {
				if(driver.findElement(By.xpath("//div/table/tbody/tr["+i+"]/td[2]")).getText().equals(uName1)) {
					isUser1Displayed = true;
				}else if(driver.findElement(By.xpath("//div/table/tbody/tr["+i+"]/td[2]")).getText().equals(uName2)) {
					isUser2Displayed = true;
				}
			}
			Assert.assertTrue(isUser1Displayed);
			Assert.assertTrue(isUser2Displayed);
				
		}
		@AfterClass (alwaysRun = true)
		/**
		 * logout from the application. 
		 * */
		public void tearDown() {
			click(driver.findElement(By.linkText("Logout")));
			driver.quit();
			
		}
	}


