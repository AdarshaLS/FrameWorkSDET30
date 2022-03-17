package com.crm.ContactTests;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class DeleteOneContactAssgnTest {
	
	@Test
	public void deleteOneContact() throws Throwable
	{
		//Step 1 : Reading necessary data
		//Reading data from Property file
		
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.Properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String BROWSER = pObj.getProperty("browser");
		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD = pObj.getProperty("password");
		
		//Reading data from Excel
		FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\Test Data.xlsx");
		Workbook wb = WorkbookFactory.create(fi);
		Sheet sh = wb.getSheet("Contacts TC");
		Row ro = sh.getRow(13);
		Cell cel = ro.getCell(2);
		String ContactName = cel.getStringCellValue();
		
		//Step 2 : Launching the browser
		WebDriver driver = null;
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("Firefox"))
		{
			driver = new FirefoxDriver();
		}
		else
		{
			System.out.println("Invalid browser");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(URL);
		
		// Step 3 : Login into application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		// Step 4 : Navigating to link tab
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step 5 : Creating a contact as a precondition
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(ContactName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		Thread.sleep(3000);
		
		//Step 6 : Navigating to contacts list page
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.linkText("del")).click();
		
		//Step 7 : Handling alert window
		Alert a = driver.switchTo().alert();
		String text = a.getText();
		System.out.println(text);
		a.accept();
		
		//Step 8 : Logging out of the application
		driver.quit();		
	}

}
