package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class HomePage  extends WebDriverUtility {
	
		//Step 1 : Declaration
		@FindBy(linkText = "Organizations")
		private WebElement organizationLnk;
		
		@FindBy(linkText = "Contacts")
		private WebElement contactsLnk;
		
		@FindBy(linkText = "Opportunities")
		private WebElement opportunitiesLnk;
		
		@FindBy(linkText = "Products")
		private WebElement productsLnk;
		
		@FindBy(linkText = "More")
		private WebElement moreLnk;
		
		@FindBy(linkText = "Campaigns")
		private WebElement campaignsLnk;
		
		@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
		private WebElement adminstratorImg;
		
		@FindBy(linkText = "Sign Out")
		private WebElement signOutLnk;
		
		//Step 2 : Initialization
		public HomePage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}

		//Step 3 : Utilization
		public WebElement getOrganizationLnk() {
			return organizationLnk;
		}

		public WebElement getContactsLnk() {
			return contactsLnk;
		}

		public WebElement getOpportunitiesLnk() {
			return opportunitiesLnk;
		}

		public WebElement getProductsLnk() {
			return productsLnk;
		}

		public WebElement getMoreLnk() {
			return moreLnk;
		}

		public WebElement getCampaignsLnk() {
			return campaignsLnk;
		}

		public WebElement getAdminstratorImg() {
			return adminstratorImg;
		}

		public WebElement getSignOutLnk() {
			return signOutLnk;
		}

		
		
		//Business Library
		
		/**
		 * This method will click on Organization link in home page
		 */
		public void clickOnOrgLnk()
		{
			organizationLnk.click();
		}
		
		/**
		 * This method will click on contacts link
		 */
		public void clickOnContactLnk()
		{
			contactsLnk.click();
		}
		
		/**
		 * This method will sign out of the application
		 * @param driver
		 */
		public void signOutOfApp(WebDriver driver)
		{
			mouseHover(driver, adminstratorImg);
			signOutLnk.click();
		}
		
		/**
		 * This method will click on opportunities link in home page
		 */
		public void clickOnOpportunityLnk()
		{
			opportunitiesLnk.click();
		}
		
		/**
		 * This method will click on campaign link in more tab
		 * @param driver
		 */
		public void clickOnCampaignLnk(WebDriver driver)
		{
			mouseHover(driver, moreLnk);
			campaignsLnk.click();
		}
}
