package com.crm.ContactTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtility;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;

public class CreateContactTestWtFrmWTest {
	
	@Test
	public void createContactTestWtFrmW() throws Throwable
	{
		PropertyFileUtility pLib = new PropertyFileUtility();
		JavaUtility jLib = new JavaUtility();
		ExcelFileUtility eLib = new ExcelFileUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		
		//Step 1: read all necessary data
		String BROWSER = pLib.readDataFromPropertyFile("browser");
		String URL = pLib.readDataFromPropertyFile("url");
		String USERNAME = pLib.readDataFromPropertyFile("username");
		String PASSWORD = pLib.readDataFromPropertyFile("password");
		
		String lastname = eLib.readDataFromExcel("Contacts", 3, 1)+"_"+jLib.getRandomNumber();
		
		//Step 2: Open(launch) the browser
		WebDriver driver=null;
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else
		{
			System.out.println("No browser Present");
		}
		
		wLib.maximizeWindow(driver);
		wLib.waitforPageLoad(driver);
		driver.get(URL);
		
		//Step 3 : login to application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Step 4 : Navigate to contacts link
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step 5: click on create contact button
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		//Step 6 : Enter mandatory fields in create page and save
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 7 : logout of application
		WebElement ele = driver.findElement(By.xpath("//img[@style='padding: 0px;padding-left:5px' and @src='themes/softed/images/user.PNG']"));
		wLib.mouseHover(driver, ele);
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		//Step 8: Close the browser
		driver.quit();		
		
	}

}
