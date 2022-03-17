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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class CreatewContactWithTeamSellingGroupAssgnTest {
	
	@Test
	public void createContactWithTeamsellingGroup() throws Throwable
	{
		//Step 1 : Reading all necessary data
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.Properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String BROWSER = pObj.getProperty("browser");
		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD = pObj.getProperty("password");
		
		FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\Test Data.xlsx");
		Workbook wb = WorkbookFactory.create(fi);
		Sheet sh = wb.getSheet("Contacts TC");
		Row ro = sh.getRow(5);
		Cell cel = ro.getCell(2);
		String ContactName = cel.getStringCellValue();
		
		Cell cele = ro.getCell(3);
		String TeamGroup = cele.getStringCellValue();
		
		//Step 2: launch the browser
		WebDriver driver = null;
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("FireFox"))
		{
			driver = new FirefoxDriver();
		}
		else
		{
			System.out.println("Invalid Browser");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(URL);
		
		// Step 3: login to application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Step 4 : Click on Contacts link
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step 5 : Click on Create contact
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		//Step 6 : Enter the mandatory fields
		driver.findElement(By.name("lastname")).sendKeys(ContactName);
		
		//Step 7 : select group radio button
		driver.findElement(By.xpath("//input[@value='T']")).click();
		
		//Step 8 : select team selling group
		WebElement ele = driver.findElement(By.name("assigned_group_id"));
		Thread.sleep(3000);
		Select s = new Select(ele);
		s.selectByVisibleText(TeamGroup);
		
		//Step 9 : Saving the contacts 
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 9 : Verifying of the element
				String conName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				
				if(conName.contains(ContactName))
				{
					System.out.println(ContactName);
					System.out.println("Contact is created");
				}
				else
				{
					System.out.println(ContactName);
					System.out.println("Contact is not created");
				}
				
				Thread.sleep(5000);
				WebElement element = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				Actions act = new Actions(driver);
				act.moveToElement(element).perform();
				
				driver.findElement(By.xpath("//a[.='Sign Out']")).click();
				Thread.sleep(5000);
		
		//Step 10 : close the browser
		driver.quit();			
	}	
}
