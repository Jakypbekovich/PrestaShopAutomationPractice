package com.prestashop.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NegativeScenarios {
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com");
		driver.findElement(By.cssSelector(".login")).click();	
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	
	@Test 
	public void wrongCredentialsTest(){
		driver.findElement(By.cssSelector("#email")).sendKeys("testing@gmail.com");
		driver.findElement(By.cssSelector("#passwd")).sendKeys("randompassword");
		driver.findElement(By.cssSelector("#SubmitLogin")).click();		
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert.alert-danger")).getText().contains("Authentication failed."));
	}
	
	@Test
	public void invalidEmailTest() {
		driver.findElement(By.cssSelector("#email")).sendKeys("testing@gmailcom");
		driver.findElement(By.cssSelector("#passwd")).sendKeys("randompassword");
		driver.findElement(By.cssSelector("#SubmitLogin")).click();		
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert.alert-danger")).getText().contains("Invalid email address."));
	}
	
	@Test
	public void blankEmailTest() {
		driver.findElement(By.cssSelector("#passwd")).sendKeys("randompassword");
		driver.findElement(By.cssSelector("#SubmitLogin")).click();		
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert.alert-danger")).getText().contains("An email address required."));
	}
	
	@Test
	public void blankPasswordTest() {
		driver.findElement(By.cssSelector("#email")).sendKeys("testing@gmail.com");
		driver.findElement(By.cssSelector("#SubmitLogin")).click();		
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert.alert-danger")).getText().contains("Password is required."));
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
