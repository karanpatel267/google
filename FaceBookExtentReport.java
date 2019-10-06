package com.expedia.flightbookig;


import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class FaceBookExtentReport {
	WebDriver driver ;
	ExtentReports report;
	ExtentTest test;

	@BeforeClass
  public void beforeClass() {
	  report = new ExtentReports("C:\\Users\\karan\\Desktop\\java programs\\ExtentReports\\.html");
	  test = report.startTest("Verify the test has started");
	  driver = new ChromeDriver();
	  test.log(LogStatus.INFO, "Browser has been opened");
	  driver.manage().window().maximize();
	  test.log(LogStatus.INFO, "Browser has been maximized");
	  driver.get("https://www.facebook.com/");
	  driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
  }
  @Test
  public void test() throws InterruptedException {
	 WebElement username  = driver.findElement(By.id("email"));
	 username.sendKeys("karandpatel@yahoo.com");
	 
	 test.log(LogStatus.INFO, "enter the username");
	 
	 WebElement password = driver.findElement(By.id("pass"));
	 password.sendKeys("Sakhi1975");
	 test.log(LogStatus.INFO, "enter the password");
	 
	 
	 /*Thread.sleep(4000);*/
	 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	 
	 test.log(LogStatus.INFO, "click on the login button");
	 
	 WebElement login = driver.findElement(By.xpath("//input[@type='submit']"));
	 login.click();
	 
	 Alert alert =driver.switchTo().alert();
	 alert.accept();
	 
	// WebElement name = driver.findElement(By.xpath("//*[@id=\"navItem_100002541690900\"]/a/div"));
	 WebElement name = null;
	try {
	 
	  name = driver.findElement(By.xpath("//div[text()='Karan Patel']"));
	}catch(Exception e) {
		System.out.println(e.getMessage());
	}
	Assert.assertTrue(name ==null);
	test.log(LogStatus.PASS, "test has been passed");
	 
  }
  @AfterMethod
  public void tearDown(ITestResult testresult) throws IOException, InterruptedException {
	  if(testresult.getStatus()==ITestResult.FAILURE) {
		 // String path =takeScreenshot.Screenshot(driver, testresult.getName());
		 // String imgpath =test.addScreenCapture(path);
		  //test.log(LogStatus.FAIL, "test has been failed",imgpath);
	  }
	  
	  Thread.sleep(4000);
	  driver.close();
	  report.endTest(test);
	  report.flush();
  }  
  
}
