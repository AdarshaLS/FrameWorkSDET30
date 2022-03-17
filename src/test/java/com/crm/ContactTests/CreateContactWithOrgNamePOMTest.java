package com.crm.ContactTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtility;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.ObjectRepository.ContactsInfoPage;
import com.crm.ObjectRepository.ContactsPage;
import com.crm.ObjectRepository.CreateContactPage;
import com.crm.ObjectRepository.CreateOrganizationPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;
import com.crm.ObjectRepository.OrganizationInfoPage;
import com.crm.ObjectRepository.OrganizationsPage;

public class CreateContactWithOrgNamePOMTest {
	
	@Test
	public void createContactWithOrgNamePOMTest() throws Throwable
	{
		ExcelFileUtility eLib = new ExcelFileUtility();
		PropertyFileUtility pLib = new PropertyFileUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		
		//Step 1 : Read all necessary data
		String BROWSER = pLib.readDataFromPropertyFile("browser");
		String URL = pLib.readDataFromPropertyFile("url");
		String USERNAME = pLib.readDataFromPropertyFile("username");
		String PASSWORD = pLib.readDataFromPropertyFile("password");
		
		String lastName = eLib.readDataFromExcel("Contacts", 1, 2)+"_"+jLib.getRandomNumber();
		String OrgName = eLib.readDataFromExcel("Contacts", 4, 3)+"_"+jLib.getRandomNumber();
		
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
				lp.loginToApp(USERNAME, PASSWORD);
				
				//Step 4 : Navigate to organization link
				HomePage hp = new HomePage(driver);
				hp.clickOnOrgLnk();
				
				//Step 5 : Click on create organization link
				OrganizationsPage op = new OrganizationsPage(driver);
				op.clickOnCreateOrgImg();
				
				//Step 6 : enter mandatory fields and save
				CreateOrganizationPage cop = new CreateOrganizationPage(driver);
				cop.createNewOrg(OrgName);
				
				//Step 7 : Verify the organization
				OrganizationInfoPage oip = new 	OrganizationInfoPage(driver);
				String header = oip.OrgNameInfo();
				if(header.contains(OrgName))
				{
					System.out.println(header);
					System.out.println("Data is verified");
				}
				else
				{
					System.out.println(header);
					System.out.println("Org Not Created");
				}
				
				//Step 8 : Navigate to contacts link
				hp.clickOnContactLnk();
				
				ContactsPage cp = new ContactsPage(driver);
				cp.clickOnCreateContactImg();
				
				CreateContactPage ccp = new CreateContactPage(driver);
				ccp.createNewContact(driver, lastName, OrgName);
		
				//Step 9: Verify for contact
				ContactsInfoPage cip = new ContactsInfoPage(driver);
				String contactHeader = cip.contactNameInfo();
				if(contactHeader.contains(lastName))
				{
					System.out.println(contactHeader +"contact created");
				}
				else
				{
					System.out.println("Contact not created");
				}
				
				//Step 10 : Logout out the application and close browser
				hp.signOutOfApp(driver);
				driver.quit();	
		
	}

}
