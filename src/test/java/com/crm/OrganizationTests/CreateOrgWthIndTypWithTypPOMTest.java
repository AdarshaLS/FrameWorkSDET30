package com.crm.OrganizationTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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

public class CreateOrgWthIndTypWithTypPOMTest {
	
	@Test
	public void createOrgWtIndTypeWtPressPOMTest() throws Throwable
	{
		PropertyFileUtility pLib = new PropertyFileUtility();
		ExcelFileUtility eLib = new ExcelFileUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		
		String BROWSER = pLib.readDataFromPropertyFile("browser");
		String URL = pLib.readDataFromPropertyFile("url");
		String USERNAME = pLib.readDataFromPropertyFile("username");
		String PASSWORD = pLib.readDataFromPropertyFile("password");
		
		String OrgName = eLib.readDataFromExcel("Org", 7, 2)+"_"+jLib.getRandomNumber();
		String indType = eLib.readDataFromExcel("Org", 7, 3);
		String typName = eLib.readDataFromExcel("Org", 7, 4);
		
		//open(Launch) the browser          
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
		
		//Step 3 : Login to application
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME,	 PASSWORD);

		//Step 4 : Navigate to Organization link
		HomePage hp = new HomePage(driver);
		hp.clickOnOrgLnk();
		
		//Step 5: click on create Organization button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnCreateOrgImg();
		
		// Step 6: Enter the mandatory fields and select industry type as healthcare and save
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.createNewOrg(OrgName, indType, typName);
		
		//Step 7 : Verification 
		OrganizationInfoPage oip =new OrganizationInfoPage(driver);
		String actOrgHeader = oip.OrgNameInfo();
		if(actOrgHeader.contains(OrgName))
		{
			System.out.println(actOrgHeader+"--->"+"Data Verified");
		}
		else
		{
			System.out.println("data not verified");
		}
		
		//Step 8 : logout of application
		hp.signOutOfApp(driver);
		
		//Step 9 : Close the application
		driver.quit();		
	}
}
