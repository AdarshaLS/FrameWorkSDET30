package com.crm.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class CreateOpportunityPage extends WebDriverUtility {
	
		//Declaration
		@FindBy(name="potentialname")
		private WebElement OpportunityNameEdt;
		
		@FindBy(xpath="//img[@src='themes/softed/images/select.gif']")
		private WebElement OrganizationsLookupImg;
		
		@FindBy(id="related_to_type")
		private WebElement TypClick;
		
		@FindBy(name="search_text")
		private WebElement searchEdt;
		
		@FindBy(name = "search")
		private WebElement searchBtn;
		
		@FindBy(xpath = "//input[@value ='U']")
		private WebElement AsgnToUsrRadio;
		
		@FindBy(xpath = "//input[@value ='T']")
		private WebElement AsgnToGrpRadio;
		
		@FindBy(name ="campaignname")
		private WebElement CampaignNameEdt;
		
		@FindBy(xpath = "//input[@name='campaignname']/following-sibling::img[@src='themes/softed/images/select.gif']")
		private WebElement CampaignLookUpImg;
		
		@FindBy(xpath = "//input[@title='Save [Alt+S]']")
		private WebElement saveBtn;
		
		//Initialization
		public CreateOpportunityPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}

		//Utilization
		public WebElement getOpportunityNameEdt() {
			return OpportunityNameEdt;
		}

		public WebElement getOrganizationsLookupImg() {
			return OrganizationsLookupImg;
		}

		public WebElement getTypClick() {
			return TypClick;
		}

		public WebElement getSearchEdt() {
			return searchEdt;
		}

		public WebElement getSearchBtn() {
			return searchBtn;
		}

		public WebElement getAsgnToUsrRadio() {
			return AsgnToUsrRadio;
		}

		public WebElement getAsgnToGrpRadio() {
			return AsgnToGrpRadio;
		}

		public WebElement getCampaignNameEdt() {
			return CampaignNameEdt;
		}

		public WebElement getCampaignLookUpImg() {
			return CampaignLookUpImg;
		}

		public WebElement getSaveBtn() {
			return saveBtn;
		}

		
		//Business library
		
		/**
		 * This method will create opportunity with organizations dropdown selection
		 * @param opprtunityName
		 * @param relatedTotxt
		 * @param orgName
		 * @param driver
		 */
		public void createOpportunityWithOrganization(String opprtunityName, String relatedTotxt, String orgName, WebDriver driver )
		{
			OpportunityNameEdt.sendKeys(opprtunityName);
			select(TypClick, relatedTotxt);
			OrganizationsLookupImg.click();
			switchTOWindow(driver, "Accounts");
			searchEdt.sendKeys(orgName);
			searchBtn.click();
			driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
			switchTOWindow(driver, "Potentials");
			saveBtn.click();
		}
		
		/**
		 * This method will create opportunity with Organization name and campaign source
		 * @param opportunityName
		 * @param relatedTotxt
		 * @param orgName
		 * @param driver
		 * @param campaignName
		 */
		public void createOpportunityWithOrganizationCampaignSrc(String opportunityName, String relatedTotxt, String orgName, WebDriver driver, String campaignName)
		{
			OpportunityNameEdt.sendKeys(opportunityName);
			select(TypClick, relatedTotxt);
			OrganizationsLookupImg.click();
			switchTOWindow(driver, "Accounts");
			searchEdt.sendKeys(orgName);
			searchBtn.click();
			driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
			switchTOWindow(driver, "Potentials");
			CampaignLookUpImg.click();
			switchTOWindow(driver, "Campaigns");
			searchEdt.sendKeys(campaignName);
			searchBtn.click();
			driver.findElement(By.xpath("//a[text()='"+campaignName+"']")).click();
			switchTOWindow(driver, "Potentials");
			saveBtn.click();
		}
		
}
