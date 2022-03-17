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

public class DeleteMultipleContactsAssgnTest {
	
	@Test
	public void deleteMultipleContacts() throws Throwable
	{
		//Step 1 :Read all necessary data
		//Read data from property file
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.Properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String BROWSER = pObj.getProperty("browser");
		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD = pObj.getProperty("password");
		
		//Read Data from excel
		FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\Test Data.xlsx");
		Workbook wb = WorkbookFactory.create(fi);
		Sheet sh = wb.getSheet("Contacts TC");
		Row ro = sh.getRow(8);
		Cell cel = ro.getCell(2);
		String FstContactName = cel.getStringCellValue(); 
		
		Row sr = sh.getRow(9);
		Cell scel = sr.getCell(2);
		String SndContactName = scel.getStringCellValue();
		
		//Step 2 : launch the browser
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
		
		//Step 3 : Login to application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		// Step 4 : Click on contacts link
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step 5 : Click on add contacts
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		//Step 6 : Creating First contacts
		// First Contact
		driver.findElement(By.name("lastname")).sendKeys(FstContactName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		Thread.sleep(3000);
		
		//Step 7 : Click on add contacts
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		// Step 8 : Creating Second contacts
		driver.findElement(By.name("lastname")).sendKeys(SndContactName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		Thread.sleep(3000);
		
		//Step 9 : Navigate to contacts page to view list
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step 10: select all contacts and delete
		driver.findElement(By.name("selectall")).click();
		
		// Step 10: Click on delete button
		driver.findElement(By.xpath("//input[@value='Delete']")).click();
		
		//Step 11 : Handling alert window
		Alert a = driver.switchTo().alert();
		String AlertTxt = a.getText();
		System.out.println(AlertTxt);
		a.accept();
		
		//Step 12 : Close the driver
		driver.quit();		
		
	}
	

}
