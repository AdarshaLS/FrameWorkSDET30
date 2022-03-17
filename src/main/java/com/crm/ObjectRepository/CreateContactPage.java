package com.crm.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class CreateContactPage extends WebDriverUtility {
	
		//Step 1 : Declaration
		@FindBy(name = "lastname")
		private WebElement lastNameEdt;
		
		@FindBy(xpath = "//input[@name='account_name']/following-sibling::img[@alt='Select']")
		private WebElement OrgNameLookUpImg;
		
		@FindBy(name="leadsource")
		private WebElement leadSourceDropDown;
		
		@FindBy(xpath = "//input[@title='Save [Alt+S]']")
		private WebElement saveBtn;
		
		@FindBy(name ="search_text")
		private WebElement searchEdt;	
		
		@FindBy(name = "search")
		private WebElement searchBtn;
		
		//Initialization
		public CreateContactPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}

		//Utilization
		public WebElement getLastNameEdt() {
			return lastNameEdt;
		}

		public WebElement getOrgNameLookUpImg() {
			return OrgNameLookUpImg;
		}

		public WebElement getLeadSourceDropDown() {
			return leadSourceDropDown;
		}

		public WebElement getSaveBtn() {
			return saveBtn;
		}

		public WebElement getSearchEdt() {
			return searchEdt;
		}

		public WebElement getSearchBtn() {
			return searchBtn;
		}
		
		
		//Business Library
		
		/**
		 * This method will create new contact and click on save button
		 * @param lastName
		 */
		public void createNewContact(String lastName)
		{
			lastNameEdt.sendKeys(lastName);
			saveBtn.click();
		}
		
		/**
		 * This method will create contact with lead source type
		 * @param lastName
		 * @param leadSource
		 */
		public void createNewContact(String lastName,String leadSource)
		{
			lastNameEdt.sendKeys(lastName);
			select(leadSource, leadSourceDropDown);
			saveBtn.click();
		}
		
 
		/**
		 * This method will create new contact with organization type
		 * @param driver
		 * @param lastName
		 * @param OrgName
		 */
		public void createNewContact(WebDriver driver,String lastName, String OrgName)
		{
			lastNameEdt.sendKeys(lastName);
			OrgNameLookUpImg.click();
			switchTOWindow(driver, "Accounts");
			searchEdt.sendKeys(OrgName);
			searchBtn.click();
			driver.findElement(By.xpath("//a[text()='"+OrgName+"']")).click();
			switchTOWindow(driver, "Contacts");
			saveBtn.click();
		}
}
