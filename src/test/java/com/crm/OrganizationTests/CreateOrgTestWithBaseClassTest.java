package com.crm.OrganizationTests;

import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.ObjectRepository.CreateOrganizationPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.OrganizationInfoPage;
import com.crm.ObjectRepository.OrganizationsPage;

public class CreateOrgTestWithBaseClassTest extends BaseClass{
	
	@Test
	public void createOrgTestWithBaseClass() throws Throwable
	{
		String orgName = eLib.readDataFromExcel("Contacts", 1, 2)+"_"+jLib.getRandomNumber();
		
		/*Step 4: Navigate to Organizations link*/
		HomePage hp = new HomePage(driver);
		hp.clickOnOrgLnk();
		
		//Step 5: click on create Organizations button
		OrganizationsPage Op = new OrganizationsPage(driver);
		Op.clickOnCreateOrgImg();
		
		//Step 6: enter all mandatory fields and save
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.createNewOrg(orgName);
		
		//step 7 : Verification
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.OrgNameInfo();
		if(actOrgName.contains(orgName))
		{
			System.out.println(actOrgName+"---> data is verified");
			}
			else
			{
				System.out.println("data is invalid");
			}
	}
}
