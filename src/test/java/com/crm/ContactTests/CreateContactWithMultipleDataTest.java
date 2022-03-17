package com.crm.ContactTests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.ObjectRepository.ContactsInfoPage;
import com.crm.ObjectRepository.ContactsPage;
import com.crm.ObjectRepository.CreateContactPage;
import com.crm.ObjectRepository.HomePage;

public class CreateContactWithMultipleDataTest extends BaseClass {
	
	@Test(dataProvider = "CreateContactWithTestData")
	public void createContactWithMultipleDataTest(String lastName)
	{
		//Navigate to contacts link
		HomePage hp = new HomePage(driver);
		hp.clickOnContactLnk();
		Reporter.log("navigate to contact link",true);
		
		
		//click on create contact
		ContactsPage cnP = new ContactsPage(driver);
		cnP.clickOnCreateContactImg();
		Reporter.log("clicked on create contact image",true);
		
		//Create contact 
		CreateContactPage cCnP = new CreateContactPage(driver);
		cCnP.createNewContact(lastName);
		Reporter.log("create contact with data",true);
		
		//Verification
		ContactsInfoPage cnIP = new ContactsInfoPage(driver);
		String actCnName = cnIP.contactNameInfo();
		Assert.assertTrue(actCnName.contains(lastName));		
	}
	
	@DataProvider(name = "CreateContactWithTestData" )
	public Object[][] getData() throws Throwable
	{
		Object[][] data = eLib.readMultipleDataFromExcel("ContactWithData");
		return data;
	}

}
