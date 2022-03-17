package com.crm.OrganizationTests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CreateOrganizationTest {
	
	@Test	
	public void createOrganizationTest()
	{
		//Step 1: Launch the browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("http://localhost:8888/index.php?action=Login&module=Users");
		
		//Setp 2: login to application
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		//Step 3: Navigate to organizations link
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step 4: click on create organizations link
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		//Step 5: create org with mandatory fields
		driver.findElement(By.name("accountname")).sendKeys("ALL STATES");
		
		//Step 6: save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();	
		
		//Step 7 : quit the browser
		driver.quit();

}
}
