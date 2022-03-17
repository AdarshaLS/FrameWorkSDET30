package com.crm.OpprtunityTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtility;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.ObjectRepository.CampaignsInfoPage;
import com.crm.ObjectRepository.CampaignsPage;
import com.crm.ObjectRepository.ContactsInfoPage;
import com.crm.ObjectRepository.ContactsPage;
import com.crm.ObjectRepository.CreateCampaignsPage;
import com.crm.ObjectRepository.CreateContactPage;
import com.crm.ObjectRepository.CreateOpportunityPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;
import com.crm.ObjectRepository.OpportunitiesInfoPage;
import com.crm.ObjectRepository.OpportunitiesPage;

public class OpportunitiesWithConactNCampaignWithPOMTest {
	
	@Test
	public void opportunitiesWithConactNCampaignWithPOM() throws Throwable
	{
		// Step 1 : Importing from Generic library
		PropertyFileUtility pLib = new PropertyFileUtility();
		ExcelFileUtility eLib = new ExcelFileUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		
		// Step 2 : Reading data from property file
		String BROWSER = pLib.readDataFromPropertyFile("browser");
		String URL = pLib.readDataFromPropertyFile("url");
		String USERNAME = pLib.readDataFromPropertyFile("username");
		String PASSWORD = pLib.readDataFromPropertyFile("password");
		
		//Step 3 : Reading data from excel file  
		String opportunityName = eLib.readDataFromExcel("Opportunities", 1, 2)+"_"+jLib.getRandomNumber();
		String contactName = eLib.readDataFromExcel("Opportunities", 1, 3)+"_"+jLib.getRandomNumber();
		String campaignName = eLib.readDataFromExcel("Opportunities", 1, 4)+"_"+jLib.getRandomNumber();
		
		//Step 4 : Launching the browser
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
		
		wLib.maximizeWindow(driver);
		wLib.waitforPageLoad(driver);
		driver.get(URL);
		
		//Step 4 : Login into application
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		//Step 5 : navigate to contact link
		HomePage hp = new HomePage(driver);
		hp.clickOnContactLnk();
		
		//Step 6 : click on create link
		ContactsPage cp = new ContactsPage(driver);
		cp.clickOnCreateContactImg();
		
		//Step 7 : creating contacts
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.createNewContact(contactName);
		
		//Step 8 : verification of contact creation
		ContactsInfoPage cip = new ContactsInfoPage(driver);
         String contactHeader = cip.contactNameInfo();
         if(contactHeader.contains(contactName))
         {
        	 System.out.println(contactName);
        	 System.out.println("Contact created successfully");
         }
         else
         {
        	 System.out.println(contactName);
        	 System.out.println("Contact not created");
         }
         
         //Step 9 : Creating campaign name
       hp.clickOnCampaignLnk(driver);
       CampaignsPage cop = new CampaignsPage(driver);
       cop.clickOnCreateCampaign();
       
       CreateCampaignsPage ccamp = new CreateCampaignsPage(driver);
       ccamp.createCampaign(campaignName);
       
       CampaignsInfoPage camInfoPg = new CampaignsInfoPage(driver);
       String campaignHeader = camInfoPg.campaignHeader(campaignName);
		if(campaignHeader.contains(campaignName))
		{
			System.out.println(campaignName);
			System.out.println("Campaign is created");
		}
		else
		{
			System.out.println(campaignName);
			System.out.println("Campaign is not created");
		}
		
		//Creating opportunities with contact and campaignname
		hp.clickOnOpportunityLnk();
		OpportunitiesPage OP = new OpportunitiesPage(driver);
		OP.clickOnCreateOpportunitiesLink();
		
		CreateOpportunityPage coprtun = new CreateOpportunityPage(driver);
		coprtun.createOpportunityWithOrganizationCampaignSrc(opportunityName, "Contacts", contactName, driver, campaignName);
		
		OpportunitiesInfoPage OpInPg = new OpportunitiesInfoPage(driver);
		String opportunityTxt = OpInPg.OpportunityInfo();
		if(opportunityTxt.contains(opportunityName))
		{
			System.out.println(opportunityName);
			System.out.println("Opportunity is created");
		}
		else
		{
			System.out.println(opportunityName);
			System.out.println("Opportunity is not created");
		}
		
		hp.signOutOfApp(driver);
		driver.quit();
		
	}

}
