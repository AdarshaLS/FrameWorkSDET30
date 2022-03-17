package com.crm.ContactTests;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class CreateContactTest {
	
	@Test
	public void createContactTest() throws Throwable
	{
		//Generate Random number
		Random ran = new Random();
		int random = ran.nextInt(500);
		
		//Step 1: read all necessary data
		//Read data from property file
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties pObj=new Properties();
		pObj.load(fis);
		String BROWSER = pObj.getProperty("browser");
		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD = pObj.getProperty("password");
		
		//read data from Excel sheet
		FileInputStream fis1=new FileInputStream(".\\src\\test\\resources\\Test Data.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Cell cel = (Cell) wb.getSheet("Contacts").getRow(1).getCell(2);
		String contact = cel.getStringCellValue();
		
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
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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
		driver.findElement(By.name("lastname")).sendKeys(contact+" "+random);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 7 : logout of application
		WebElement ele = driver.findElement(By.xpath("//img[@style='padding: 0px;padding-left:5px' and @src='themes/softed/images/user.PNG']"));
		Actions act =new Actions(driver);
		act.moveToElement(ele).build().perform();
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		//Step 8: Close the browser
		driver.quit();
		
		
	}

}
