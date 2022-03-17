package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class CampaignsPage extends WebDriverUtility {
	
	//Declaration
		@FindBy(xpath="//img[@alt='Create Campaign...']")
		private WebElement createCampaignImg;
		
		//Initialization
		public CampaignsPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}

		//Utilization
		public WebElement getCreateCampaignImg() {
			return createCampaignImg;
		}
		
		//Business Library
		/**
		 * This method will click on create campaign image
		 */
		public void clickOnCreateCampaign()
		{
			createCampaignImg.click();
		}
}
