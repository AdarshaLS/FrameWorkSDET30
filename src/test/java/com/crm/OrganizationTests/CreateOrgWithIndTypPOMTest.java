package com.crm.OrganizationTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtility;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.ObjectRepository.CreateOrganizationPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;
import com.crm.ObjectRepository.OrganizationInfoPage;
import com.crm.ObjectRepository.OrganizationsPage;

public class CreateOrgWithIndTypPOMTest {

	
	@Test
	public void createOrgHealthcarePOMTest() throws Throwable
	{
	
	ExcelFileUtility eLib = new ExcelFileUtility();
	JavaUtility jLib = new JavaUtility();
	PropertyFileUtility pLib = new PropertyFileUtility();
	WebDriverUtility wLib = new WebDriverUtility();
	
	/* load all the nessacry data*/
			String BROWSER = pLib.readDataFromPropertyFile("browser");
			String URL = pLib.readDataFromPropertyFile("url");
			String USERNAME = pLib.readDataFromPropertyFile("username");
			String PASSWORD = pLib.readDataFromPropertyFile("password");
			
			String OrgName = eLib.readDataFromExcel("Org", 1, 2)+"_"+jLib.getRandomNumber();
			String indType = eLib.readDataFromExcel("Org", 4, 3);
			
			// Step 2: launch the browser
			WebDriver driver = null;
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
				System.out.println("invalid browser");
			}
			
			wLib.maximizeWindow(driver);
			wLib.waitforPageLoad(driver);
			driver.get(URL);
			
			//Step 3 : login to application
			LoginPage lp = new LoginPage(driver);
			lp.loginToApp(USERNAME, PASSWORD);
			
			//Step 4 : Navigate to Organizations Link
			HomePage hp = new HomePage(driver);
			hp.clickOnOrgLnk();
			
			//Step 5 : click on create Organization button
			OrganizationsPage op = new OrganizationsPage(driver);
			op.clickOnCreateOrgImg();
			
			//Step 6 : enter mandatory fields and healthcare domain click save
			CreateOrganizationPage cop = new CreateOrganizationPage(driver);
			cop.createNewOrg(OrgName, indType);
			
			//Step 7 : Verification of organization
			OrganizationInfoPage oip = new OrganizationInfoPage(driver);
			String actOrgHeader = oip.OrgNameInfo();
			Assert.assertTrue(actOrgHeader.contains(OrgName));
			Reporter.log(actOrgHeader+"Org is created", true);
			
			//Step 7: logout of application 
			WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			wLib.waitForElementToBeVisible(driver, ele);
			wLib.mouseHover(driver, ele);
			driver.findElement(By.xpath("//a[.='Sign Out']")).click();
			
			//Step 8: close the browser
			driver.quit();

}
}
