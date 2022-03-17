package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class OrganizationsPage  extends WebDriverUtility {
	
		//Step 1 : declaration
		@FindBy(xpath = "//img[@alt='Create Organization...']")
		private WebElement createOrgLookUpImg;
		
		//Step 2 : Initialization
		public OrganizationsPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}

		//Step 3 : Utilization
		public WebElement getCreateOrgLookUpImg() {
			return createOrgLookUpImg;
		}

		
		//Business Library
		
		/**
		 * This method will click on create organization image in organizations page
		 */
		public void clickOnCreateOrgImg()
		{
			createOrgLookUpImg.click();
		}
		
}
