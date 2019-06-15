package com.class01;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utils.CommonMethods;



/*TC3
* Create the test case for each one of the testng annotation.
* Test
* BeforeMethod
* AfterMethod
*  BeforeClass
*  AfterClass
*  BeforeTest
*  AfterTest
*  BeforeSuite
*  AfterSuite
*  And Share your screenshot of your report
*/

    public class Task3 extends CommonMethods{
        
        @Test
         public void myTestMethod() {
        
         setUpDriver("chrome", "http://www.seleniumframework.com/Practiceform/");
         System.out.println("is the main test method in the entire program. Other annotated methods will be executed around this method.");
        
         String title = driver.getTitle();
         System.out.println(title);
         driver.quit();
         }
        
         @BeforeSuite
        public void beforeSuite() {
         System.out.println("This piece of code is executed before all the tests in the suite have run.");
         }
        
         @AfterSuite
         public void afterSuite() {
         System.out.println("This piece of code is executed after all the tests in the suite have run.");
         }
        
         @BeforeTest
         public void beforeTest() {
         System.out.println("Test will run before any test method belonging to a class is run.");
         }
        
         @AfterTest
         public void afterTest() {
         System.out.println("Test will run after all the test methods belonging to a class have run.");
         }
         @BeforeMethod
         public void beforeMethod() {
         System.out.println("This piece of code is executed before method:- myTestMethod");
         }
        
         @AfterMethod
         public void afterMethod() {
         System.out.println("This piece of code is executed after method:- myTestMethod");
         }
        
         @BeforeClass
         public void beforeClass() {
         System.out.println("This piece of code is executed before the class is executed");
         }
        
         @AfterClass
         public void afterClass() {
         System.out.println("This piece of code is executed after the class is executed");
         }
        }