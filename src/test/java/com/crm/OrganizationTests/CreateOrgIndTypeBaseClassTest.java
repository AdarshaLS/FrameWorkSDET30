package com.crm.OrganizationTests;

import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.ObjectRepository.CreateOrganizationPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.OrganizationInfoPage;
import com.crm.ObjectRepository.OrganizationsPage;

public class CreateOrgIndTypeBaseClassTest extends BaseClass {
	
	@Test
	public void createOrgIndTypeBaseClassTest() throws Throwable
	{
	
			String orgName = eLib.readDataFromExcel("Org", 1, 2)+"_"+jLib.getRandomNumber();
			String indType = eLib.readDataFromExcel("Org", 4, 3);
			
			
			HomePage hp=new HomePage(driver);
			hp.clickOnOrgLnk();
			
			//click on create organization button
			
			OrganizationsPage op= new OrganizationsPage(driver);
			op.clickOnCreateOrgImg();
			
			//enter the mandatory field  and select industry type as healthcare and save
			CreateOrganizationPage cop= new CreateOrganizationPage(driver);
			cop.createNewOrg(orgName, indType);
			
			//verification
			OrganizationInfoPage oip=new OrganizationInfoPage(driver);
			String actOrgHeader = oip.OrgNameInfo();
			if(actOrgHeader.contains(orgName))
			{
				System.out.println(actOrgHeader+"---->"+"Data verified");
			}
			else
			{
				System.out.println("data not verified");
			}
			
			}

}
