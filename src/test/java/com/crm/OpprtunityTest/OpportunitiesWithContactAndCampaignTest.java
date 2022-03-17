package com.crm.OpprtunityTest;

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

public class OpportunitiesWithContactAndCampaignTest {
	
	@Test
	public void opportunitiesWithContactAndCampaign() throws Throwable
	
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
		
		String opportunityName = eLib.readDataFromExcel("Opportunities", 1, 2)+"_"+jLib.getRandomNumber();
		String contactName = eLib.readDataFromExcel("Opportunities", 1, 3)+"_"+jLib.getRandomNumber();
		String campaignName = eLib.readDataFromExcel("Opportunities", 1, 4)+"_"+jLib.getRandomNumber();
		
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
				
				//Step 3 : Login into application
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();
				
				//Step 4 : Creating contact as a precondition to opportunity
				//Navigating to contacts application
				driver.findElement(By.linkText("Contacts")).click();
				
				//Click on add contact
				driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
				
				//Creating contact with mandatory fields
				driver.findElement(By.name("lastname")).sendKeys(contactName);
				
				//Saving the field
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				/*Step 5: Verification Product */
				String header = driver.findElement(By.className("dvHeaderText")).getText();
				if(header.contains(contactName))
				{
					System.out.println(header);
					System.out.println("Contact is created");
				}
				else
				{
					System.out.println(header);
					System.out.println("Contact is not created");
				}
				
				//Step 6: Creating campaign
				//Navigate to campaigns link
				driver.findElement(By.linkText("More")).click();
				driver.findElement(By.linkText("Campaigns")).click();
								
				driver.findElement(By.xpath("//img[@alt='Create Campaign...']")).click();
				driver.findElement(By.name("campaignname")).sendKeys(campaignName);
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				//Step 7: Navigate to opportunities link
				driver.findElement(By.linkText("Opportunities")).click();
				
				//Step 8: click on create opportunity icon
				driver.findElement(By.xpath("//img[@alt='Create Opportunity...']")).click();
				
				//Step 9 : Enter mandatory details
				driver.findElement(By.name("potentialname")).sendKeys(opportunityName);
				
				//Step 10 : Select contacts from drop down
				WebElement ele = driver.findElement(By.name("related_to_type"));
				wLib.select("Contacts", ele);
				driver.findElement(By.xpath("//input[@name='related_to_display']/following-sibling::img")).click();
				
				//Step 11: Switch to Contact Pop-Up
				wLib.switchTOWindow(driver, "Contacts");
				driver.findElement(By.name("search_text")).sendKeys(contactName);
				driver.findElement(By.name("search")).click();
				driver.findElement(By.linkText(contactName)).click();
				
				//Step 12: Switch back to main window and select Employee in Lead Source Dropdown/
				wLib.switchTOWindow(driver, "Potentials");
				WebElement ele1 = driver.findElement(By.name("leadsource"));
				wLib.select("Employee", ele1);
				
				//Step 13: Click on Select Campaign
				driver.findElement(By.xpath("//input[@name='campaignname']/following-sibling::img")).click();
				
				//Step 14: Switch to Campaigns Pop-up and Select Campaign/
				wLib.switchTOWindow(driver, "Campaigns");
				driver.findElement(By.name("search_text")).sendKeys(campaignName);
				driver.findElement(By.name("search")).click();
				 WebElement campName = driver.findElement(By.xpath("//a[text()='"+campaignName+"']"));
				 wLib.waitForElementToBeClickable(driver, campName);
				 campName.click();
								
				//Step 15: Switch back to main window and save 
				wLib.switchTOWindow(driver, "Potentials");
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
							
							
				//Step 16: Verify Campaign/
				String title = driver.findElement(By.className("dvHeaderText")).getText();
				if(title.contains(opportunityName))
				{
					System.out.println(title);
					System.out.println("Opportunity created");
				}
				else
				{
					System.out.println(title);
					System.out.println("Opportunity not created");
				}
				
				//Step 17: logout and close the browser/
				WebElement element = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				wLib.mouseHover(driver, element);
				
				driver.findElement(By.linkText("Sign Out")).click();
				driver.quit();	
				
	}

}
