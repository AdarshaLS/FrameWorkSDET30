package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class CreateOrganizationPage extends WebDriverUtility {
	
		// Step 1 : Declaration
		@FindBy(name = "accountname")
		private WebElement orgNameEdt;
		
		@FindBy(name = "industry")
		private WebElement industryDropDown;
		
		@FindBy(name = "accounttype")
		private WebElement typeDropDown;
		
		@FindBy(xpath = "//input[@title='Save [Alt+S]']")
		private WebElement saveBtn;
		
		//Step 2: Initialization
		public CreateOrganizationPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}
		

		//Utilization
		
		public WebElement getOrgNameEdt() {
			return orgNameEdt;
		}

		public WebElement getIndustryDropDown() {
			return industryDropDown;
		}

		public WebElement getTypeDropDown() {
			return typeDropDown;
		}

		public WebElement getSaveBtn() {
			return saveBtn;
		}
		
		
		//Business Library
		
		/**
		 * This method will Create new Organization and click on save
		 * @param orgName
		 */
		public void createNewOrg(String orgName)
		{
			orgNameEdt.sendKeys(orgName);
			saveBtn.click();
		}
		
		
		/**
		 * This method will create new organization with industry type and save data
		 * @param orgName
		 * @param indType
		 */
		public void createNewOrg(String orgName,String indType)
		{
			orgNameEdt.sendKeys(orgName);
			select(indType, industryDropDown);
			saveBtn.click();
		}
		
		/**
		 * This method will create new organization with industry type and type press and save data
		 * @param orgName
		 * @param indType
		 * @param typ
		 */
		public void createNewOrg(String orgName,String indType,String typ)
		{
			orgNameEdt.sendKeys(orgName);
			select(industryDropDown, indType);
			select(typeDropDown, typ);
			saveBtn.click();
		}
}
