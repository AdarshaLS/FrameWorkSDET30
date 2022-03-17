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

public class CreateOrgPOMTest {
	
	@Test
	public void createOrgPOMTest() throws Throwable
	{
		ExcelFileUtility eLib = new ExcelFileUtility();
		JavaUtility jLib = new JavaUtility();
		PropertyFileUtility pLib = new PropertyFileUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		
		//Step 1: read all necessary data
		String BROWSER = pLib.readDataFromPropertyFile("browser");
		String URL = pLib.readDataFromPropertyFile("url");
		String USERNAME = pLib.readDataFromPropertyFile("username");
		String PASSWORD = pLib.readDataFromPropertyFile("password");
		
		String OrgName = eLib.readDataFromExcel("Contacts", 1, 2)+jLib.getRandomNumber();		
		
		/*Step 2: launch the browser*/
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
			System.out.println("Invalid Browser");
		}
		
		wLib.maximizeWindow(driver);
		wLib.waitforPageLoad(driver);
		driver.get(URL);
		
		/*Step 3 : login to application*/
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		/*Step 4: Navigate to Organizations link*/
		HomePage hp = new HomePage(driver);
		hp.clickOnOrgLnk();
		
		//Step 5: click on create Organizations button
		OrganizationsPage Op = new OrganizationsPage(driver);
		Op.clickOnCreateOrgImg();
		
		//Step 6: enter all mandatory fields and save
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.createNewOrg(OrgName);
		
		//step 7 : Verification
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.OrgNameInfo();
		if(actOrgName.contains(OrgName))
		{
		System.out.println(actOrgName+"---> data is verified");
		}
		else
		{
			System.out.println("data is invalid");
		}
		
		//Step 7: logout of the application
		hp.signOutOfApp(driver);
		
		//Step 8:close the browser
		driver.quit();
			
	}

}
