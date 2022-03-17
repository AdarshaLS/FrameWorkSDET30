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
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;

public class CreateContactswithLeadSourceTest {
	
@Test
	
	public void createContactswithLeadSourceTest() throws Throwable
	{
		//Step 1 : Importing the methods
		PropertyFileUtility pLib = new PropertyFileUtility();
		ExcelFileUtility eLib = new ExcelFileUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		
		//Step 2 : Reading data from the Property file
		String BROWSER = pLib.readDataFromPropertyFile("browser");
		String URL = pLib.readDataFromPropertyFile("url");
		String USERNAME = pLib.readDataFromPropertyFile("username");
		String PASSWORD = pLib.readDataFromPropertyFile("password");
		
		//Step 3 : Reading Data from Excel file
		String cntName = eLib.readDataFromExcel("Contacts", 7, 2)+"_"+jLib.getRandomNumber();
		String typDrDown = eLib.readDataFromExcel("Contacts", 7, 3);
		
		//Step 4 : Launching the browser
		WebDriver driver = null;
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if (BROWSER.equalsIgnoreCase("Firefox"))
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
		
		//Step 5 : Login to application
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		//Step 6 : Navigate to contacts tab and click on application
		HomePage hp = new HomePage(driver);
		hp.clickOnContactLnk();
		
		//Step 7 : Click on add contacts link
		ContactsPage cp = new ContactsPage(driver);
		cp.clickOnCreateContactImg();
		
		//Step 8 : Creating Organization with lead source
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.createNewContact(cntName, typDrDown);
		
		//Step 9 : Verifying the contact creation
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String contactHeader = cip.contactNameInfo();
		if(contactHeader.contains(cntName))
		{
			System.out.println(cntName);
			System.out.println("Data is created");
		}
		else
		{
			System.out.println(cntName);
			System.out.println("Data is not created");
		}
		
		//Step 9 : logging out of application and quit driver
		hp.signOutOfApp(driver);
		driver.quit();			
	}
}
