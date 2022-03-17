package com.crm.ContactTests;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
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
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateCampaignTest {
	
	@Test(retryAnalyzer=com.crm.GenericLibrary.RetryAnalyserImplementation.class)
	public void createCampaignTest() throws Throwable
	{
		//Generate random number
		Random ran = new Random();
		int random = ran.nextInt(500);
		
		// Step 1: read all necessary data
		//read data from property file
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.Properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String BROWSER= pObj.getProperty("browser");
		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD = pObj.getProperty("password");
		
		//read data from excel
		FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\Test Data.xlsx");
		Workbook wb = WorkbookFactory.create(fi);
		Sheet sh = wb.getSheet("Products");
		Row ro = sh.getRow(1);
		Cell cel = ro.getCell(2);
		String ProductName = cel.getStringCellValue();
		String ProductNameRan = ProductName+" "+random;
		
		Sheet shc = wb.getSheet("Campaign");
		Row roc = shc.getRow(1);
		Cell celc = roc.getCell(2);
		String CampaignName = celc.getStringCellValue();
		String CampaignNameRan = CampaignName+" "+random;
		
		
		//Step 3 : launch the browser
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
			System.out.println("invalid browser");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(URL);
		
		/*Step 3: login to application*/
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Step 4 : Navigate to Products tab
		driver.findElement(By.linkText("Products")).click();
		
		//Step 5 : Clicking on create products tab
		driver.findElement(By.xpath("//img[@alt='Create Product...']")).click();
		
		//Step 6 : Enter Mandatory fields and save
		driver.findElement(By.name("productname")).sendKeys(ProductNameRan);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		Assert.fail();
		
		//Step 7: verify the product created
		String header = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if(header.contains(ProductNameRan))
		{
			System.out.println(header);
			System.out.println("Product is Created");
		}
		else
		{
			System.out.println(header);
			System.out.println("Product is not created");
		}
		
		//Step 8: Navigate to campaign link
		driver.findElement(By.xpath("//img[@src='themes/softed/images/menuDnArrow.gif']")).click();
		driver.findElement(By.name("Campaigns")).click();
		driver.findElement(By.xpath("//img[@alt='Create Campaign...']")).click();
		driver.findElement(By.name("campaignname")).sendKeys(CampaignNameRan);
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
		
		// Step 9: Choose the product
		Set<String> win = driver.getWindowHandles();
		for(String winId:win)
		{
			driver.switchTo().window(winId);
		}
		
		driver.findElement(By.name("search_text")).sendKeys(ProductNameRan);
		
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+ProductNameRan+"']")).click();
		
		Set<String> win1 = driver.getWindowHandles();
		for(String wi : win1)
		{
			driver.switchTo().window(wi);
		}
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 10: Verify for Header
		String campaignHeader= driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(campaignHeader.contains(CampaignNameRan))
				{
			System.out.println(campaignHeader);
			System.out.println("campaign is Created");
				}
		else
		{
			System.out.println("campaign not created");
		}
		
		Thread.sleep(5000);
		WebElement elem = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		
		Actions act = new Actions(driver);
		Thread.sleep(5000);
		act.moveToElement(elem).perform();
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		//Step 11 : Close the application
		driver.quit();
		
	}

}
