package com.prestashop.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class PositiveScenarios {
	Faker faker = new Faker();
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
	public void loginTest() throws InterruptedException {
		String password = "password";
		String email="";
		String firstName=faker.name().firstName();
		String lastName=faker.name().lastName();
		String name = firstName+" "+lastName;
		do {	
			email = faker.name().firstName() + "@" + "gmail.com";
			driver.findElement(By.cssSelector("#email_create")).sendKeys(email);	
			driver.findElement(By.cssSelector("#SubmitCreate")).click();
			
		} while (driver.findElement(By.cssSelector("#create_account_error")).isDisplayed());
		email+=driver.findElement(By.cssSelector("#email_create")).getText();
		Thread.sleep(3000);
		
		driver.findElement(By.cssSelector("#customer_firstname")).sendKeys(firstName);
		driver.findElement(By.cssSelector("#customer_lastname")).sendKeys(lastName);
		
		driver.findElement(By.cssSelector("#passwd")).sendKeys("password");
		driver.findElement(By.cssSelector("#address1")).sendKeys("Some Street 1001");
		driver.findElement(By.cssSelector("#city")).sendKeys("City of Future");
		driver.findElement(By.cssSelector("#id_state")).sendKeys("Virginia"+Keys.ENTER);
		driver.findElement(By.cssSelector("#postcode")).sendKeys(faker.number().digits(5));
		driver.findElement(By.cssSelector("#phone_mobile")).sendKeys("703-111-1234");
		
		driver.findElement(By.cssSelector("#submitAccount")).click();
		
		driver.findElement(By.cssSelector(".logout")).click();
			
		driver.findElement(By.cssSelector("#email")).sendKeys(email);
		driver.findElement(By.cssSelector("#passwd")).sendKeys(password);
		driver.findElement(By.cssSelector("#SubmitLogin")).click();
		
			
		Assert.assertTrue(driver.findElement(By.cssSelector(".account")).getText().equalsIgnoreCase(name));
		
	}
	


}
