package com.crm.ContactTests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.ObjectRepository.ContactsInfoPage;
import com.crm.ObjectRepository.ContactsPage;
import com.crm.ObjectRepository.CreateContactPage;
import com.crm.ObjectRepository.HomePage;

public class CreateContactListnrsAndRetryTest extends BaseClass {
	
	@Test(retryAnalyzer=com.crm.GenericLibrary.RetryAnalyserImplementation.class)
	public void createContactListnrsAndRetry() throws Throwable
	{
		
		// Step 2 : Read all necessary data from file
		String lastName = eLib.readDataFromExcel("Contacts", 1, 2)+"_"+jLib.getRandomNumber();
		
		//Step 5 : Navigate to contact link
		HomePage hp = new HomePage(driver);
		hp.clickOnContactLnk();
		
		//Step 6 : Click on create contact button
		ContactsPage cp = new ContactsPage(driver);
		cp.clickOnCreateContactImg();
		
		String expData = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		String actData = "Creating New Contact";
		Assert.assertEquals(actData, expData);
		
		//Step 7 : Enter all mandatory fields
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.createNewContact(lastName);
		Assert.fail();
		
		// Step 8 : Verification
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		 String actContactHeader = cip.contactNameInfo();
		 Assert.assertTrue(actContactHeader.contains(lastName));
			
	}

	}


