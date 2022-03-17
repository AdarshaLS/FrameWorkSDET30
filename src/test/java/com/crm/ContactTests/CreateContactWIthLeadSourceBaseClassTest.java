package com.crm.ContactTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.ObjectRepository.ContactsInfoPage;
import com.crm.ObjectRepository.CreateContactPage;
import com.crm.ObjectRepository.HomePage;

public class CreateContactWIthLeadSourceBaseClassTest extends BaseClass {
	
	@Test
	public void createContactWIthLeadSourceBaseClassTest() throws Throwable
	{
		//load data from excel
		String lastName = eLib.readDataFromExcel("Contacts", 7, 2)+"_"+jLib.getRandomNumber();
		String leadSource = eLib.readDataFromExcel("Contacts", 7, 3);
		
		// Navigate to contacts link
		HomePage hmp = new HomePage(driver);
		hmp.clickOnCampaignLnk(driver);
		Assert.fail();
		
		//Create contacts with leadsource
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.createNewContact(lastName, leadSource);		
		
		//Verification
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String actContact = cip.contactNameInfo();
		Assert.assertTrue(actContact.contains(lastName));
			
	}

}
