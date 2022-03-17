package com.crm.ContactTests;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class EditOneContactAssgnTest {
	
	@Test
	public void editOneContactAssgnTest() throws Throwable
	{
		// Reading all necessary data
		//Step 1 : Reading data from property file
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.Properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String BROWSER = pObj.getProperty("browser");
		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD = pObj.getProperty("password");
		
		// Step 2 : Reading data from Excel file
		FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\Test Data.xlsx");
		Workbook wb = WorkbookFactory.create(fi);
		Sheet sh = wb.getSheet("Contacts TC");
		Row ro = sh.getRow(17);
		Cell cel = ro.getCell(2);
		String ContactName = cel.getStringCellValue();
		
		//Step 3 : Launch the browser
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
		
		// Step 4 : Login into application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		// Step 5 : Navigating to Contacts tab
		driver.findElement(By.linkText("Contacts")).click();
		
		// Step 6 : Creating contact as a Precondition
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		// Step 7 : Enter details 
		driver.findElement(By.name("lastname")).sendKeys(ContactName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 8 : Navigating contacts list
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.linkText("edit")).click();
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();		
		
		//Step 9 : closing the browser
		driver.quit();	
		
	}

}
